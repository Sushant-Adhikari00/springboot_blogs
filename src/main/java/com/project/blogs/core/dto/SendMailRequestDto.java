package com.project.blogs.core.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMailRequestDto {
    @NotBlank (message = "Recipient mail is required")
    private String recipient;

    @NotBlank (message = "Subject is required")
    private String subject;

    @NotBlank (message = "Message is required")
    private String message;
}
