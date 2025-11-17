package com.project.blogs.dto.user_dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteUserDto {
    @NotBlank(message = "Username is required")
    private String username;
}
