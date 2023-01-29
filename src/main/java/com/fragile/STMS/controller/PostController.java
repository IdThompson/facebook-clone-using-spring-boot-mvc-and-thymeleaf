package com.fragile.STMS.controller;

import com.fragile.STMS.dto.ResponseDto;
import com.fragile.STMS.entity.Comment;
import com.fragile.STMS.entity.Post;
import com.fragile.STMS.entity.PostLikes;
import com.fragile.STMS.entity.User;
import com.fragile.STMS.mapper.LikePost;
import com.fragile.STMS.service.CommentService;
import com.fragile.STMS.service.LikeService;
import com.fragile.STMS.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @Autowired
    LikeService likeService;

    /**
     * Method to get the home page
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/home")
    public String getHomePage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("logUser");
        if (user==null) return "redirect:/";
        model.addAttribute("post", new Post());
        model.addAttribute("newComment", new Comment());
        model.addAttribute("loggedUser", user);
        model.addAttribute("postLikes", new PostLikes());
        model.addAttribute("postDelete", new Post());

        List<LikePost> listOfPosts = postService.getAllPost(user);

        model.addAttribute("listOfAllPosts", listOfPosts);
        return "home";
    }

    /**
     * Method to get the update post page
     * @param model
     * @param httpSession
     * @param postId
     * @return
     */
    @GetMapping("/updatepost")
    public String getUpdatePostPage(Model model, HttpSession httpSession, Long postId) {
        User user = (User) httpSession.getAttribute("logUser");
        if (user==null) return "redirect:/";

        Post post = postService.getPostById(postId);

        model.addAttribute("editpost", post);
        model.addAttribute("loggedUser", user);

        return "updatepost";
    }


    @PostMapping("/home_post")
    public String createPost(HttpSession httpSession, @ModelAttribute("post") Post post) {
        User user = (User) httpSession.getAttribute("logUser");
        postService.addPost(user, post);
        return "redirect:/home";
    }


    @PostMapping("/update")
    public String updatePost(HttpSession httpSession, Post post){
        Post newPost = postService.getPostById(post.getPostId());
        newPost.setBody(post.getBody());
        postService.updatePost(newPost);
        return "redirect:/home";
    }


    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") Long id, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        User user = (User) httpSession.getAttribute("logUser");
        ResponseDto response = new ResponseDto();
        if (user == null) {
            return "redirect:/index";
        }
        Post post = postService.getPostById(id);
        commentService.deleteAllCommentsInPost(post);
        likeService.deleteAllLikesInPost(post);
        postService.deletePost(post);
        redirectAttributes.addFlashAttribute("message", response.getMessage());
        return "redirect:/home";
    }

}
