package com.project.blogs.dto.user_dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ResponseViewUserDto {
    private String fullName;
    private String username;
    private String email;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:s a")
    private LocalDateTime createdAt;
    private String profilePicture;
}
