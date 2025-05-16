package com.vpr.security.repository;

import com.vpr.security.dto.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

  Optional<Token> findByToken(String token);

}
