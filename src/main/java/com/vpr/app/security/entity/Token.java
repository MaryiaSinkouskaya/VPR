package com.vpr.app.security.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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

  @ManyToOne(cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
  })
  @JoinColumn(name = "user_id")
  @JsonManagedReference(value = "token-_user")
  private User user;
}
