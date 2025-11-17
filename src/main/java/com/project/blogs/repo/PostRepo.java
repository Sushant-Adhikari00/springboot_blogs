package com.project.blogs.repo;

import com.project.blogs.entity.Post;
import com.project.blogs.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByAuthor_Id(int userId);
    List<Post> findByAuthor_Username(String username);
    @Query("SELECT p FROM Post p JOIN FETCH p.author WHERE p.id = :id")
    Post getPostWithAuthor(int id);

    Page<Post> findAll(Pageable pageable);

    @Query("""
            SELECT p FROM Post p
            WHERE (:keyword IS NULL OR LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')))
               OR (:keyword IS NULL OR LOWER(p.slug) LIKE LOWER(CONCAT('%', :keyword, '%')))
               OR (:keyword IS NULL OR LOWER(p.content) LIKE LOWER(CONCAT('%', :keyword, '%')))
        """)
    Page<Post> searchPosts(String keyword, Pageable pageable);
    boolean existsBySlug(String slug);
    Optional<Post> findBySlug(String slug);

}
