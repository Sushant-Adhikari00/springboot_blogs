package com.project.blogs.dto.post_dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ViewPostRequestDto {
    @NotBlank(message ="Can not be empty")
    private String slug;
}
