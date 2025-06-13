package com.vpr.app.security.config;

import static com.vpr.app.security.enums.Authority.CREATE;
import static com.vpr.app.security.enums.Authority.READ;
import static com.vpr.app.security.enums.Authority.UPDATE;
import static com.vpr.app.security.enums.Role.ADMIN;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration class for Spring Security settings.
 * Defines security rules, authentication mechanisms, and access control policies for the application.
 * Configures JWT-based authentication and role-based authorization.
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  /**
   * List of URLs that are publicly accessible without authentication.
   * Includes authentication endpoints and Swagger documentation URLs.
   */
  private static final String[] WHITE_LIST_URL = {
      "/api/auth/**",
      "/v2/api-docs",
      "/v3/api-docs",
      "/v3/api-docs/**",
      "/swagger-resources",
      "/swagger-resources/**",
      "/configuration/ui",
      "/configuration/security",
      "/swagger-ui/**",
      "/webjars/**",
      "/swagger-ui.html"
  };

  /**
   * Base URL template for API endpoints requiring authentication.
   */
  private static final String URL_TEMPLATE = "/api/**";

  /**
   * URL template for user-specific endpoints requiring admin role.
   */
  private static final String URL_TEMPLATE_USER = "/api/user";

  /**
   * URL template for user management endpoints requiring admin role.
   */
  private static final String URL_TEMPLATE_USERS = "/api/user/**";

  private final JwtFilter jwtFilter;

  /**
   * Configures the security filter chain for the application.
   * Sets up:
   * - CSRF protection (disabled for API)
   * - URL-based authorization rules
   * - Session management (stateless)
   * - JWT filter integration
   *
   * @param http the HttpSecurity object to configure
   * @return the configured SecurityFilterChain
   * @throws Exception if an error occurs during configuration
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(request ->
            request.requestMatchers(WHITE_LIST_URL)
                .permitAll()
                .requestMatchers(GET, URL_TEMPLATE).hasAuthority(READ.name())
                .requestMatchers(POST, URL_TEMPLATE).hasAuthority(CREATE.name())
                .requestMatchers(PUT, URL_TEMPLATE).hasAuthority(UPDATE.name())
                .requestMatchers(DELETE, URL_TEMPLATE).hasAuthority(DELETE.name())
                .requestMatchers(GET, URL_TEMPLATE_USER).hasRole(ADMIN.name())
                .requestMatchers(POST, URL_TEMPLATE_USERS).hasRole(ADMIN.name())
                .requestMatchers(PUT, URL_TEMPLATE_USERS).hasRole(ADMIN.name())
                .requestMatchers(DELETE, URL_TEMPLATE_USERS).hasRole(ADMIN.name())
                .anyRequest().authenticated()
        )
        .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}
