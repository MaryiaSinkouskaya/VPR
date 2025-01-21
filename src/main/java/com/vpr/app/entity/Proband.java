package com.vpr.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vpr.app.enums.LaborOutcome;
import com.vpr.app.enums.Ploidity;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Schema(description = "Proband entity")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "proband")
public class Proband {

  @Schema(description = "Proband's uniq id", example = "26713", accessMode = Schema.AccessMode.READ_ONLY)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proband_seq_gen")
  @SequenceGenerator(name = "proband_seq_gen",
      sequenceName = "proband_id_seq",
      allocationSize = 1)
  @Column(name = "id")
  private long id;

  @Schema(description = "Proband's birth date", example = "2017-07-08")
  @Column(name = "birth_date")
  private LocalDate birthDate;

  @Schema(description = "Proband's karyotype", example = "47,ХХ, 21+; 47,ХY, 21++")
  @Column(name = "karyotype")
  private String karyotype;

  @Schema(description = "Mother's pregnancy duration in weeks", example = "40")
  @Column(name = "pregnancy_duration_in_weeks")
  private int pregnancyDurationInWeeks;

  @Schema(description = "Proband's weight (kg)", example = "3.0")
  @Column(name = "weight")
  private double weight;

  @Schema(description = "Measurement of the width of the proband's head (sm)", example = "22.1")
  @Column(name = "head")
  private double head;

  @Schema(description = "Mother's pregnancy number", example = "2")
  @Column(name = "pregnancy_number")
  private int pregnancyNumber;

  @Schema(description = "Was the pregnancy interrupted", example = "false")
  @Column(name = "is_aborted")
  private boolean isAborted;

  @Schema(description = "Information about proband's father")
  @ManyToOne(cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
  })
  @JoinColumn(name = "father_id")
  @JsonManagedReference(value = "personInfo-proband")
  private PersonInfo father;

  @Schema(description = "Information about proband's clinic")
  @ManyToOne(cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
  })
  @JoinColumn(name = "organization_id")
  @JsonManagedReference(value = "organization-proband")
  private Organization organization;

  @Schema(description = "Information about proband's mother")
  @ManyToOne(cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
  })
  @JoinColumn(name = "mother_id")
  @JsonManagedReference(value = "mother-proband")
  private Mother mother;

  @Schema(description = "Proband's abnormality")
  @ManyToOne(cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
  })
  @JoinColumn(name = "abnormality_id")
  @JsonManagedReference(value = "abnormality-proband")
  private Abnormality abnormality;

  @Schema(description = "Ploidy - the number of chromosomes occurring in the nucleus of a cell", example = "DIPLOID")
  @Column(name = "ploidity")
  private Ploidity ploid;

  @Schema(description = "Labour outcome", example = "LIVE_BIRTH")
  @Column(name = "labor_outcome")
  private LaborOutcome laborOutcome;

  @Schema(description = "Proband's gender", example = "FEMALE")
  @Column(name = "gender")
  private String gender;

  @Schema(description = "Additional note about proband")
  @ManyToOne(cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
  })
  @JoinColumn(name = "note_id")
  @JsonManagedReference(value = "note-proband")
  private Note note;

  @Hidden
  @OneToMany(mappedBy = "proband", cascade = CascadeType.PERSIST)
  @JsonIgnore
  private List<ProbandD> probDS;

}
