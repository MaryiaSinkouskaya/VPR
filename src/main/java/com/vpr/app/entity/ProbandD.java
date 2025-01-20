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
import lombok.*;

import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prob_d")
public class ProbandD {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prob_d_seq_gen")
  @SequenceGenerator(name = "prob_d_seq_gen",
      sequenceName = "prob_d_id_seq",
      allocationSize = 1)
  @Column(name = "id")
  private long id;

  @Column(name = "death_date")
  private Date deathDate;

  @ManyToOne(cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
  })
  @JoinColumn(name = "proband_id")
  @JsonManagedReference(value = "proband-probD")
  private Proband proband;
}
