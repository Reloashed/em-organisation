package ch.axa.its.emorganisationbackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
  @Size(min = 2, max = 50)
  @NotBlank
  private String firstname;

  @Column(nullable = false)
  @Size(min = 2, max = 50)
  @NotBlank
  private String lastname;

  @Email
  private String email;

  @Column(name = "phone_number", nullable = false)
  @NotBlank
  private String phoneNumber;

  @JsonIgnore
  @ManyToMany(mappedBy = "people")
  private Set<Job> jobs = new HashSet<>();
}
