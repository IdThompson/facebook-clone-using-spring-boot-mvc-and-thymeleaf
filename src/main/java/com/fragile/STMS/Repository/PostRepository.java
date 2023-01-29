package com.fragile.STMS.Repository;

import com.fragile.STMS.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByPostIdIsNotNullOrderByPostIdDesc();
    Post findByPostId(Long id);
}
