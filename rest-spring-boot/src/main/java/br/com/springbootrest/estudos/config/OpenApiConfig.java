package br.com.springbootrest.estudos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI().info(new Info().title("Estudo sobre RESTful API with Spring Boot").version("v1")
				.description("Projeto realizado para estudar Spring Boot").termsOfService("http://swagger.io.terms/")
				.license(new License().name("Apache 2.0").url("http://springdoc.org")));

	}
}
