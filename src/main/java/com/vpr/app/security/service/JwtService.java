package com.vpr.app.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.impl.DefaultJwtParserBuilder;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;

@Service
public class JwtService {

  private final SecretKey secretKey =//todo update key storage
      Keys.hmacShaKeyFor("your-super-secure-256-bit-secret-key-which-is-long-enough".getBytes());

  private static final long EXPIRATION_TIME_MS = 1000 * 60 * 15; // 15 minutes

  public String generateToken(UserDetails userDetails) {
    return Jwts.builder()
        .subject(userDetails.getUsername())
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS))
        .claims(Map.of("roles", userDetails.getAuthorities()))
        .signWith(secretKey, SIG.HS256)
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
        .setSigningKey(secretKey)
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }
}
