package com.project.blogs.dto.post_dto.response;

import com.project.blogs.dto.user_dto.response.AuthorDto;
import com.project.blogs.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ViewPostResponseDto {
    @NotBlank(message = "Can not be empty")
    private String title;
    @NotBlank (message = "Can not be empty")
    private String content;
    private AuthorDto author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
