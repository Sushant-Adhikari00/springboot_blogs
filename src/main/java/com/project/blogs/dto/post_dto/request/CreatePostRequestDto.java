package com.project.blogs.dto.post_dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreatePostRequestDto {
    @NotBlank (message = "Post must have title")
    private String title;
    @NotBlank(message = "Content can not be empty")
    private String content;
    private Integer authorId;
}
