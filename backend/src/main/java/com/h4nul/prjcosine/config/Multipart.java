package com.h4nul.prjcosine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Multipart implements WebMvcConfigurer {
    @Bean
    public MultipartResolver multipartResolver() {
        StandardServletMultipartResolver multipartResolver
                = new StandardServletMultipartResolver();
        return multipartResolver;
    }
}
