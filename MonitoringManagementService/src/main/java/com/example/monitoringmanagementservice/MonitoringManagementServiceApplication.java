package com.example.monitoringmanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication(scanBasePackages = "com.example.monitoringmanagementservice")
@EnableScheduling
@Validated
public class MonitoringManagementServiceApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MonitoringManagementServiceApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MonitoringManagementServiceApplication.class, args);
    }

}
