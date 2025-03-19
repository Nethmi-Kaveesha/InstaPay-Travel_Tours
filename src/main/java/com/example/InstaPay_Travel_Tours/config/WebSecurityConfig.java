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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
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
                .csrf(csrf -> csrf.disable()) // Disable CSRF for stateless sessions
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                // Existing public endpoints
                                "/api/v1/auth/authenticate",
                                "/api/v1/user/register",
                                "/api/v1/user/save",
                                "/api/v1/user/update",
                                "/api/v1/user/getAll",
                                "/api/v1/auth/refreshToken",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/api/v1/tourguide/save",
                                "/api/v1/tours/**",
                                "/api/v1/tourguide/getAll",
                                "/api/v1/tourguide/update",
                                "/api/v1/tourguide/delete/**",
                                "/api/v1/tours/save",
                                "/api/v1/tours/getAll",
                                "/api/v1/reviews",
                                "/api/v1/tours/edit",
                                "/api/v1/tours/1",
                                "/api/v1/payment/**",
                                "/api/v1/booking/**",
                                "/api/v1/reviews/save",
                                "/api/v1/reviews/getAll",
                                "/api/v1/reviews/update",
                                "/api/v1/reviews/delete/**",
                                "/api/v1/reviews/getById/**",
                                "/api/v1/tourschedule/save",
                                "/api/v1/tourschedule/getAll",
                                "/api/v1/tourschedule/update",
                                "/api/v1/tourschedule/delete/**",
                                "/addExpense",
                                "/api/v1/expense/save",
                                "/api/v1/endPath",  // New endpoint added

                                // Image-related endpoints
                                "/api/v1/img/upload",   // Image upload endpoint
                                "/api/v1/img/**",        // Any other image-related endpoints
                                "/images/**",
                                "/api/v1/expense/","/api/v1/expense/saveExpense","api/v1/expense/deleteExpense","api/v1/expense/updateExpense", // Expense API Endpoints

                                // Add new public endpoints here
                                "/new/api/endpoint1",    // Add new public endpoint 1
                                "/new/api/endpoint2",    // Add new public endpoint 2

                                // Income API Endpoints
                                "/api/v1/income/saveIncome",   // Save income endpoint
                                "/api/v1/income/update", // Update income endpoint
                                "/api/v1/income/delete", // Delete income endpoint
                                "/api/v1/income/getAll", // Get all incomes endpoint
                                "/api/v1/income/entries", // Entries of income
                                "/api/v1/income/"        // General income endpoint

                        ).permitAll() // Allow public access to the above endpoints
                        .anyRequest().authenticated() // Require authentication for other endpoints
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless sessions
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Add JWT filter before UsernamePasswordAuthenticationFilter
                .build();
    }
}
