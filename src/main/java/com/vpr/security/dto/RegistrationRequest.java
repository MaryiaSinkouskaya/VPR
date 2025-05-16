package com.vpr.security.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class RegistrationRequest {

  private String email;
  private String password;
  //  private Role role;//todo make admin able assign/unassign  roles
}
