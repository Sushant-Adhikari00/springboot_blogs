package com.project.blogs.core.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PaginationDto {
    @NotNull(message = "Should not be null") @Min(value = 0,message = "Must start from zero")
    private int page;

    @NotNull(message = "Should not be null") @Min(value = 1,message = "Must start from zero")
    private int size;

    private String keyword;
}
