package com.leverx.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

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
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "proband")
public class Proband {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "karyotype")
    private String karyotype;

    @Column(name = "pregnancy_duration_in_weeks")
    private int pregnancyDurationInWeeks;

    @Column(name = "weight")
    private double weight;

    @Column(name = "head")
    private double head;

    @Column(name = "pregnancy_number")
    private int pregnancyNumber;

    @Column(name = "is_aborted")
    private boolean isAborted;

    @ManyToOne()
    @JoinColumn(name = "father_id")
    @JsonManagedReference(value = "personInfo-proband")
    private PersonInfo father;

    @ManyToOne()
    @JoinColumn(name = "organization_id")
    @JsonManagedReference(value = "organization-proband")
    private Organization organization;

    @ManyToOne()
    @JoinColumn(name = "mother_id")
    @JsonManagedReference(value = "mother-proband")
    private Mother mother;

    @ManyToOne()
    @JoinColumn(name = "tpor_id")
    @JsonManagedReference(value = "abnormality-proband")
    private Abnormality abnormality;

    @ManyToOne()
    @JoinColumn(name = "ploid_id")
    @JsonManagedReference(value = "ploid-proband")
    private Ploid ploid;

    @ManyToOne()
    @JoinColumn(name = "labor_outcome_id")
    @JsonManagedReference(value = "laborOutcome-proband")
    private LaborOutcome laborOutcome;

    @ManyToOne()
    @JoinColumn(name = "gender_id")
    @JsonManagedReference(value = "gender-proband")
    private Gender gender;

    @ManyToOne()
    @JoinColumn(name = "note_id")
    @JsonManagedReference(value = "note-proband")
    private Note note;

    @OneToMany(mappedBy = "proband", cascade = CascadeType.ALL)
    @JsonBackReference(value = "proband-probD")
    private List<ProbD> probDS;

}
