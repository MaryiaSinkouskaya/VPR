package com.vpr.app.repository;

import com.vpr.app.entity.PersonInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonInfoRepository extends JpaRepository<PersonInfo, Long> {
}