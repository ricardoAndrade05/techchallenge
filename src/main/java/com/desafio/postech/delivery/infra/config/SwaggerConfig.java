package com.desafio.postech.delivery.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@OpenAPIDefinition
@Configuration
public class SwaggerConfig {
	
	@Bean
	OpenAPI localTech() {
		return new OpenAPI()
				.info(new Info()
						.title("PosTech Delivery API")
						.description("API para gerenciamento de restaurantes")
						.version("1.0.0")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}

}
