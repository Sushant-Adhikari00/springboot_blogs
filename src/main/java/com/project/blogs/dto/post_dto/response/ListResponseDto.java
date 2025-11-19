package com.project.blogs.dto.post_dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
@Getter
@Setter
public class ListResponseDto implements Serializable {
    @NotBlank(message = "Can not be empty")
    private String title;
    @NotBlank (message = "Can not be empty")
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
