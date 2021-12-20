package com.vpr.app.repository;

import com.vpr.app.entity.Abnormality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbnormalityRepository extends JpaRepository<Abnormality, Long> {
}