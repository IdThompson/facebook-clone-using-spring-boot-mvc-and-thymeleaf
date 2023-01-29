package com.fragile.STMS.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name="likes")
public class PostLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postLikeId;


    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;
}
