package com.vpr.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Table(name = "abnormality")
public class Abnormality {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "abnormality_seq_gen")
    @SequenceGenerator(name = "abnormality_seq_gen",
            sequenceName = "abnormality_id_seq",
            allocationSize = 1)
    @Schema(description = "Abnormality's uniq id", example = "26713", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "id")
    private Long id;

    @Schema(description = "Abnormality", example = "Hepatoblastoma")
    @Column(name = "name")
    private String name;

    @Hidden
    @OneToMany(mappedBy = "abnormality", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Proband> probands;
}