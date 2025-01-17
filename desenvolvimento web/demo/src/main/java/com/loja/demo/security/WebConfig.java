package com.loja.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class WebConfig {
    @SuppressWarnings("deprecation")
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors.configure(http))
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(HttpMethod.GET, "/clientes").permitAll()
                        .requestMatchers(HttpMethod.POST, "/clientes").permitAll()
                        .requestMatchers(HttpMethod.POST, "/clientes/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/clientes").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/clientes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/compras/{nome:.*}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/dev").permitAll()
                        .requestMatchers(HttpMethod.POST, "/dev").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/dev").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/dev").permitAll()

                )
                .build();
    }
}