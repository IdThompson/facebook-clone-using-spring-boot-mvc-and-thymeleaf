package com.fragile.STMS.ServiceImp;


import com.fragile.STMS.Repository.LikeRepository;
import com.fragile.STMS.Repository.PostRepository;
import com.fragile.STMS.entity.Post;
import com.fragile.STMS.entity.PostLikes;
import com.fragile.STMS.entity.User;
import com.fragile.STMS.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeServiceImplementation implements LikeService {

    @Autowired
    LikeRepository likeRepository;
    @Autowired
    PostRepository postRepository;

    /**
     * Method to like and unlike a post
     * @param user the user performing the action
     * @param postId the id of the post
     * @param action the action performed
     * @return the result
     */
    @Override
    public boolean likePost(User user, Long postId, String action){
        boolean result = false;
        Post post = postRepository.findByPostId(postId);
        try{
            PostLikes like = new PostLikes();
            like.setUser(user);
            like.setPost(post);
            if(action.equals("1")){
                likeRepository.save(like);
                System.out.println("save");
            }else{
                System.out.println("delete");
                likeRepository.deletePostLikesByPostAndUser(post,user);
            }
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    /**
     * Method to delete all the likes in a post
     * @param post the post with the likes to be deleted
     */
    @Override
    @Transactional
    public void deleteAllLikesInPost(Post post) {
        likeRepository.deleteAllByPost(post);
    }
}