package com.project.blogs.service;

import com.project.blogs.core.dto.ApiResponse;
import com.project.blogs.dto.user_dto.request.DeleteUserDto;
import com.project.blogs.dto.user_dto.request.RequestUserDto;
import com.project.blogs.dto.user_dto.request.UpdateUserDto;
import com.project.blogs.dto.user_dto.request.ViewUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UserService{
    ResponseEntity<ApiResponse<?>> saveUser(RequestUserDto requestUserDto, MultipartFile profilePicture);
    ResponseEntity<ApiResponse<?>> viewUser(ViewUserRequest viewUserRequest);
    ResponseEntity<ApiResponse<?>> updateUser(UpdateUserDto updateUserDto);
    ResponseEntity<ApiResponse<?>> deleteUser(DeleteUserDto deleteUserDto);
}
