package com.vpr.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mother")
public class Mother {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mother_seq_gen")
  @SequenceGenerator(name = "mother_seq_gen",
      sequenceName = "mother_id_seq",
      allocationSize = 1)
  @Column(name = "id")
  private long id;

  @Column(name = "last_menstruation_date")
  private LocalDate lastMenstruationDate;

  @Column(name = "diagnose_date")
  private LocalDate diagnoseDate;

  @Column(name = "girl_surname")
  private String girlSurname;

  @ManyToOne(cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
  })
  @JoinColumn(name = "person_info_id")
  @JsonManagedReference(value = "personInfo-mother")
  private PersonInfo personInfo;

  @OneToMany(mappedBy = "mother", cascade = CascadeType.PERSIST)
  @JsonIgnore
  private List<Proband> probands;
}
