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
import java.util.List;

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

    @ManyToOne()
    @JoinColumn(name = "town_id")
    @JsonManagedReference(value = "town-address")
    private Town town;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    @JsonBackReference(value = "address-personInfo")
    private List<PersonInfo> personInfos;
}
