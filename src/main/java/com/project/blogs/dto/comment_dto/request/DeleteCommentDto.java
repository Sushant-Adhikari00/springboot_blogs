package com.project.blogs.dto.comment_dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeleteCommentDto {
    @NotNull(message = "Can't be empty")
    private Integer id;

}
