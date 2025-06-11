package com.vpr.app.repository;

import com.vpr.app.entity.Mother;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotherRepository extends JpaRepository<Mother, Long> {
}