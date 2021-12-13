package com.leverx.app.repository;

import com.leverx.app.entity.PersonInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonInfoRepository extends JpaRepository<PersonInfo, Long> {
}