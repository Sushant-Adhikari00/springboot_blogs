package com.project.blogs.controller;

import com.project.blogs.core.dto.ApiResponse;
import com.project.blogs.dto.user_dto.request.DeleteUserDto;
import com.project.blogs.dto.user_dto.request.RequestUserDto;
import com.project.blogs.dto.user_dto.request.UpdateUserDto;
import com.project.blogs.dto.user_dto.request.ViewUserRequest;
import com.project.blogs.service.UserService;
import com.project.blogs.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
//    @Autowired
//    private UserServiceImpl userServiceImpl;
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid RequestUserDto requestUserDto) {
        userService.saveUser(requestUserDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("User Created Successfully");
    }


    @PostMapping("/list")
    public ResponseEntity<ApiResponse<?>> getUser(@RequestBody @Valid ViewUserRequest viewUserRequest) {
        return userService.viewUser(viewUserRequest);
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse<?>> updateUser(@RequestBody @Valid UpdateUserDto updateUserDto) {
        return userService.updateUser(updateUserDto);
    }

    @PostMapping("/delete")
    public ResponseEntity<ApiResponse<?>> deleteUser(@RequestBody @Valid DeleteUserDto deleteUserDto) {
        return userService.deleteUser(deleteUserDto);
    }
}
