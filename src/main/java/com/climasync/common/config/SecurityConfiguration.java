package com.climasync.common.config;

import com.climasync.common.jwt.JwtAuthenticationFilter;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> {
                    csrf.disable();
                })

                .authorizeRequests(auth -> {
                    auth
//                            .requestMatchers("/api/weather/current").hasAnyAuthority("USER", "ADMIN")
//                            .anyRequest()
                            .requestMatchers("/api/weather/current").hasAnyRole("ADMIN", "USER")
                            .requestMatchers("/api/weather/current").hasAnyAuthority("ADMIN", "USER")
                            .anyRequest()
                            .permitAll();
                })
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
                                .authenticationProvider(authenticationProvider)
                                        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        http.cors().disable();
        http.headers().frameOptions().disable();
        http.httpBasic();

        return http.build();
    }
}
