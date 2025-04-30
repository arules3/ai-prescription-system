package com.aihealth.prescription_system.config;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for API use (enable later as needed)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/doctors/register").permitAll() // Public registration
                        .requestMatchers("/api/v1/doctors/**").permitAll()
                        .anyRequest().authenticated() // All other requests require auth
                )
                .httpBasic(Customizer.withDefaults()); // or use formLogin(), or add JWT later

        return http.build();
    }



}
