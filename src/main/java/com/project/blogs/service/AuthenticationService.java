package com.project.blogs.service;

import com.project.blogs.core.dto.ApiResponse;
import com.project.blogs.dto.user_dto.request.LoginUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {
    ApiResponse<?> authenticate(LoginUser loginUser);
    ApiResponse<?> refreshToken(HttpServletRequest request, HttpServletResponse response);
}
