package com.proyecto.Edutech_v1.proyecto.Edutech_v1.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.servers.Server;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI edutechOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Edutech")
                        .version("1.0")
                        .description("Documentación de la API para Edutech"))
                .components(new Components())
                .servers(List.of(new Server().url("http://localhost:8080")));
    }

    @Bean
    public GroupedOpenApi instructoresApi() {
        return GroupedOpenApi.builder()
                .group("instructores")
                .pathsToMatch("/api/instructores/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("edutech")
                .pathsToMatch("/api/**")
                .build();
    }
}
