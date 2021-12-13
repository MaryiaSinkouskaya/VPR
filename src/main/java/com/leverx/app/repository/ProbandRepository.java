package com.leverx.app.repository;

import com.leverx.app.entity.Proband;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProbandRepository extends JpaRepository<Proband, Long> {
}