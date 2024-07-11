package ch.axa.its.emorganisationbackend.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Game {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(nullable = false)
  private LocalDate date;

  @Column(nullable = false)
  private String stadium;

  @ManyToMany(mappedBy = "games")
  private Set<Job> jobGameSet = new HashSet<>();
}