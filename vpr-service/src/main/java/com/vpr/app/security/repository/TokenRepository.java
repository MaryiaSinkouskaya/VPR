package com.vpr.app.security.repository;

import com.vpr.app.security.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

  Optional<Token> findByToken(String token);

}
