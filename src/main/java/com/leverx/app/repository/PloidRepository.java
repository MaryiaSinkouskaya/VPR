package com.leverx.app.repository;

import com.leverx.app.entity.Ploid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PloidRepository extends JpaRepository<Ploid, Long> {
}