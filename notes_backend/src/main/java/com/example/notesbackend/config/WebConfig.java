package com.example.notesbackend.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web configuration for CORS and OpenAPI metadata.
 */
@Configuration
public class WebConfig {

    // PUBLIC_INTERFACE
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        /** Configures global CORS allowing common HTTP methods. */
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }

    // PUBLIC_INTERFACE
    @Bean
    public OpenAPI customOpenAPI() {
        /** Provides custom OpenAPI metadata for the application. */
        return new OpenAPI()
                .info(new Info()
                        .title("Notes Backend API")
                        .version("0.1.0")
                        .description("REST API for managing notes")
                        .contact(new Contact().name("Notes Backend").email("noreply@example.com")))
                .externalDocs(new ExternalDocumentation().description("Swagger UI").url("/swagger-ui.html"));
    }
}
