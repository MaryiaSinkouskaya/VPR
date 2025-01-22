package com.vpr.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person_info")
public class PersonInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_info_seq_gen")
    @SequenceGenerator(name = "person_info_seq_gen",
            sequenceName = "person_info_id_seq",
            allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "phone")
    private String phone;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "address_id")
    @JsonManagedReference(value = "address-personInfo")
    private Address address;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "work_id")
    @JsonManagedReference(value = "workplace-personInfo")
    private Workplace workplace;

    @OneToMany(mappedBy = "personInfo", cascade = CascadeType.PERSIST)
    @JsonIgnore()
    private List<Mother> mothers;

    @OneToMany(mappedBy = "personInfo", cascade = CascadeType.PERSIST)
    @JsonIgnore()
    private List<Doctor> doctors;

    @OneToMany(mappedBy = "father", cascade = CascadeType.PERSIST)
    @JsonIgnore()
    private List<Proband> probands;
}
