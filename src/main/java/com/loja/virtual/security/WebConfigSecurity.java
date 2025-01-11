package com.loja.virtual.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.http.HttpSessionListener;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebConfigSecurity implements HttpSessionListener {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/salvarAcesso", "/deleteAcesso").permitAll()
                .requestMatchers(HttpMethod.POST, "/salvarAcesso", "/deleteAcesso").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/deleteAcesso").permitAll()
                .anyRequest().authenticated()
            )
            .csrf(csrf -> csrf.disable()); // Desabilita CSRF se necessário

        return http.build();
    }
}
