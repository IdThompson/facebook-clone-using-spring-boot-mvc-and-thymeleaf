package com.fragile.STMS.ServiceImp;

import com.fragile.STMS.Repository.CommentRepository;
import com.fragile.STMS.Repository.LikeRepository;
import com.fragile.STMS.Repository.PostRepository;
import com.fragile.STMS.entity.Comment;
import com.fragile.STMS.entity.Post;
import com.fragile.STMS.entity.PostLikes;
import com.fragile.STMS.entity.User;
import com.fragile.STMS.mapper.LikePost;
import com.fragile.STMS.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PostServiceIpm implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    CommentRepository commentRepository;

    /**
     * Method to save a post made by a user to the database
     * @param user the user making the post
     * @param post the post made
     */
    public void addPost(User user, Post post) {
        post.setUser(user);
        postRepository.save(post);
    }


    /**
     * Method to get all the posts made by each user
     * @param user the user that made the post
     * @return the list of posts
     */
    @Override
    public List<LikePost> getAllPost(User user) {
        List<LikePost> listOfLikePosts = new ArrayList<>();
        List<Post> listOfPosts = postRepository.findAllByPostIdIsNotNullOrderByPostIdDesc();

        for (Post post : listOfPosts) {

            LikePost likePosts = new LikePost();

            likePosts.setPostId(post.getPostId());
            likePosts.setTitle(post.getTitle());
            likePosts.setBody(post.getBody());
            likePosts.setUser(post.getUser());

            List<PostLikes> listOfLikes =  likeRepository.findAllByPost(post);
            List<Comment> listOfComments = commentRepository.findCommentByPost(post);
            likePosts.setListOfComments(listOfComments);



            System.out.println("lllllllll" + listOfComments);
            likePosts.setPostLikes(listOfLikes);


            List<PostLikes> listOfPostLikes = likeRepository.findAllByPost(post);

            for (PostLikes like: listOfPostLikes) {
                System.out.println("wwwwww "+like.getUser().getUserId() );
                System.out.println("uuuuuuu "+user.getUserId());
                if (like.getUser().getUserId().equals(user.getUserId())) {
                    likePosts.setLikedPost(true);
                    break;
                }
            }

            System.out.println("tttttttt"+listOfPostLikes);

            listOfLikePosts.add(likePosts);
        }
        return listOfLikePosts;
    }


    /**
     * Method to save an edited post to the database
     * @param post
     */
    @Override
    public void updatePost(Post post) {
        postRepository.save(post);
    }

    /**
     * Method to delete a post from the database
     * @param post the post to be deleted
     */
    @Override
    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    /**
     * Method to get a post by the postId
     * @param id the id of the post
     * @return the post
     */
    @Override
    public Post getPostById(Long id) {
        return postRepository.findByPostId(id);
    }
}
