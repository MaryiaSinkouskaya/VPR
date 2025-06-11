package com.vpr.app.security.controller;

import com.vpr.app.security.dto.request.AuthenticationRequest;
import com.vpr.app.security.dto.request.RegistrationRequest;
import com.vpr.app.security.dto.response.AuthenticationResponse;
import com.vpr.app.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(@RequestBody RegistrationRequest request) {
    return ResponseEntity.ok(authenticationService.registerUser(request));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(authenticationService.authenticateUser(request));
  }
}
