package com.climasync.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> {
                    csrf.disable();
                })

                .authorizeRequests(auth -> {
                    auth
                            .requestMatchers("/api/weather/current").hasAnyAuthority("USER", "ADMIN")
                            .anyRequest()
                            .permitAll();
                })
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.cors().disable();
        http.headers().frameOptions().disable();
        http.httpBasic();

        return http.build();
    }
}
