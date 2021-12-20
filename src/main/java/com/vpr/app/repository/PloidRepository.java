package com.vpr.app.repository;

import com.vpr.app.entity.Ploid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PloidRepository extends JpaRepository<Ploid, Long> {
}