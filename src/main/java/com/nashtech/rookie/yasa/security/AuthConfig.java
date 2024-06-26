package com.nashtech.rookie.yasa.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AuthConfig {

    @Autowired
    @Qualifier("delegatedAuthenticationEntryPoint")
    AuthenticationEntryPoint authEntryPoint;


    private static final String[] AUTH_WHITELIST = {
            "/api/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/bus/v3/api-docs/**",
            "/configuration/security",
            "/swagger-ui/**",
            "/swagger-ui/index.html",
            "/webjars/**",
            "/swagger-ui.html"
    };

    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity,
                                             JwtTokenFilter authFilter) throws Exception {
        return httpSecurity
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .requestMatchers(HttpMethod.GET, new String[]{
                                "api/products/**",
                                "api/products/",
                                "api/categories/**",
                                "api/categories",
                                "api/ratings/**",
                                "api/ratings/",
                                 "/error"}).permitAll()
                        .requestMatchers(HttpMethod.POST, new String[]{
                                "api/ratings/**",
                                "api/ratings/",
                        }).authenticated()
                        .requestMatchers(HttpMethod.POST, new String[]{
                                "api/products/**",
                                "api/products/",
                                "api/categories/**",
                                "api/categories",
                        }).hasAuthority("admin")
                        .requestMatchers(HttpMethod.PUT, new String[]{
                                "api/products/**",
                                "api/products/",
                                "api/categories/**",
                                "api/categories",
                        }).hasAuthority("admin")
                        .requestMatchers(HttpMethod.DELETE, new String[]{
                                "api/products/**",
                                "api/products/",
                                "api/categories/**",
                                "api/categories",
                                "api/ratings/**",
                                "api/ratings/",
                        }).hasAuthority("admin")
                        .requestMatchers(HttpMethod.POST,"api/auth/**").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling((exception) -> exception.authenticationEntryPoint(authEntryPoint))
                //.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    AuthenticationManager authenticationManagerJwt (AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}