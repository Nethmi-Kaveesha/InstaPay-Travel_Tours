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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/v1/auth/authenticate",
                                "/api/v1/auth/refreshToken",
                                "/api/v1/user/register",
                                "/api/v1/user/save",
                                "/api/v1/user/update",
                                "/api/v1/user/getAll",
                                "/api/v1/user/delete/**",  // ✅ DELETE User endpoint

                                "/api/v1/tourguide/save",
                                "/api/v1/tourguide/getAll",
                                "/api/v1/tourguide/update",
                                "/api/v1/tourguide/delete/**",  // ✅ DELETE Tour Guide endpoint

                                "/chat/**",              // ✅ WebSocket endpoint for real-time chat
                                "/chat",                 // ✅ WebSocket endpoint for real-time chat
                                "/api/v1/chat/sendMessage",  // ✅ Chat endpoint for sending messages
                                "/api/v1/chat/getMessages", // ✅ Chat endpoint for getting messages
                                "/api/v1/chat/history",     // ✅ Chat endpoint for message history
                                "/api/v1/chat/history",
                                "/api/v1/tours/**",
                                "/api/v1/tours/save",
                                "/api/v1/tours/getAll",
                                "/api/v1/tours/edit",
                                "/api/v1/tours/1",
                                "/api/v1/tours/uploadImage",

                                "/api/v1/reviews", // ✅ Reviews endpoint for fetching reviews
                                "/api/v1/reviews/save", // ✅ Reviews endpoint for saving new review
                                "/api/v1/reviews/getAll", // ✅ Reviews endpoint for getting all reviews
                                "/api/v1/reviews/update", // ✅ Reviews endpoint for updating review
                                "/api/v1/reviews/delete/**",  // ✅ DELETE Review endpoint
                                "/api/v1/reviews/getById/**", // ✅ Reviews endpoint for fetching review by ID

                                "/api/v1/tourschedule/save",
                                "/api/v1/tourschedule/getAll",
                                "/api/v1/tourschedule/update",
                                "/api/v1/tourschedule/delete/**",  // ✅ DELETE Tour Schedule endpoint

                                "/api/v1/payment/**",
                                "/api/v1/booking/**",

                                "/api/v1/expense/save",
                                "/api/v1/expense/",
                                "/api/v1/expense/saveExpense",
                                "/api/v1/expense/updateExpense",
                                "/api/v1/expense/deleteExpense/**",  // ✅ DELETE Expense endpoint

                                "/api/v1/income/saveIncome",
                                "/api/v1/income/update",
                                "/api/v1/income/getAll",
                                "/api/v1/income/entries",
                                "/api/v1/income/delete/**",  // ✅ DELETE Income endpoint

                                "/api/v1/img/upload",
                                "/api/v1/img/**",
                                "/images/**",

                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",


                                "/new/api/endpoint1", // ✅ New API endpoint 1
                                "/new/api/endpoint2", // ✅ New API endpoint 2
                                "/new/api/endpoint3", // ✅ New API endpoint 3

                                "/another/api/endpoint1", // ✅ New API endpoint for another functionality
                                "/another/api/endpoint2"  // ✅ Another new endpoint
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
