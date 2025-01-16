package com.vpr.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
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
@Table(name = "note")
public class Note {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "date")
  private Date date;

  @Column(name = "note")
  private String note;

  @OneToMany(mappedBy = "note", cascade = CascadeType.ALL)
  @JsonBackReference(value = "note-proband")
  private List<Proband> probands;
}
