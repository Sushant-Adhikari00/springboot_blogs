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

@RestController
@RequestMapping("api/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<?>> save(@RequestBody @Valid CreatePostRequestDto postRequestDto) {
        return postService.savePost(postRequestDto);
    }

    @PostMapping("/list")
    public ResponseEntity<ApiResponse<?>> getAllPosts(@RequestBody @Valid PaginationDto pageRequestDto) {
        return postService.listAllPost(pageRequestDto);
    }

    @PostMapping("/view")
    public ResponseEntity<ApiResponse<?>> getPost(@RequestBody @Valid ViewPostRequestDto viewPostRequestDto) {
        return  postService.viewPost(viewPostRequestDto);
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse<?>> update(@RequestBody @Valid UpdatePostRequestDto updatePostRequestDto) {
        return postService.updatePost(updatePostRequestDto);
    }

    @PostMapping("/delete")
    public ResponseEntity<ApiResponse<?>> delete(@RequestBody @Valid DeletePostRequestDto deletePostRequestDto) {
        return postService.deletePost(deletePostRequestDto);
    }
}
