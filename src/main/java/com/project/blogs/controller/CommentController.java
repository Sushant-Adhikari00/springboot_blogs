package com.project.blogs.controller;

import com.project.blogs.core.dto.ApiResponse;
import com.project.blogs.core.dto.PaginationDto;
import com.project.blogs.dto.comment_dto.request.CreateCommentDto;
import com.project.blogs.dto.comment_dto.request.DeleteCommentDto;
import com.project.blogs.dto.comment_dto.response.ListCommentDto;
import com.project.blogs.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/comment")
public class CommentController {
    @Autowired
    CommentService commentService;


    @PostMapping("/add")
    public ResponseEntity<ApiResponse<?>> save(@RequestBody @Valid CreateCommentDto createCommentDto){
             return (ResponseEntity<ApiResponse<?>>) commentService.addComment(createCommentDto);
    }

    @PostMapping("/list")
    public ResponseEntity<ApiResponse<?>> list(@RequestBody @Valid PaginationDto paginationDto){
        return (ResponseEntity<ApiResponse<?>>) commentService.listAllComment(paginationDto);
    }

    @PostMapping("/delete")
    public ResponseEntity<ApiResponse<?>> delete(@RequestBody @Valid DeleteCommentDto deleteCommentDto){
        return (ResponseEntity<ApiResponse<?>>) commentService.deleteComment(deleteCommentDto);
    }

}
