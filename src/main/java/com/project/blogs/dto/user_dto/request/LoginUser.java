package com.project.blogs.dto.user_dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUser {
    @NotBlank(message = "Email Can not be empty")@Email(message ="Invalid format")
    private String email;
    private String password;
}
