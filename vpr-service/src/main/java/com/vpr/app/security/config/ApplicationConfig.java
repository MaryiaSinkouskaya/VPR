package com.vpr.app.security.config;

import com.vpr.app.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class for Spring Security application settings.
 * Provides beans for user authentication and authorization components.
 * Configures user details service, authentication provider, and password encoding.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
  private final UserRepository repository;

  /**
   * Creates a UserDetailsService bean that loads user details from the repository.
   * Searches for users by email and throws UsernameNotFoundException if not found.
   *
   * @return UserDetailsService implementation for loading user details
   */
  @Bean
  public UserDetailsService userDetailsService() {
    return username -> repository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  /**
   * Creates an AuthenticationProvider bean for handling user authentication.
   * Configures the provider with user details service and password encoder.
   *
   * @return configured DaoAuthenticationProvider
   */
  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  /**
   * Creates an AuthenticationManager bean for managing authentication processes.
   *
   * @param config the AuthenticationConfiguration to use
   * @return configured AuthenticationManager
   * @throws Exception if an error occurs during configuration
   */
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
      throws Exception {
    return config.getAuthenticationManager();
  }

  /**
   * Creates a PasswordEncoder bean for handling password encoding and verification.
   * Uses BCryptPasswordEncoder for secure password hashing.
   *
   * @return configured BCryptPasswordEncoder
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
