package com.vpr.app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctor")
public class Doctor {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_seq_gen")
  @SequenceGenerator(name = "doctor_seq_gen",
      sequenceName = "doctor_id_seq",
      allocationSize = 1)
  @Column(name = "id")
  private long id;

  @Column(name = "speciality")
  private String speciality;

  @ManyToOne(cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
  })
  @JoinColumn(name = "person_info_id")
  @JsonManagedReference(value = "personInfo-doctor")
  private PersonInfo personInfo;
}
