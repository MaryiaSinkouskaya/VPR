package com.vpr.app.repository;

import com.vpr.app.entity.Proband;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProbandRepository extends JpaRepository<Proband, Long> {
  @Query(value = "select count(*) from proband where gender = :gender",
      nativeQuery = true)
  int countByGender(@Param("gender") String gender);

}