package com.mentalism.testtask.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPI31
public class OpenApiConfig {

    @Bean
    public OpenAPI springOpenApi() {
        return new OpenAPI()
                .openapi("3.0.1")
                .info(new Info().title("[Тестовое задание]"));
    }
}
