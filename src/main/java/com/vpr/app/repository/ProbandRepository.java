package com.vpr.app.repository;

import com.vpr.app.entity.Proband;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProbandRepository extends JpaRepository<Proband, Long> {
    @Query(value = "select count(*) from proband where " +
            "gender_id = (select id from gender where gender.gender = :gender)",
            nativeQuery = true)
    int countByGender(@Param("gender") String gender);


    @Query(value = "select count(*) from proband",
            nativeQuery = true)
    int countProbands();

    @Query(value = "select count(*) from prob_d",
            nativeQuery = true)
    int countDead();


    @Query(value = "select count(*) from proband where extract (year from birth_date) = :year",
            nativeQuery = true)
    int countProbandsByYear(@Param("year") int year);

    @Query(value = "select count(*) from proband where extract (year from birth_date) = :year " +
            "and id = (select proband_id from prob_d)",
            nativeQuery = true)
    int countDeadByYear(@Param("year") int year);

}