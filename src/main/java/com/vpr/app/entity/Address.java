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
