package com.vpr.app.repository;

import com.vpr.app.entity.Workplace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkplaceRepository extends JpaRepository<Workplace, Long> {
}