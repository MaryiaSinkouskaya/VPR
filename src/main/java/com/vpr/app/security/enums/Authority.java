package com.vpr.app.security.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@RequiredArgsConstructor
public enum Authority {
  CREATE(new SimpleGrantedAuthority("CREATE")),
  READ(new SimpleGrantedAuthority("READ")),
  UPDATE(new SimpleGrantedAuthority("UPDATE")),
  DELETE(new SimpleGrantedAuthority("DELETE"));

  @Getter
  private final GrantedAuthority authority;
}
