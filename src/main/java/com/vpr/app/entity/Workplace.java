package com.vpr.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "workplace")
public class Workplace {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "work_seq_gen")
  @SequenceGenerator(name = "work_seq_gen",
      sequenceName = "work_id_seq",
      allocationSize = 1)
  @Column(name = "id")
  private long id;

  @Column(name = "job_type")
  private String jobType;

  @Column(name = "company")
  private String company;

  @OneToMany(mappedBy = "workplace", cascade = CascadeType.PERSIST)
  @JsonIgnore
  private List<PersonInfo> personInfos;
}//todo DTO, exception handling for delete action, response entities
