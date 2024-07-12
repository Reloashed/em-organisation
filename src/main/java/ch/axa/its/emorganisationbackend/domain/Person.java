package ch.axa.its.emorganisationbackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
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
@Table(name = "person")
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(nullable = false)
  private String firstname;

  @Column(nullable = false)
  private String lastname;

  private String email;

  @Column(name = "phone_number", nullable = false)
  private String phoneNumber;

  @JsonIgnore
  @ManyToMany(mappedBy = "people")
  private Set<Job> jobs = new HashSet<>();
}
