package com.project.blogs.service;

import com.project.blogs.core.dto.ApiResponse;
import com.project.blogs.dto.user_dto.request.LoginUser;

public interface AuthenticationService {
    ApiResponse<?> authenticate(LoginUser loginUser);
}
