package com.employee.EmployeeDatabaseManagement.EDM.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class MySecurityConfig {
//    private final JwtAuthenticationFilter jwtAuthFilter;
////    private UserDetailsService userDetailsService;
//
//    @Autowired(required = true)
//    public MySecurityConfig(JwtAuthenticationFilter jwtAuthFilter) {
//        this.jwtAuthFilter = jwtAuthFilter;}

//    @Bean
//    public AuthenticationManager customAuthenticationManager(HttpSecurity httpSecurity) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject
//                (AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.userDetailsService(userDetailsService)
//                .passwordEncoder(bCryptPasswordEncoder());
//        return authenticationManagerBuilder.build();
//    }
    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf((csrf) -> csrf.disable())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).exceptionHandling(exception -> {
//                                   exception.authenticationEntryPoint((request, response, authenticationException) -> {
//                                   response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authenticationException.getMessage());});})
//                        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//                .authorizeHttpRequests(authRequest -> authRequest.requestMatchers("/api/users/login", "/api/users/register", "/api/users/process_register", "/api/users/processLogin").permitAll().anyRequest().authenticated())
//                .formLogin(form->form.loginPage("/app/login").usernameParameter("username").passwordParameter("password").permitAll());
//
//
//        return http.build();
//    }

}
