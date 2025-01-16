package com.vpr.app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "doctor")
public class Doctor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "speciality")
  private String speciality;

  @ManyToOne()
  @JoinColumn(name = "person_info_id")
  @JsonManagedReference(value = "personInfo-doctor")
  private PersonInfo personInfo;
}
