package com.vpr.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vpr.app.enums.LaborOutcome;
import com.vpr.app.enums.Ploidity;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Schema(description = "Proband entity")
@Entity
@Getter
@Setter
@Table(name = "proband")
public class Proband {

  @Schema(description = "Proband's uniq id", example = "26713", accessMode = Schema.AccessMode.READ_ONLY)
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Schema(description = "Proband's birth date", example = "2017-07-08")
  @Column(name = "birth_date")
  private Date birthDate;

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
  @ManyToOne()
  @JoinColumn(name = "father_id")
  @JsonManagedReference(value = "personInfo-proband")
  private PersonInfo father;

  @Schema(description = "Information about proband's clinic")
  @ManyToOne()
  @JoinColumn(name = "organization_id")
  @JsonManagedReference(value = "organization-proband")
  private Organization organization;

  @Schema(description = "Information about proband's mother")
  @ManyToOne()
  @JoinColumn(name = "mother_id")
  @JsonManagedReference(value = "mother-proband")
  private Mother mother;

  @Schema(description = "Proband's abnormality")
  @ManyToOne()
  @JoinColumn(name = "tpor_id")
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
  @ManyToOne()
  @JoinColumn(name = "note_id")
  @JsonManagedReference(value = "note-proband")
  private Note note;

  @Hidden
  @OneToMany(mappedBy = "proband", cascade = CascadeType.ALL)
  @JsonBackReference(value = "proband-probD")
  private List<ProbandD> probDS;

}
