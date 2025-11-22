package com.project.blogs.dto.comment_dto.request;

import com.project.blogs.dto.user_dto.response.AuthorDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreateCommentDto {
    @NotBlank(message = "Can't be empty")
    private String content;
    @NotNull(message = "Can't be empty")
    private Integer authorId;
    @NotNull(message = "Can't be empty")
    private Integer postId;

}
