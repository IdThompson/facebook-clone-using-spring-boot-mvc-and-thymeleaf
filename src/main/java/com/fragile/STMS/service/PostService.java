package com.fragile.STMS.service;

import com.fragile.STMS.entity.Post;
import com.fragile.STMS.entity.User;
import com.fragile.STMS.mapper.LikePost;

import java.util.List;

public interface PostService {
    void addPost(User user, Post post);

    List<LikePost> getAllPost(User user);

    void updatePost(Post post);

    void deletePost(Post post);

    Post getPostById(Long id);
}
