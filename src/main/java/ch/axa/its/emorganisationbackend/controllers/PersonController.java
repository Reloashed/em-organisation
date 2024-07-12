package ch.axa.its.emorganisationbackend.controllers;

import ch.axa.its.emorganisationbackend.domain.Job;
import ch.axa.its.emorganisationbackend.domain.Person;
import ch.axa.its.emorganisationbackend.domain.Task;
import ch.axa.its.emorganisationbackend.repositories.PersonRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/people")
public class PersonController {
  @Autowired
  private PersonRepository personRepository;

  @GetMapping
  public Iterable<Person> getAllPeople() {
    return personRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Person> getPersonById(@PathVariable String id) {
    Optional<Person> personOpt = personRepository.findById(id);
    if (personOpt.isPresent()) {
      return ResponseEntity.ok(personOpt.get());
    }

    return ResponseEntity.notFound().build();
  }

  @GetMapping("/{id}/jobs")
  public ResponseEntity<Iterable<Job>> getJobsFromPerson(@PathVariable String id) {
    Optional<Person> personOpt = personRepository.findById(id);
    if (personOpt.isPresent()) {
      Person person = personOpt.get();
      return ResponseEntity.ok(person.getJobs());
    }

    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<Person> createPerson(@Valid @RequestBody Person person) {
    return ResponseEntity.status(HttpStatus.CREATED).body(personRepository.save(person));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Person> updatePerson(@PathVariable String id, @Valid @RequestBody Person person) {
    person.setId(id);
    return ResponseEntity.status(HttpStatus.CREATED).body(personRepository.save(person));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Person> deletePerson(@PathVariable String id) {
    personRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
