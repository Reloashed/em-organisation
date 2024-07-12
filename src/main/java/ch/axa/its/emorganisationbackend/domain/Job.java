package ch.axa.its.emorganisationbackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job")
public class Job {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(nullable = false)
  @NotBlank
  private String designation;

  @ManyToMany
  @JoinTable(
          name = "job_person",
          joinColumns = @JoinColumn(name = "job_id"),
          inverseJoinColumns = @JoinColumn(name = "person_id")
  )
  @JsonIgnoreProperties("jobs")
  private Set<Person> people = new HashSet<>();

  @OneToMany(mappedBy = "job")
  @JsonIgnoreProperties("job")
  private Set<Task> tasks = new HashSet<>();

  @ManyToMany
  @JoinTable(
          name = "job_game",
          joinColumns = @JoinColumn(name = "job_id"),
          inverseJoinColumns = @JoinColumn(name = "game_id")
  )
  @JsonIgnoreProperties("jobs")
  private Set<Game> games = new HashSet<>();
}
