package com.example.pmt_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        
        // Autoriser CORS pour toutes les routes du frontend
        registry.addMapping("/**")  // <-- Autorise toutes les routes backend à accepter des requêtes
                .allowedOrigins("http://localhost:4200")  // <-- Origine du frontend Angular
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // <-- Méthodes HTTP autorisées
                .allowedHeaders("*")  // <-- Autoriser tous les en-têtes
                .allowCredentials(true);  // <-- Si vous avez besoin de cookies ou d'authentification
    }
}
