package com.project.blogs.dto.post_dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdatePostRequestDto {
    @NotBlank(message = "Can not be empty")
    private String slug;
    @NotBlank(message = "Post must have title")
    private String title;
    @NotBlank(message = "Content can not be empty")
    private String content;
    private int authorId;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:s a")
    private LocalDateTime createdAt;
}
