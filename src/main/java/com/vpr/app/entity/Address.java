package com.vpr.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "address")
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
