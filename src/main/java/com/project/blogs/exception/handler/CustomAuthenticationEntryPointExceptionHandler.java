package com.project.blogs.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.project.blogs.core.dto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;


@Component
    public class CustomAuthenticationEntryPointExceptionHandler implements AuthenticationEntryPoint {
        private final ObjectMapper objectMapper;

        public CustomAuthenticationEntryPointExceptionHandler(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
            objectMapper.registerModule(new JavaTimeModule());
        }

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            ApiResponse<Object> apiResponse = new ApiResponse<>(
                    false,
                    HttpServletResponse.SC_FORBIDDEN,
                    LocalDateTime.now(),
                    "Authentication Required: " +authException.getMessage()
            );
            response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
        }
    }

