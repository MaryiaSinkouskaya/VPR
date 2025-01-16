package com.vpr.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "mother")
public class Mother {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "last_menstruation_date")
  private Date lastMenstruationDate;

  @Column(name = "diagnose_date")
  private Date diagnoseDate;

  @Column(name = "girl_surname")
  private String girlSurname;

  @ManyToOne()
  @JoinColumn(name = "person_info_id")
  @JsonManagedReference(value = "personInfo-mother")
  private PersonInfo personInfo;

  @OneToMany(mappedBy = "mother", cascade = CascadeType.ALL)
  @JsonBackReference(value = "mother-proband")
  private List<Proband> probands;
}
