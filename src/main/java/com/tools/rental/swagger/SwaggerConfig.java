package com.tools.rental.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	@Bean
	GroupedOpenApi publicApi(){
		return GroupedOpenApi.builder()
				.group("public-apis")
				.pathsToMatch("/**")
				.build();
	}
	/*
	@Bean
	OpenAPI customeAPI(){
		return new OpenAPI()
				.info(new Info().title("API Title").version("API Version"))
				.addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
				.components(new Components()
				.addSecuritySchemes("bearerAuth", new SecurityScheme()
						.type(SecurityScheme.Type.HTTP)
						.scheme("bearer")
						.bearerFormat("JWT")));
	}
	*/
}
