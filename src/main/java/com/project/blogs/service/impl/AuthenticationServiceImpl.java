package com.project.blogs.service.impl;

import com.project.blogs.core.dto.ApiResponse;
import com.project.blogs.dto.user_dto.request.LoginUser;
import com.project.blogs.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ApiResponse<?> authenticate(LoginUser loginUser) {
       try{
           Authentication authentication = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword())
           );
           return new ApiResponse<>(true,200,LocalDateTime.now(), "Logged in successfully");
       }
       catch(Exception e){
           throw new BadCredentialsException("Invalid email or password");
       }
   }

}
