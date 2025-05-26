package com.vpr.app.security.service;

import com.vpr.app.exceptions.InvalidCredentialsException;
import com.vpr.app.exceptions.UserAlreadyExistsException;
import com.vpr.app.security.dto.request.AuthenticationRequest;
import com.vpr.app.security.dto.request.RegistrationRequest;
import com.vpr.app.security.dto.response.AuthenticationResponse;
import com.vpr.app.security.entity.Token;
import com.vpr.app.security.entity.User;
import com.vpr.app.security.repository.TokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private static final String CHECK_CREDENTIALS =
      "Invalid email or password. Please, check credentials.";
  private static final String ALREADY_REGISTERED = "Email is already registered: ";

  private final AuthenticationManager authenticationManager;
  private final UserService userService;
  private final JwtService jwtService;
  private final TokenRepository tokenRepository;

  @Transactional
  public AuthenticationResponse registerUser(RegistrationRequest request) {
    if (userService.isUserExistsByEmail(request.getEmail())) {
      throw new UserAlreadyExistsException(ALREADY_REGISTERED + request.getEmail());
    }

    User savedUser = userService.createViewerUser(request);
    String jwtToken = generateTokenForUser(savedUser);
    return buildAuthResponse(jwtToken);
  }

  @Transactional
  public AuthenticationResponse authenticateUser(AuthenticationRequest request) {
    try {
      UsernamePasswordAuthenticationToken authenticationToken =
          new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
      authenticationManager.authenticate(authenticationToken);
    } catch (BadCredentialsException ex) {
      throw new InvalidCredentialsException(CHECK_CREDENTIALS);
    }

    User user = userService.findByEmail(request.getEmail());
    String jwtToken = generateTokenForUser(user);
    return buildAuthResponse(jwtToken);
  }

  private String generateTokenForUser(User user) {
    String jwtToken = jwtService.generateToken(user);
    saveToken(user, jwtToken);
    return jwtToken;
  }

  private void saveToken(User user, String jwtToken) {
    Token token = Token.builder()
        .user(user)
        .token(jwtToken)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private AuthenticationResponse buildAuthResponse(String jwtToken) {
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
        .build();
  }
}