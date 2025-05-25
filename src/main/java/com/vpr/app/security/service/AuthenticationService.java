package com.vpr.app.security.service;

import com.vpr.app.security.dto.*;
import com.vpr.app.security.repository.TokenRepository;
import com.vpr.app.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserConverter userConverter;

    public AuthenticationResponse registerUser(RegistrationRequest request) {
        User user = userConverter.convertRegisterRequestToUser(request);
        User savedUser = userRepository.save(user);
        String jwtToken = assignNewTokenToUser(savedUser);
        return prepareAuthenticationResponse(jwtToken);
    }

    private String assignNewTokenToUser(User user) {
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

    public AuthenticationResponse authenticateUser(AuthenticationRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        authenticationManager.authenticate(authenticationToken);
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String jwtToken = assignNewTokenToUser(user);
        return prepareAuthenticationResponse(jwtToken);
    }

    private AuthenticationResponse prepareAuthenticationResponse(String jwtToken) {
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}