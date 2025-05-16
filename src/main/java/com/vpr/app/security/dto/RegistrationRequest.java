package com.vpr.app.security.dto;

import com.vpr.app.security.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {

  private String email;
  private String password;
  private Role role;//todo make admin able assign/unassign  roles
}
