package com.vpr.app.security.dto;

import com.vpr.app.security.enums.Role;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {

    private String email;
    private String password;
    private Role role;
}
