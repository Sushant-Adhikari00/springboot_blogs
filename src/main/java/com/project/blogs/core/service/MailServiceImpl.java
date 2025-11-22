package com.project.blogs.core.service;

import com.project.blogs.core.dto.email.SendMailRequestDto;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Value("${EMAIL_UNAME}")
    private String sender;

    @Autowired
    private JavaMailSender mailSender;

    @Async
    @Override
    public void sendMail(SendMailRequestDto sendMailRequestDto){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mineMessageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            mineMessageHelper.setFrom(sender);
            mineMessageHelper.setTo(sendMailRequestDto.getRecipient());
            mineMessageHelper.setSubject(sendMailRequestDto.getSubject());
            mineMessageHelper.setText(sendMailRequestDto.getMessage(), true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
