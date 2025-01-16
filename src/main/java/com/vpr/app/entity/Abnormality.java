package com.vpr.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "abnormality")
public class Abnormality {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
