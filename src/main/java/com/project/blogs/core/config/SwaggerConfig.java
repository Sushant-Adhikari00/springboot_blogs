package com.project.blogs.core.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                    .title("Advance Blogs Platform API Documentation"))
                .addSecurityItem(new SecurityRequirement()
                        .addList("AdvanceBlogsPlatformSecurityScheme"))
                .components(new Components()
                        .addSecuritySchemes("AdvanceBlogsPlatformSecurityScheme", new SecurityScheme()
                        .name("AdvanceBlogsSecurityScheme")
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")));
    }
}
