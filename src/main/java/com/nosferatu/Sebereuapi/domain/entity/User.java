package com.nosferatu.Sebereuapi.domain.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "tbl_users")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String email;
    private String password;
}