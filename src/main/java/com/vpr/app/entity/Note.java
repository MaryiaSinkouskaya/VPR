package com.vpr.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Builder
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
  private long id;

  @Column(name = "date")
  private Date date;

  @Column(name = "note")
  private String note;

  @OneToMany(mappedBy = "note", cascade = CascadeType.PERSIST)
  @JsonIgnore
  private List<Proband> probands;
}
