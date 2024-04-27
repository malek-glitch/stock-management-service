package com.crocostaud.stockmanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disables CSRF protection, common in stateless REST APIs.
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(new AntPathRequestMatcher("/user")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/product")).permitAll()// Allow POST requests to /user without authentication
                        .anyRequest().authenticated() // Ensures all requests are authenticated.
                )
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll) // Enables HTTP Basic Authentication with default settings.
                .httpBasic(withDefaults())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Configures session management to be stateless.
                );

        return http.build(); // Builds and returns the SecurityFilterChain.
    }

    @Bean
    UserDetailsService userDetailsService() {

        UserDetails user1 = User
                .withUsername("user")
                .password("$2a$10$xi4yWAO1p.3H65JKtc7etuk1b5/yYM4/F3kKdeKLm.4/4L92MFHSK") // password
                .roles("USER")
                .build();

        UserDetails user2 = User
                .withUsername("admin")
                .password("$2a$10$xi4yWAO1p.3H65JKtc7etuk1b5/yYM4/F3kKdeKLm.4/4L92MFHSK")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}