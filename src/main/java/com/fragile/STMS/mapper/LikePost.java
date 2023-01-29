package com.fragile.STMS.mapper;

import com.fragile.STMS.entity.Comment;
import com.fragile.STMS.entity.PostLikes;
import com.fragile.STMS.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikePost {
    private Long postId;
    private String title;
    private String body;
    private User user;
    private List<Comment> listOfComments = new ArrayList<>();
    private List<PostLikes> postLikes = new ArrayList<>();
    private boolean likedPost;


}