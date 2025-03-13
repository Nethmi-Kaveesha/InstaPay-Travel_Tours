package com.example.InstaPay_Travel_Tours.config;

import com.example.InstaPay_Travel_Tours.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security Configuration class for InstaPay_Travel_Tours application.
 */
@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private JwtFilter jwtFilter;

    // Bean to encode passwords using BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configure authentication manager to use the custom user service
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    // Bean for authentication manager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Configure the HTTP security to manage authorization and JWT-based authentication
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for stateless sessions
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/v1/auth/authenticate",        // Public endpoint for authentication
                                "/api/v1/user/register",            // Public user registration
                                "/api/v1/user/save",                // User save (public endpoint)
                                "/api/v1/user/update",
                                "/api/v1/user/getAll",              // Get all users (public endpoint)
                                "/api/v1/auth/refreshToken",        // Public endpoint to refresh token
                                "/v3/api-docs/**",                  // Swagger documentation
                                "/swagger-ui/**",                   // Swagger UI
                                "/swagger-ui.html",                 // Swagger HTML page
                                "/api/v1/tourguide/save",           // Public endpoint for saving a tour guide
                                "/api/v1/tours/**",                 // Public tour endpoints
                                "/api/v1/tourguide/getAll",         // Public endpoint to get all tour guides
                                "/api/v1/tourguide/update",         // Public endpoint for updating a tour guide
                                "/api/v1/tourguide/delete/**",      // Public endpoint for deleting a tour guide
                                "/api/v1/tours/save",              // Public endpoint to save tours
                                "/api/v1/tours/getAll",            // Public endpoint to get all tours
                                "/api/v1/tours/edit",              // Public endpoint to edit tours
                                "/api/v1/tours/1",                 // Public endpoint for a specific tour
                                "/api/v1/payment/**",              // Public payment-related endpoints
                                "/api/v1/booking/**",              // Public booking-related endpoints
                                "/api/v1/review/save",             // Public endpoint to save reviews
                                "/api/v1/review/getAll",           // Public endpoint to get all reviews
                                "/api/v1/review/update"            // Public endpoint to update reviews
                        ).permitAll() // Allow public access to the above endpoints
                        .anyRequest().authenticated()  // Require authentication for other endpoints
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless sessions
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Add JWT filter before UsernamePasswordAuthenticationFilter
                .build();
    }
}
