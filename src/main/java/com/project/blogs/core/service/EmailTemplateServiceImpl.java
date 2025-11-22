package com.project.blogs.core.service;

import com.project.blogs.core.dto.email.SendMailRequestDto;
import com.project.blogs.entity.Post;
import com.project.blogs.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {
    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private MailService mailService;

    @Override
    public void sendWelcomeMail(User user) {
        Context context = new Context();
        context.setVariable("fullName", user.getFullName());
        context.setVariable("email", user.getEmail());
        String message = templateEngine.process("email/welcome-email", context);
        SendMailRequestDto sendMailRequestDto = new SendMailRequestDto();
        sendMailRequestDto.setRecipient(user.getEmail());
        sendMailRequestDto.setSubject("Account created");
        sendMailRequestDto.setMessage(message);
        mailService.sendMail(sendMailRequestDto);
    }

    public void sendPostCreateMail(Post post) {
        Context context = new Context();
        context.setVariable("fullName", post.getAuthor().getFullName());
        context.setVariable("title", post.getTitle());
        context.setVariable("content", post.getContent());
        context.setVariable("createdAt", post.getCreatedAt());
        String message = templateEngine.process("email/post-create-email", context);
        SendMailRequestDto sendMailRequestDto = new SendMailRequestDto();
        sendMailRequestDto.setRecipient(post.getAuthor().getEmail());
        sendMailRequestDto.setSubject("Post created");
        sendMailRequestDto.setMessage(message);
        mailService.sendMail(sendMailRequestDto);
    }
}
