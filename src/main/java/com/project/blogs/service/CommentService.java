package com.project.blogs.service;

import com.project.blogs.core.dto.PaginationDto;
import com.project.blogs.dto.comment_dto.request.CreateCommentDto;
import com.project.blogs.dto.comment_dto.request.DeleteCommentDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface CommentService {
     ResponseEntity<?> addComment(CreateCommentDto createCommentDto);
     ResponseEntity<?> listAllComment(@Valid PaginationDto paginationDto);
     ResponseEntity<?> deleteComment(DeleteCommentDto deleteCommentDto);
}
