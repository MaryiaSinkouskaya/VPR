package com.vpr.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "workplace")
public class Work {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "job_type")
  private String jobType;

  @Column(name = "company")
  private String company;

  @OneToMany(mappedBy = "work", cascade = CascadeType.ALL)
  @JsonBackReference(value = "work-personInfo")
  private List<PersonInfo> personInfos;
}
