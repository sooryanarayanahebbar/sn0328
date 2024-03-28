package com.tools.rental;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tools.rental.security.auth.AuthService;
import com.tools.rental.security.config.RegisterRequest;

@SpringBootApplication
public class ToolsRentalDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToolsRentalDemoApplication.class, args);
	}

	/*
	@Bean
	CommandLineRunner commandLineRunner(
			AuthService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.username("admin@mail.com")
					.password("password")
					.role("ADMIN")
					.build();
			System.out.println("Admin Status: " + service.register(admin));

			var user = RegisterRequest.builder()
					.firstname("User")
					.lastname("User")
					.username("user@mail.com")
					.password("password")
					.role("USER")
					.build();
			System.out.println("User Status: " + service.register(user));

		};
	}
	*/
}
