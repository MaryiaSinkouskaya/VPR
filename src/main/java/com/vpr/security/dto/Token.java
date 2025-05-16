package com.vpr.security.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_seq_gen")
  @SequenceGenerator(name = "token_seq_gen",
      sequenceName = "token_id_seq",
      allocationSize = 1)
  @Column(name = "id")
  private Integer id;

  @Column(unique = true)
  private String token;

  private boolean revoked;

  private boolean expired;

  @ManyToOne()
  @JoinColumn(name = "user_id")
  @JsonManagedReference(value = "token-user")
  private User user;
}
