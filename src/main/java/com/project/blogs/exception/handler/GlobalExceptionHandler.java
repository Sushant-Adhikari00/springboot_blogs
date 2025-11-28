package com.project.blogs.exception.handler;

import com.project.blogs.core.dto.ApiResponse;
import com.project.blogs.exception.DuplicateException;
import com.project.blogs.exception.FileSizeExceededException;
import com.project.blogs.exception.InvalidTokenException;
import com.project.blogs.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ApiResponse<?>> handleDuplicateException(DuplicateException e) {
        ApiResponse<?> apiResponse = new ApiResponse<>(false, HttpStatus.CONFLICT.value(), LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleNotFoundException(NotFoundException e) {
        ApiResponse<?> apiResponse = new ApiResponse<>(false, HttpStatus.NOT_FOUND.value(), LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationErrors(MethodArgumentNotValidException e) {
        String error = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        return new ResponseEntity<>(new ApiResponse<>(false, HttpStatus.BAD_REQUEST.value(),LocalDateTime.now(), error
        ), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception e) {
        ApiResponse<?> apiResponse = new ApiResponse<>(false,HttpStatus.INTERNAL_SERVER_ERROR.value(),LocalDateTime.now(),e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ApiResponse<?>> handleInvalidTokenException(InvalidTokenException e) {
        ApiResponse<?> apiResponse = new ApiResponse<>(false,HttpStatus.UNAUTHORIZED.value(),LocalDateTime.now(),e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(FileSizeExceededException.class)
    public ResponseEntity<ApiResponse<?>> handleFileSizeExceededException(FileSizeExceededException e) {
        ApiResponse<?> apiResponse = new ApiResponse<>(false,HttpStatus.PAYLOAD_TOO_LARGE.value(),LocalDateTime.now(),e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.PAYLOAD_TOO_LARGE);
    }
}
