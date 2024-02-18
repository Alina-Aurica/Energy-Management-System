package com.example.sensormanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
@Validated
@EnableScheduling
public class SensorManagementServiceApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SensorManagementServiceApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SensorManagementServiceApplication.class, args);
    }

    // test zone
//    @Bean
//    CommandLineRunner init(ReadFromFile readFromFile) {
//        return args -> {
//            readFromFile.readFromCSVFile();
//        };
//    }

}
