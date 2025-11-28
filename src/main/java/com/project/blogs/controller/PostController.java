package com.project.blogs.controller;

import com.project.blogs.core.dto.ApiResponse;
import com.project.blogs.core.dto.PaginationDto;
import com.project.blogs.dto.post_dto.request.CreatePostRequestDto;
import com.project.blogs.dto.post_dto.request.DeletePostRequestDto;
import com.project.blogs.dto.post_dto.request.UpdatePostRequestDto;
import com.project.blogs.dto.post_dto.request.ViewPostRequestDto;
import com.project.blogs.dto.post_dto.response.ViewPostResponseDto;
import com.project.blogs.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<?>> save(@RequestBody @Valid CreatePostRequestDto postRequestDto) {
        ApiResponse<?> apiResponse = postService.savePost(postRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PostMapping("/list")
    public ResponseEntity<ApiResponse<?>> getAllPosts(@RequestBody @Valid PaginationDto pageRequestDto) {
        ApiResponse<?> apiResponse = postService.listAllPost(pageRequestDto);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/listMyPost")
    public ResponseEntity<ApiResponse<?>> getMyPosts(Principal loggedInUser) {
        ApiResponse<?> apiResponse = postService.listMyPost(loggedInUser);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/view")
    public ResponseEntity<ApiResponse<?>> getPost(@RequestBody @Valid ViewPostRequestDto viewPostRequestDto) {
        return  postService.viewPost(viewPostRequestDto);
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse<?>> update(@RequestBody @Valid UpdatePostRequestDto updatePostRequestDto) {
        ApiResponse<?> apiResponse = postService.updatePost(updatePostRequestDto);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<ApiResponse<?>> delete(@RequestBody @Valid DeletePostRequestDto deletePostRequestDto) {
        ApiResponse<?> apiResponse = postService.deletePost(deletePostRequestDto);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
