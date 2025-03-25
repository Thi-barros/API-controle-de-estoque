package com.br.api_controle_estoque.infra.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permite CORS para todas as rotas
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5500")  // URL do seu frontend (ajustar conforme necessário)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Permite os métodos desejados
                .allowedHeaders("Content-Type", "Authorization")  // Permite cabeçalhos específicos
                .allowCredentials(true);  // Permite o envio de credenciais (como cookies, tokens, etc.)
    }

    /*
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowCredentials(true);
        corsConfig.setAllowedOrigins(List.of("http://127.0.0.1:5500"));
        corsConfig.setAllowedHeaders(List.of("*"));
        corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsFilter(source);
    }*/
}