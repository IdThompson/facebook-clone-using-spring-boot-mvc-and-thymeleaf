package com.fragile.STMS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String surName;

    @Column(unique = true, nullable = false)
    private String emailAddress;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String dateOfBirth;

    @Column(nullable = false)
    private String gender;

}