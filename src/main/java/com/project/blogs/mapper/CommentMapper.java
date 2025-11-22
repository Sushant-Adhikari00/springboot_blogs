package com.project.blogs.mapper;


import com.project.blogs.dto.comment_dto.request.CreateCommentDto;
import com.project.blogs.dto.comment_dto.request.DeleteCommentDto;
import com.project.blogs.dto.comment_dto.response.ListCommentDto;
import com.project.blogs.entity.Comment;
import com.project.blogs.entity.Post;
import com.project.blogs.entity.User;
import com.project.blogs.exception.NotFoundException;
import com.project.blogs.repo.PostRepo;
import com.project.blogs.repo.UserRepo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CommentMapper {
    @Autowired
    protected UserRepo userRepo;

    @Autowired
    protected PostRepo postRepo;

    public Comment saveComment(CreateCommentDto createCommentDto) {
        User author = userRepo.findById(createCommentDto.getAuthorId())
                .orElseThrow(() -> new NotFoundException("Author not found"));

        Post post = postRepo.findById(createCommentDto.getPostId())
                .orElseThrow(() -> new NotFoundException("Post not found"));
        Comment comment = new Comment();
        comment.setContent(createCommentDto.getContent());
        comment.setAuthorId(author.getId());
        comment.setPostId(post.getId());

        return comment;
    }

    public abstract ListCommentDto entityToRsponse(Comment comment);
    public List<ListCommentDto> listAllComment(Page<Comment> comments) {
        return comments.getContent().stream().map(this::entityToRsponse).collect(Collectors.toList());
    }


    public Comment deleteComment(Comment comment) {
        comment.setIsDeleted(true);
        return comment;
    }
}
