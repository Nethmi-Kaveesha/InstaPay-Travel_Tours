package com.example.InstaPay_Travel_Tours.config;

import com.example.InstaPay_Travel_Tours.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

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
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:63342"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/user/getAll",
                                "/api/random-destination",
                                "/api/history",
                                "/reviews/getAll",
                                "/api/articles",
                                "/reviews/save",
                                "/reviews/update",
                                "/reviews/delete/**",
                                "/api/payments/send-email",
                                "/api/payments/create-payment-intent",
                                "/api/payments/update-payment-status",
                                "/api/v1/booking/place",
                                "/api/v1",
                                "/api/v1/tours/",
                                "/api/v1/dashboard",
                                "/api/v1/dashboard/stats",
                                "/api/v1/dashboard/reports",
                                "/email/send",
                                "/email/sendHtml",
                                "/api/send-email",
                                "/api/weather/**",
                                "/api/v1/auth/authenticate",
                                "/api/v1/auth/refreshToken",
                                "/api/v1/user/register",
                                "/api/v1/user/save",
                                "/api/v1/user/update",
                                "/api/v1/user/getAll",
                                "/api/v1/user/delete/**",
                                "/api/v1/tourguide/save",
                                "api/v1/tourguide/getAll",
                                "/api/v1/tourguide/update",
                                "/api/v1/tourguide/delete/**",
                                "/chat/**",
                                "/api/v1/chat/sendMessage",
                                "/api/v1/chat/getMessages",
                                "/api/v1/chat/history",
                                "/api/v1/tours/save",
                                "/api/v1/tours/getAll",
                                "/api/v1/tours/edit",
                                "/api/v1/tours/delete/**",
                                "/api/v1/tours/update",
                                "/api/v1/tours/1",
                                "/api/v1/tours/uploadImage",
                                "/api/v1/reviews",
                                "/api/v1/reviews/save",
                                "/api/v1/reviews/getAll",
                                "/api/v1/reviews/update",
                                "/api/v1/reviews/delete/**",
                                "/api/v1/reviews/getById/**",
                                "/api/v1/tourschedule/save",
                                "/api/v1/tourschedule/getAll",
                                "/api/v1/tourschedule/update",
                                "/api/v1/tourschedule/delete/**",
                                "/api/v1/payment/**",
                                "/api/v1/booking/place",
                                "/api/v1/booking/view",
                                "/api/v1/expense/save",
                                "/api/v1/expense/",
                                "/api/v1/expense/saveExpense",
                                "/api/v1/expense/updateExpense/**",
                                "/api/v1/expense/deleteExpense/**",
                                "/api/v1/income/",
                                "/api/v1/income/saveIncome",
                                "/api/v1/income/updateIncome/**",
                                "/api/v1/income/getAll",
                                "/api/v1/income/entries",
                                "/api/v1/income/deleteIncome/**",
                                "/reviews",
                                "/api/v1/tours/search",
                                "/api/v1/img/upload",
                                "/api/v1/img/**",
                                "/images/**",
                                "/api/v1/tours/search/**",
                                "api/v1/tours/book/**",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "https://api.openai.com/v1/chat/completions",
                                "/api/v1/gallery/all",
                                "/api/v1/gallery/upload",
                                "/api/v1/gallery/featured",
                                "/api/v1/gallery/filter",
                                "/api/v1/gallery/delete/**",
                                "/api/events",
                                "api/v1/booking/view",
                                "/api/transfer/create",
                                "/api/payments/view",
                                "/api/payments/payment-details/**",
                                "/payhere/notify",
                                "/api/payhere/create",
                                "/payhere/initiate-payment",
                                "/payment/confirm",
                                "api/v1/booking/latest-booking-id"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
