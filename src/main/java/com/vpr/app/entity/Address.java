package com.vpr.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq_gen")
    @SequenceGenerator(name = "address_seq_gen",
            sequenceName = "address_id_seq",
            allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "building")
    private int building;

    @Column(name = "apartment")
    private int apartment;

    @Column(name = "town")
    private String town;

    @OneToMany(mappedBy = "address", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<PersonInfo> personInfos;
}