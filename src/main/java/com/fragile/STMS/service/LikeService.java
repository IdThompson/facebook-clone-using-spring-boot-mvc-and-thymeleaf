package com.fragile.STMS.service;

import com.fragile.STMS.entity.Post;
import com.fragile.STMS.entity.User;

public interface LikeService {
    boolean likePost(User user, Long postId, String action);
    void deleteAllLikesInPost(Post post);

}
