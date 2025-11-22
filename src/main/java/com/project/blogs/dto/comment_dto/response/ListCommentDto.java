package com.project.blogs.dto.comment_dto.response;

import com.project.blogs.dto.user_dto.response.AuthorDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListCommentDto {
    private String content;
    private AuthorDto author;
}
