package com.fragile.STMS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String commentBody;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;

    private String comments;

    public Comment(String comment, Post post, User user) {
        this.commentBody = comment;
        this.post = post;
        this.user = user;
    }
    public Comment(Long commentId, String comment) {
        this.commentId = commentId;
        this.commentBody = comment;
    }
}