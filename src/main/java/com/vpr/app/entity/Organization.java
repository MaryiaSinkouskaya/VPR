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
@Table(name = "organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "org_seq_gen")
    @SequenceGenerator(name = "org_seq_gen",
            sequenceName = "org_id_seq",
            allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "number")
    private int number;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Proband> probands;
}
