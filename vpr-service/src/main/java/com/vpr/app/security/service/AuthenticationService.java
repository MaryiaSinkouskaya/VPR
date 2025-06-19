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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * Service responsible for handling user authentication and registration operations.
 * Provides functionality for user registration, authentication, and JWT token management.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

  private static final String CHECK_CREDENTIALS =
      "Invalid email or password. Please, check credentials.";
  private static final String ALREADY_REGISTERED = "Email is already registered: ";

  private final AuthenticationManager authenticationManager;
  private final UserService userService;
  private final JwtService jwtService;
  private final TokenRepository tokenRepository;

  /**
   * Registers a new user in the system.
   *
   * @param request the registration request containing user details
   * @return authentication response containing the JWT token
   * @throws UserAlreadyExistsException if a user with the given email already exists
   */
  @Transactional
  public AuthenticationResponse registerUser(RegistrationRequest request) {
    if (userService.isUserExistsByEmail(request.getEmail())) {
      throw new UserAlreadyExistsException(ALREADY_REGISTERED + request.getEmail());
    }

    User savedUser = userService.createViewerUser(request);
    String jwtToken = generateTokenForUser(savedUser);
    return buildAuthResponse(jwtToken);
  }

  /**
   * Authenticates a user and generates a JWT token.
   *
   * @param request the authentication request containing user credentials
   * @return authentication response containing the JWT token
   * @throws InvalidCredentialsException if the provided credentials are invalid
   */
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

  /**
   * Generates a JWT token for a user and saves it in the database.
   *
   * @param user the user for whom to generate the token
   * @return the generated JWT token
   */
  private String generateTokenForUser(User user) {
    String jwtToken = jwtService.generateToken(user);
    saveToken(user, jwtToken);
    return jwtToken;
  }

  /**
   * Saves a JWT token in the database.
   *
   * @param user the user associated with the token
   * @param jwtToken the JWT token to save
   */
  private void saveToken(User user, String jwtToken) {
    Token token = Token.builder()
        .user(user)
        .token(jwtToken)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  /**
   * Builds an authentication response containing the JWT token.
   *
   * @param jwtToken the JWT token to include in the response
   * @return the authentication response
   */
  private AuthenticationResponse buildAuthResponse(String jwtToken) {
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
        .build();
  }
}