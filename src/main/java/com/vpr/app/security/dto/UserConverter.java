package com.vpr.app.security.dto;

import com.vpr.app.security.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserConverter {

    private final PasswordEncoder passwordEncoder;

    public User convertRegisterRequestToUser(RegistrationRequest registrationRequest) {
        return User.builder()
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
//              .role(request.getRole())
                .role(Role.VIEWER)//todo make only admin able to create other admin
                .build();
    }
}
