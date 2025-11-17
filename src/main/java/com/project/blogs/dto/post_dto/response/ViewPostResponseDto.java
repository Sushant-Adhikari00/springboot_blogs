package com.project.blogs.dto.post_dto.response;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class ViewPostResponseDto {
    @NotBlank(message = "Can not be empty")
    private String title;
    @NotBlank (message = "Can not be empty")
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
