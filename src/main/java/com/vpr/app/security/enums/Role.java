package com.vpr.app.security.enums;

import static com.vpr.app.security.enums.Authority.CREATE;
import static com.vpr.app.security.enums.Authority.DELETE;
import static com.vpr.app.security.enums.Authority.READ;
import static com.vpr.app.security.enums.Authority.UPDATE;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;

@RequiredArgsConstructor
public enum Role {
  ADMIN(List.of(CREATE.getAuthority(), READ.getAuthority(), UPDATE.getAuthority(),
      DELETE.getAuthority(), new SimpleGrantedAuthority("ROLE_ADMIN"))),
  DOCTOR(List.of(CREATE.getAuthority(), READ.getAuthority(), UPDATE.getAuthority(),
      new SimpleGrantedAuthority("ROLE_DOCTOR"))),
  VIEWER(List.of(READ.getAuthority(), new SimpleGrantedAuthority("ROLE_VIEWER")));

  @Getter
  private final List<GrantedAuthority> authorities;
}
