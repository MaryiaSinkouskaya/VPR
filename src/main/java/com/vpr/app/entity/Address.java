package com.vpr.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "address")
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq_gen")
  @SequenceGenerator(name = "address_seq_gen",
          sequenceName = "address_id_seq",
          allocationSize = 1)
  @Column(name = "id")
  private long id;

  @Column(name = "street")
  private String street;

  @Column(name = "building")
  private int building;

  @Column(name = "apartment")
  private int apartment;

  @Column(name = "town")
  private String town;

  @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
  @JsonBackReference(value = "address-personInfo")
  private List<PersonInfo> personInfos;
}
