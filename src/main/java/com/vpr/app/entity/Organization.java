package com.vpr.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "organization")
public class Organization {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "org_seq_gen")
  @SequenceGenerator(name = "org_seq_gen",
      sequenceName = "org_id_seq",
      allocationSize = 1)
  @Column(name = "id")
  private long id;

  @Column(name = "number")
  private int number;

  @Column(name = "name")
  private String name;

  @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
  @JsonBackReference(value = "organization-proband")
  private List<Proband> probands;
}
