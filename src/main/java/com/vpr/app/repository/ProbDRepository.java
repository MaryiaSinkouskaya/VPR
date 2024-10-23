package com.vpr.app.repository;

import com.vpr.app.entity.ProbandDeath;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProbDRepository extends JpaRepository<ProbandDeath, Long> {
}