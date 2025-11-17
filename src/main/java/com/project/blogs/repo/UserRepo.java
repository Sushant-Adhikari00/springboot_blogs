package com.project.blogs.repo;

import com.project.blogs.entity.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByUniqueId(String uniqueId);
    @Query("""
            SELECT u FROM User u
            WHERE (:keyword IS NULL OR LOWER(u.fullName) LIKE LOWER(CONCAT('%', :keyword, '%')))
               OR (:keyword IS NULL OR LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%')))
               OR (:keyword IS NULL OR LOWER(u.username) LIKE LOWER(CONCAT('%', :keyword, '%')))
        """)
    Page<User> searchUsers(String keyword, Pageable pageable);

    User findByUniqueId(String uniqueId);

    Optional<User> findByUsername(@NotBlank(message = "Username is required") String username);
}
