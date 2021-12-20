package com.vpr.app.entity;

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
@Table(name = "mother")
public class Mother {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "last_menstruation_date")
    private Date lastMenstruationDate;

    @Column(name = "diagnose_date")
    private Date diagnoseDate;

    @Column(name = "girl_surname")
    private String girlSurname;

    @ManyToOne()
    @JoinColumn(name = "person_info_id")
    @JsonManagedReference(value = "personInfo-mother")
    private PersonInfo personInfo;

    @OneToMany(mappedBy = "mother", cascade = CascadeType.ALL)
    @JsonBackReference(value = "mother-proband")
    private List<Proband> probands;
}
