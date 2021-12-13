package com.leverx.app.repository;

import com.leverx.app.entity.Abnormality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbnormalityRepository extends JpaRepository<Abnormality, Long> {
}