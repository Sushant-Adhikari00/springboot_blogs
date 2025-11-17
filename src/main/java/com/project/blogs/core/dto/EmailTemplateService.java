package com.project.blogs.core.dto;

import com.project.blogs.entity.User;

public interface EmailTemplateService {
    public void sendWelcomeMail(User user);
}
