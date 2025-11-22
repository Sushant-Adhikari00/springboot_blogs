package com.project.blogs.core.service;

import com.project.blogs.core.dto.email.SendMailRequestDto;

public interface MailService {
    void sendMail(SendMailRequestDto sendMailRequestDto);
}
