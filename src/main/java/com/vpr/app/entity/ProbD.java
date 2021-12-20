package com.vpr.app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Getter
@Setter
@Table(name = "prob_d")
public class ProbD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "death_date")
    private Date deathDate;

    @ManyToOne()
    @JoinColumn(name = "proband_id")
    @JsonManagedReference(value = "proband-probD")
    private Proband proband;
}
