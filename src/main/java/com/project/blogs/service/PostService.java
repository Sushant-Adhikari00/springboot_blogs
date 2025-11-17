package com.project.blogs.service;

import com.project.blogs.core.dto.ApiResponse;
import com.project.blogs.core.dto.PaginationDto;
import com.project.blogs.dto.post_dto.request.CreatePostRequestDto;
import com.project.blogs.dto.post_dto.request.DeletePostRequestDto;
import com.project.blogs.dto.post_dto.request.UpdatePostRequestDto;
import com.project.blogs.dto.post_dto.request.ViewPostRequestDto;
import org.springframework.http.ResponseEntity;

public interface  PostService {
    ResponseEntity<ApiResponse<?>> savePost(CreatePostRequestDto postRequestDto);
    ResponseEntity<ApiResponse<?>> listAllPost(PaginationDto paginationDto);
    ResponseEntity<ApiResponse<?>> viewPost(ViewPostRequestDto viewpostRequestDto);
    ResponseEntity<ApiResponse<?>> updatePost(UpdatePostRequestDto updatePostRequestDto);
    ResponseEntity<ApiResponse<?>> deletePost(DeletePostRequestDto deletePostRequestDto);
}
