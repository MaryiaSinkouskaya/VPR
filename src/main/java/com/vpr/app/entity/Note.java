package com.vpr.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_seq_gen")
    @SequenceGenerator(name = "note_seq_gen",
            sequenceName = "note_id_seq",
            allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "note")
    private String note;

    @OneToMany(mappedBy = "note", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Proband> probands;
}
