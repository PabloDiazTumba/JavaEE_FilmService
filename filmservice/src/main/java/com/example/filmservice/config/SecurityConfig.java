package com.example.filmservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/register", "/login", "/css/**", "/js/**").permitAll()  // Offentliga sidor
                        .anyRequest().authenticated() // Alla andra sidor kräver autentisering
                )
                .formLogin(withDefaults())  // Enkel form login
                .logout(withDefaults());    // Enkel logout
        return http.build();
    }

    // PasswordEncoder för att använda BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // För lösenordshashning
    }
}