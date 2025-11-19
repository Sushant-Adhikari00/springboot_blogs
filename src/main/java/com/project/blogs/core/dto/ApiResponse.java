package com.project.blogs.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> implements Serializable {
    private boolean success;
    private String message;
    private Integer StatusCode;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime Timestamp;
    private T Data;

    public ApiResponse(boolean success, Integer statusCode, LocalDateTime timestamp, String message) {
        this.success = success;
        this.message = message;
        this.StatusCode = statusCode;
        this.Timestamp = timestamp;
    }

}
