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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
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
                                "/api/v1/endPath",

                                "/api/v1/img/upload",
                                "/api/v1/img/**",
                                "/images/**",
                                "/api/v1/expense/","/api/v1/expense/saveExpense","api/v1/expense/deleteExpense","api/v1/expense/updateExpense",

                                "/new/api/endpoint1",
                                "/new/api/endpoint2",


                                "/api/v1/income/saveIncome",
                                "/api/v1/income/update",
                                "/api/v1/income/delete",
                                "/api/v1/income/getAll",
                                "/api/v1/income/entries",
                                "/api/v1/income/"

                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
