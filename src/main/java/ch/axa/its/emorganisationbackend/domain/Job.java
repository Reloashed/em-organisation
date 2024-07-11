package ch.axa.its.emorganisationbackend.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Job {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(nullable = false)
  private String designation;

  @ManyToMany
  @JoinTable(
          name = "job_person",
          joinColumns = @JoinColumn(name = "job_id"),
          inverseJoinColumns = @JoinColumn(name = "person_id")
  )
  private Set<Person> people = new HashSet<>();

  @OneToMany(mappedBy = "job")
  private Set<Task> tasks = new HashSet<>();

  @ManyToMany
  @JoinTable(
          name = "job_match",
          joinColumns = @JoinColumn(name = "job_id"),
          inverseJoinColumns = @JoinColumn(name = "match_id")
  )
  private Set<Game> games = new HashSet<>();
}
