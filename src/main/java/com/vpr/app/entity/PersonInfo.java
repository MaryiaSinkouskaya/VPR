package com.vpr.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "person_info")
public class PersonInfo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "surname")
  private String surname;

  @Column(name = "patronymic")
  private String patronymic;

  @Column(name = "birth_date")
  private Date birthDate;

  @Column(name = "phone")
  private String phone;

  @ManyToOne()
  @JoinColumn(name = "address_id")
  @JsonManagedReference(value = "address-personInfo")
  @JsonIgnore
  private Address address;

  @ManyToOne()
  @JoinColumn(name = "work_id")
  @JsonManagedReference(value = "workplace-personInfo")
  @JsonIgnore
  private Workplace workplace;

  @OneToMany(mappedBy = "personInfo", cascade = CascadeType.ALL)
  @JsonBackReference(value = "personInfo-mother")
  private List<Mother> mothers;

  @OneToMany(mappedBy = "personInfo", cascade = CascadeType.ALL)
  @JsonBackReference(value = "personInfo-doctor")
  private List<Doctor> doctors;

  @OneToMany(mappedBy = "father", cascade = CascadeType.ALL)
  @JsonBackReference(value = "personInfo-proband")
  private List<Proband> probands;
}
