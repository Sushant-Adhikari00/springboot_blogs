package com.project.blogs.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name ="comments")
@SQLRestriction("is_deleted = false")
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    @Column(name ="created_at",nullable = false)
    private LocalDateTime createdAt;

    @Column(name="is_deleted", nullable = false)
    private Boolean isDeleted = false;
}
