package com.leverx.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Getter
@Setter
@Table(name = "organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "number")
    private int number;

    @Column(name = "name")
    private String name;

//    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
//    @JsonBackReference(value = "organization-proband")
//    private List<Proband> probands;
}
