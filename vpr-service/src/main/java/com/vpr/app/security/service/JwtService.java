package com.vpr.app.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.impl.DefaultJwtParserBuilder;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Service responsible for JWT token operations including generation, validation, and parsing.
 * Handles token creation, expiration checks, and user information extraction from tokens.
 */
@Service
public class JwtService {

  @Value("${JWT_SECRET}")
  private String secretKey;
  private static final long EXPIRATION_TIME_MS = 1000 * 60 * 15; // 15 minutes
  private static final String ROLES = "roles";

  /**
   * Generates a JWT token for the given user details.
   *
   * @param userDetails the user details to include in the token
   * @return the generated JWT token
   */
  public String generateToken(UserDetails userDetails) {
    return Jwts.builder()
        .subject(userDetails.getUsername())
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS))
        .id(UUID.randomUUID().toString())
        .claims(Map.of(ROLES, userDetails.getAuthorities()))
        .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SIG.HS256)
        .compact();
  }

  /**
   * Extracts the user email from a JWT token.
   *
   * @param token the JWT token to extract the email from
   * @return the user email from the token
   */
  public String extractUserEmail(String token) {
    return getClaims(token).getSubject();
  }

  /**
   * Validates if a JWT token is valid for the given user details.
   *
   * @param token the JWT token to validate
   * @param userDetails the user details to validate against
   * @return true if the token is valid, false otherwise
   */
  public boolean isValid(String token, UserDetails userDetails) {
    final String userEmail = extractUserEmail(token);
    return userEmail.equals(userDetails.getUsername()) && !isExpired(token);
  }

  /**
   * Checks if a JWT token has expired.
   *
   * @param token the JWT token to check
   * @return true if the token has expired, false otherwise
   */
  private boolean isExpired(String token) {
    return getClaims(token).getExpiration().before(new Date());
  }

  /**
   * Extracts and parses the claims from a JWT token.
   *
   * @param token the JWT token to parse
   * @return the claims from the token
   */
  private Claims getClaims(String token) {
    return new DefaultJwtParserBuilder()
        .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }
}
