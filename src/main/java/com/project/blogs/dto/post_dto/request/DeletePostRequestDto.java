package com.project.blogs.dto.post_dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DeletePostRequestDto {
    @NotBlank(message = "Enter Valid Value")
    private String slug;
}
