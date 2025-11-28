package com.project.blogs.controller;

import com.project.blogs.core.dto.ApiResponse;
import com.project.blogs.dto.user_dto.request.LoginUser;
import com.project.blogs.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody @Valid LoginUser loginUser) {
        ApiResponse<?> apiResponse = authenticationService.authenticate(loginUser);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<?>> refreshToken(HttpServletRequest request, HttpServletResponse response ) {
        ApiResponse<?> apiResponse = authenticationService.refreshToken(request, response);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
