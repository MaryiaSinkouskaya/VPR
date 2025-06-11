package com.vpr.app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prob_d")
public class ProbandD {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prob_d_seq_gen")
    @SequenceGenerator(name = "prob_d_seq_gen",
            sequenceName = "prob_d_id_seq",
            allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "death_date")
    private LocalDate deathDate;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "proband_id")
    @JsonManagedReference(value = "proband-probD")
    private Proband proband;
}
