package com.project.blogs.service.impl;

import com.project.blogs.core.dto.ApiResponse;
import com.project.blogs.core.security.JwtService;
import com.project.blogs.dto.user_dto.request.LoginUser;
import com.project.blogs.dto.user_dto.response.AuthenticationResponse;
import com.project.blogs.entity.User;
import com.project.blogs.exception.InvalidTokenException;
import com.project.blogs.exception.NotFoundException;
import com.project.blogs.repo.UserRepo;
import com.project.blogs.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtService jwtService;

    @Override
    public ApiResponse<?> authenticate(LoginUser loginUser) {
        Optional<User> user = userRepo.findByEmail(loginUser.getEmail());
        if(user.isEmpty()){
            throw new NotFoundException("User not found");
        }
       try{
           Authentication authentication = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword())
           );

           String accessToken = jwtService.generateAccessToken(user.get());
           String refreshToken = jwtService.generateAccessToken(user.get());

           AuthenticationResponse authenticationResponse = new AuthenticationResponse();
           authenticationResponse.setAccessToken(accessToken);
           authenticationResponse.setRefreshToken(refreshToken);
           return new ApiResponse<>(true,"Logged in successfully",200,LocalDateTime.now(), authenticationResponse);
       }
       catch(Exception e){
           throw new BadCredentialsException("Invalid email or password");
       }
   }

   @Override
    public ApiResponse<?> refreshToken(HttpServletRequest request, HttpServletResponse response){
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            throw new InvalidTokenException("Failed to find refresh token. Please login to continue");
        }

        String refreshTokenInHeader = authHeader.substring(7);
        Optional<User> user = userRepo.findByEmail(jwtService.extractEmail(refreshTokenInHeader));
        if(user.isEmpty()){
            throw new NotFoundException("User not found");
        }
        boolean isRefreshTokenValid = jwtService.validateRefreshToken(refreshTokenInHeader,user.get().getEmail());
        if(!isRefreshTokenValid){
            String accessToken = jwtService.generateAccessToken(user.get());
            String refreshToken = jwtService.generateAccessToken(user.get());
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setAccessToken(accessToken);
            authenticationResponse.setRefreshToken(refreshToken);
            return new ApiResponse<>(true,"Refresh token valid",200,LocalDateTime.now(), authenticationResponse);
        }
        throw new InvalidTokenException("Invalid refresh token. Please login to continue");
   }


}
