package com.example.usermanagementservice;

import com.example.usermanagementservice.entities.Role;
import com.example.usermanagementservice.entities.Users;
import com.example.usermanagementservice.services.LogInService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserManagementServiceApplication extends SpringBootServletInitializer {


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(UserManagementServiceApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(UserManagementServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner init(LogInService logInService) {
		return args -> {
			Users user = new Users();
			user.setFirstName("Mihai");
			user.setLastName("Morar");
			user.setEmail("mihai.morar@gmail.com");
			user.setPassword("mihai");
			user.setRole(Role.CLIENT);
			logInService.register(user);

			Users admin = new Users();
			admin.setFirstName("Alina");
			admin.setLastName("Aurica");
			admin.setEmail("alynaa.2001@gmail.com");
			admin.setPassword("alina");
			admin.setRole(Role.ADMIN);
			logInService.register(admin);
//            User userInserted = userRepository.save(user);
//
//            System.out.println(userInserted);
//            System.out.println(userRepository.findByFirstName("Mihai"));

		};


	}
}
