package com.vpr.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "abnormality")
public class Abnormality {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "abnormality_id_seq")
  @SequenceGenerator(name = "abnormality_id_seq",
          sequenceName = "abnormality_id_seq",
          initialValue = 50,
          allocationSize = 1)
  @Schema(description = "Abnormality's uniq id", example = "26713", accessMode = Schema.AccessMode.READ_ONLY)
  @Column(name = "id")
  private long id;

  @Schema(description = "Abnormality", example = "Hepatoblastoma")
  @Column(name = "name")
  private String name;

  @Hidden
  @OneToMany(mappedBy = "abnormality", cascade = CascadeType.ALL)
  @JsonBackReference(value = "abnormality-proband")
  private List<Proband> probands;
}
