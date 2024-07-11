package ch.axa.its.emorganisationbackend.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
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
  private long phoneNumber;

  @ManyToMany(mappedBy = "people")
  private Set<Job> personJobSet = new HashSet<>();
}
