package com.fragile.STMS.ServiceImp;

import com.fragile.STMS.Repository.CommentRepository;
import com.fragile.STMS.Repository.PostRepository;
import com.fragile.STMS.entity.Comment;
import com.fragile.STMS.entity.Post;
import com.fragile.STMS.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImplementation implements CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;


//    /**
//     * Method to save a comment to the database
//     * @param user the user making the comment
//     * @param comment the comment made
//     * @param postId the id of the post
//     */
//    @Override
//    public void addComment(User user, Comment comment, Long postId) {
//        Post post = postRepository.findByPostId(postId);
//        comment.setUser(user);
//        comment.setPost(post);
//        commentRepository.save(comment);
//    }

    /**
     * Method to save a comment
     * @param comment the comment made
     */
    @Override
    public void saveComment(Comment comment){
        commentRepository.save(comment);
    }


    /**
     * Method to find all the comments in a post
     * @param post the post that has the comments
     * @return the list of comments
     */
    @Override
    public List<Comment> findCommentByPost(Post post) {
        return commentRepository.findCommentByPost(post);
    }

    /**
     * Method to delete a comment by the id
     * @param commentId the id of the comment
     */
    @Override
    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteCommentByCommentId(commentId);
    }

    @Override
    @Transactional
    public void deleteAllCommentsInPost(Post post) {
        commentRepository.deleteAllByPost(post);
    }


//    @Override
//    public List<Comment> getAllComment() {
//        return commentRepository.findAllByCommentIdIsNotNullOrderByCommentIdDesc();
//    }


    @Override
    public void updateComment(Comment editedComment) {
        commentRepository.save(editedComment);
    }

//    @Override
//    public void deleteComment(Comment comment) {
//        commentRepository.delete(comment);
//    }


    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findByCommentId(id);
    }
}