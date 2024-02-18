package com.example.chatmanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
@Validated
public class ChatManagementServiceApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ChatManagementServiceApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ChatManagementServiceApplication.class, args);
    }

}
