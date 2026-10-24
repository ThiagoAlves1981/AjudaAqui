package com.AjudaAqui.trabalhoFinalDAW.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().permitAll() // Libera todas as rotas (produtos, clientes, pedidos, etc.)
            )
            .csrf(csrf -> csrf.disable()); // Desabilita proteção CSRF para simplificar requisições do HTMX

        return http.build();
    }
}