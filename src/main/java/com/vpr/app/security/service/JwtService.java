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

@Service
public class JwtService {

  @Value("${JWT_SECRET}")
  private String secretKey;
  private static final long EXPIRATION_TIME_MS = 1000 * 60 * 15; // 15 minutes
  private static final String ROLES = "roles";

  public String generateToken(UserDetails userDetails) {
    return Jwts.builder()
        .subject(userDetails.getUsername())
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS))
        .claims(Map.of(ROLES, userDetails.getAuthorities()))
        .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SIG.HS256)
        .compact();
  }

  public String extractUserEmail(String token) {
    return getClaims(token).getSubject();
  }

  public boolean isValid(String token, UserDetails userDetails) {
    final String userEmail = extractUserEmail(token);
    return userEmail.equals(userDetails.getUsername()) && !isExpired(token);
  }

  private boolean isExpired(String token) {
    return getClaims(token).getExpiration().before(new Date());
  }

  private Claims getClaims(String token) {
    return new DefaultJwtParserBuilder()
        .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }
}
