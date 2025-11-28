package com.project.blogs.service.impl;

import com.project.blogs.core.dto.ApiResponse;
import com.project.blogs.core.service.EmailTemplateServiceImpl;
import com.project.blogs.core.dto.PaginationDto;
import com.project.blogs.dto.post_dto.request.CreatePostRequestDto;
import com.project.blogs.dto.post_dto.request.DeletePostRequestDto;
import com.project.blogs.dto.post_dto.request.UpdatePostRequestDto;
import com.project.blogs.dto.post_dto.request.ViewPostRequestDto;
import com.project.blogs.dto.post_dto.response.ListResponseDto;
import com.project.blogs.dto.post_dto.response.ViewPostResponseDto;
import com.project.blogs.entity.Post;
import com.project.blogs.entity.User;
import com.project.blogs.exception.NotFoundException;
import com.project.blogs.mapper.PostMapper;
import com.project.blogs.repo.PostRepo;
import com.project.blogs.repo.UserRepo;
import com.project.blogs.service.PostService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private EmailTemplateServiceImpl emailTemplateServiceImpl;
    @Autowired
    private UserRepo userRepo;

    @CacheEvict(value ="users", allEntries = true)
    @Override
    @Transactional
    public ApiResponse<?> savePost(CreatePostRequestDto postRequestDto){
        Post post = postMapper.savePost(postRequestDto);

        postRepo.save(post);
        emailTemplateServiceImpl.sendPostCreateMail(post);
        logger.info(post.toString());

        return new ApiResponse<>(true, 200, LocalDateTime.now(),"Post created successfully");
    }

    @Override
    @Transactional
    @Cacheable(
            value = "users",
            key = "#paginationDto.page + '-' + #paginationDto.size + '-' + (#paginationDto.keyword != null ? #paginationDto.keyword : '')"
    )
    public ApiResponse<?> listAllPost(PaginationDto paginationDto){
        Pageable pageable = PageRequest.of(paginationDto.getPage(), paginationDto.getSize(), Sort.Direction.DESC, "id");
        Page<Post> postsPage;

        if(paginationDto.getKeyword()!=null && paginationDto.getKeyword().trim().isEmpty()){
            postsPage = postRepo.searchPosts(paginationDto.getKeyword().trim(),pageable);
        }
        else {
            postsPage = postRepo.findAll(pageable);
        }
        List<ListResponseDto> listPostResponse = postMapper.listAllPost(postsPage);
        logger.info("Posts listed successfully");

        return new ApiResponse<>(true, "Posts listed" ,200, LocalDateTime.now(), listPostResponse);
    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponse<?>> viewPost(ViewPostRequestDto viewPostRequestDto){
        Optional<Post> post = postRepo.findBySlug(viewPostRequestDto.getSlug());
        if(post.isEmpty()){
            logger.info("Post not found");
            throw new NotFoundException("Post not found");
        }

        ViewPostResponseDto viewPostResponseDto = postMapper.viewPost(post.get());
        logger.info("Post viewed successfully");

        ApiResponse<?> apiResponse = new ApiResponse<>(true, "Post viewed",200, LocalDateTime.now(), viewPostResponseDto);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @CacheEvict(value ="users", allEntries = true)
    @Override
    @Transactional
    public ApiResponse<?> updatePost(UpdatePostRequestDto updatePostRequestDto){
        Optional<Post> postOpt = postRepo.findBySlug(updatePostRequestDto.getSlug());
        if(postOpt.isEmpty()){
            logger.info("Post not found");
            throw new NotFoundException("Post not found");
        }
        Post postExsits = postOpt.get();
         Post postToUpdate = postMapper.updatePost(postExsits, updatePostRequestDto);
         postRepo.save(postToUpdate);
         logger.info("Post updated successfully");

        return new ApiResponse<>(true, 200, LocalDateTime.now(),"Post updated");

    }

    @CacheEvict(value ="users", allEntries = true)
    @Override
    @Transactional
    public ApiResponse<?> deletePost(DeletePostRequestDto deletePostRequestDto){
        Optional<Post> post = postRepo.findBySlug(deletePostRequestDto.getSlug());
        if(post.isEmpty()){
            logger.info("Post not found");
            throw new NotFoundException("Post not found");
        }
        Post postToDelete = postMapper.deletePost(post.get());
        postRepo.save(postToDelete);
        logger.info("Post deleted successfully");

        return new ApiResponse<>(true, 200, LocalDateTime.now(),"Post deleted");

    }

    @Override
    public ApiResponse<?> listMyPost(Principal loggedInUser) {
        Optional<User> user = userRepo.findByEmail(loggedInUser.getName());
        if(user.isEmpty()){
            throw new NotFoundException("User not found");
        }
        List<Post> posts =postRepo.findByAuthor_Id(user.get().getId());
        List<ListResponseDto> postResponse = postMapper.listMyPost(posts);
        return new ApiResponse<>(true, "Posts listed" ,200, LocalDateTime.now(), postResponse);
    }
}
