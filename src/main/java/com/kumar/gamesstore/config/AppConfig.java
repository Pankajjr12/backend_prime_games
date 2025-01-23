package com.kumar.gamesstore.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class AppConfig {

    // Security filter chain with CORS and JWT token validation
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(auth -> auth
                .requestMatchers("/api/**").authenticated() // All API endpoints need authentication
                .requestMatchers("/api/products/*/reviews").permitAll() // Public reviews
                .anyRequest().permitAll() // Allow other requests (can be adjusted as per your needs)
                )
                .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class) // JWT Token filter
                .csrf(csrf -> csrf.disable()) // Disable CSRF for stateless applications
                .cors(cors -> cors.configurationSource(corsConfigurationSource()));  // Apply custom CORS configuration

        return http.build();
    }

    // Custom CORS configuration
    private CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration cfg = new CorsConfiguration();
        cfg.setAllowedOrigins(Arrays.asList("https://frontend-prime-games.vercel.app", "http://localhost:3000", "https://backend-prime-games.onrender.com"));  // Allowed frontend URLs
        cfg.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));  // Allowed HTTP methods
        cfg.setAllowCredentials(true);  // Allow credentials (cookies, Authorization headers)
        cfg.setAllowedHeaders(Collections.singletonList("*"));  // Allow all headers
        cfg.setExposedHeaders(Arrays.asList("Authorization"));  // Expose Authorization header
        cfg.setMaxAge(3600L);  // Cache pre-flight response for 1 hour

        // Register the CORS configuration for all paths
        source.registerCorsConfiguration("/**", cfg);
        return source;
    }

    // Password encoder bean (e.g., for BCrypt)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // RestTemplate bean (if you need to call other services)
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
