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
@Table(name = "workplace")
public class Workplace {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "work_seq_gen")
    @SequenceGenerator(name = "work_seq_gen",
            sequenceName = "work_id_seq",
            allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "job_type")
    private String jobType;

    @Column(name = "company")
    private String company;

    @OneToMany(mappedBy = "workplace", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<PersonInfo> personInfos;
}
