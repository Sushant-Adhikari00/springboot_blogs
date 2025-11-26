package com.project.blogs.dto.user_dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestUserDto {
    @NotBlank (message = "Uername Can not be empty")
    private String username;
//    @Pattern(
//            regexp = "^(?=.[!@#$%^&(),.?\":{}|<>])(?=.\\d)(?=.[a-z])(?=.[A-Z])[A-Za-z\\d!@#$%^&(),.?\":{}|<>]{8,15}$",
//            message = "Password must be 8-15 characters long, include at least one lowercase, one uppercase, one digit, and one special character"
//    )
    private String password;
    @NotBlank (message = "Password Can not be empty")
    private String firstName;

    @NotBlank (message = "Email Can not be empty")@Email (message ="Invalid format")
    private String email;



}
