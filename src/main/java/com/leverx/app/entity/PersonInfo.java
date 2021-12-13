package com.leverx.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;


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
    private Address address;

    @ManyToOne()
    @JoinColumn(name = "work_id")
    @JsonManagedReference(value = "work-personInfo")
    private Work work;

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
