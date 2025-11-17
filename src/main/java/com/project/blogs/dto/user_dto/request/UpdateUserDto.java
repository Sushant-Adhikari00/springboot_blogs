package com.project.blogs.dto.user_dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDto {
    @NotNull (message = "Can not be null")
    private int id;
    @NotBlank(message = "Can not be empty")
    private String username;
    //    @Pattern(
//            regexp = "^(?=.[!@#$%^&(),.?\":{}|<>])(?=.\\d)(?=.[a-z])(?=.[A-Z])[A-Za-z\\d!@#$%^&(),.?\":{}|<>]{8,15}$",
//            message = "Password must be 8-15 characters long, include at least one lowercase, one uppercase, one digit, and one special character"
//    )
    private String password;
    @NotBlank (message = "Can not be empty")
    private String firstName;
    @NotBlank (message = "Can not be empty")@Email(message ="Invalid format")
    private String email;

}
