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
@Table(name = "abnormality")
public class Abnormality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

//    @OneToMany(mappedBy = "abnormality", cascade = CascadeType.ALL)
//    @JsonBackReference(value = "abnormality-proband")
//    private List<Proband> probands;
}
