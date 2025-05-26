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

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

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
  private static final String URL_TEMPLATE = "/api/**";
  private static final String URL_TEMPLATE_USER = "/api/user";
  private static final String URL_TEMPLATE_USERS = "/api/user/**";

  private final JwtFilter jwtFilter;

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
