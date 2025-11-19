package com.project.blogs.core.dto.email;

import com.project.blogs.entity.Post;
import com.project.blogs.entity.User;

public interface EmailTemplateService {
    void sendWelcomeMail(User user);
    void sendPostCreateMail(Post post);
}
