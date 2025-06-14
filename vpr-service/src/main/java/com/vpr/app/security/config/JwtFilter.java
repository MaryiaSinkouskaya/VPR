package com.vpr.app.security.config;

import com.vpr.app.security.repository.TokenRepository;
import com.vpr.app.security.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * JWT Authentication Filter that processes JWT tokens in incoming requests.
 * Extends OncePerRequestFilter to ensure the filter is executed once per request.
 * Validates JWT tokens and sets up Spring Security context for authenticated users.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

  private static final String BEARER = "Bearer ";
  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;
  private final TokenRepository tokenRepository;
  private final SecurityProperties securityProperties;

  /**
   * Processes each HTTP request to validate JWT tokens and set up authentication.
   * The filter:
   * 1. Skips authentication for /api/auth endpoints
   * 2. Extracts and validates the JWT token from the Authorization header
   * 3. Verifies token validity and user existence
   * 4. Sets up Spring Security context for authenticated users
   *
   * @param request the HTTP request to process
   * @param response the HTTP response
   * @param filterChain the filter chain to continue processing
   * @throws ServletException if a servlet error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain) throws ServletException, IOException {

    log.info("JwtFilter activated: {}", request.getRequestURI());

    String servletPath = request.getServletPath();
    final String authHeader = request.getHeader(AUTHORIZATION);

    if (authHeader == null || !authHeader.startsWith(BEARER) ||
        securityProperties.getUrls().stream().anyMatch(servletPath::startsWith)) {
      filterChain.doFilter(request, response);
      return;
    }

    final String jwt = authHeader.substring(7);
    final String userEmail = jwtService.extractUserEmail(jwt);

    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
      boolean isTokenValid = tokenRepository.findByToken(jwt)
          .map(t -> !t.isExpired() && !t.isRevoked())
          .orElse(false);

      if (jwtService.isValid(jwt, userDetails) && isTokenValid) {
        UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    filterChain.doFilter(request, response);
  }
}
