package com.project.blogs.service.impl;

import com.project.blogs.core.dto.ApiResponse;
import com.project.blogs.core.dto.PaginationDto;
import com.project.blogs.dto.comment_dto.request.CreateCommentDto;
import com.project.blogs.dto.comment_dto.request.DeleteCommentDto;
import com.project.blogs.dto.comment_dto.response.ListCommentDto;
import com.project.blogs.entity.Comment;
import com.project.blogs.exception.NotFoundException;
import com.project.blogs.mapper.CommentMapper;
import com.project.blogs.repo.CommentRepo;
import com.project.blogs.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private CommentMapper commentMapper;

    public ResponseEntity<?> addComment(CreateCommentDto createCommentDto) {
        Comment comment = commentMapper.saveComment(createCommentDto);
        commentRepo.save(comment);

        logger.info("Comment saved successfully");
        ApiResponse<?> apiResponse = new ApiResponse<>(true,200, LocalDateTime.now(),"Comment added successfully");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> listAllComment(PaginationDto paginationDto) {
        Pageable pageable = PageRequest.of(paginationDto.getPage(), paginationDto.getSize(), Sort.Direction.DESC, "id");
        Page<Comment> commentPage =  commentRepo.findAll(pageable);

        List<ListCommentDto> commentList = commentMapper.listAllComment(commentPage);
        logger.info("Comment listed");

        ApiResponse<?> apiResponse = new ApiResponse<>(true,"Comment listed",200, LocalDateTime.now(), commentList);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> deleteComment(DeleteCommentDto deleteCommentDto) {
        Optional<Comment> comment = commentRepo.findById(deleteCommentDto.getId());
        if(comment.isEmpty()){
            logger.info("Post not found");
            throw new NotFoundException("Post not found");
        }

        Comment commentToDelete = commentMapper.deleteComment(comment.get());
        commentRepo.delete(commentToDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
