package com.vpr.app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_seq_gen")
    @SequenceGenerator(name = "doctor_seq_gen",
            sequenceName = "doctor_id_seq",
            allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "speciality")
    private String speciality;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "person_info_id")
    @JsonManagedReference(value = "personInfo-doctor")
    private PersonInfo personInfo;
}
