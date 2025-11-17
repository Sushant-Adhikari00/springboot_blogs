package com.project.blogs.repo;

import com.project.blogs.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
