package com.project.blogs.repo;

import com.project.blogs.entity.Comment;
import com.project.blogs.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {
    Page<Comment> findAll(Pageable pageable);
}
