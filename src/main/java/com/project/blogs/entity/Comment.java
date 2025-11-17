package com.project.blogs.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name ="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name ="content",nullable = false)
    private String content;
    @Column(name ="author_id",nullable = false)
    private Integer authorId;
    @Column(name ="post_id",nullable = false)
    private Integer postId;
    @Column(name ="created_at",nullable = false)
    private LocalDateTime createdAt;
}
