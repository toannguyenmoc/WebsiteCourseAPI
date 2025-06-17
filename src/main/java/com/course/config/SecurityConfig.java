package com.course.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.course.filter.JwtAuthFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(@Lazy JwtAuthFilter jwtAuthFilter,
                          UserDetailsService userDetailsService,
                          PasswordEncoder passwordEncoder) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
     // Disable CSRF (not needed for stateless JWT)
        .csrf(csrf -> csrf.disable())

        // Configure endpoint authorization
        .authorizeHttpRequests(auth -> auth
        		
        		//Tất cả endpoint đều không cầu authorize
        		.anyRequest().permitAll()
        		
        		
            // Public endpoints
//            .requestMatchers( "/api/account/addNewUser", "/api/account/generateToken").permitAll()
            
            // Role-based endpoints
//            .requestMatchers("/auth/user/**").hasAuthority("NORMAL_USER")
//            .requestMatchers("/auth/admin/**").hasAuthority("ADMIN")
            
            // All other endpoints require authentication
//            .anyRequest().authenticated()
        )

        // Stateless session (required for JWT)
        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        
        // Set custom authentication provider
        .authenticationProvider(authenticationProvider())
        
        // Add JWT filter before Spring Security's default filter
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder); 
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
