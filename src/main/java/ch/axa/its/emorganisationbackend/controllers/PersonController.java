package ch.axa.its.emorganisationbackend.controllers;

import ch.axa.its.emorganisationbackend.domain.Person;
import ch.axa.its.emorganisationbackend.repositories.PersonRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/people")
@Tag(name = "People", description = "Manage all people present.")
public class PersonController {
  @Autowired
  private PersonRepository personRepository;

  @GetMapping
  @Operation(summary = "Get all people", description = "Get all people with filters for the first and lastname.")
  public Iterable<Person> getAllPeople(@Parameter(description = "The desired firstname to filter for.") @RequestParam(required = false) String firstname, @Parameter(description = "The desired lastname to filter for.") @RequestParam(required = false) String lastname) {
    return personRepository.filter(firstname, lastname);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get specific person", description = "Get a person by their unique identifier.")
  public ResponseEntity<Person> getPersonById(@Parameter(description = "The unique identifier for the specific person.") @PathVariable String id) {
    Optional<Person> personOpt = personRepository.findById(id);
    if (personOpt.isPresent()) {
      return ResponseEntity.ok(personOpt.get());
    }

    return ResponseEntity.notFound().build();
  }

  @PostMapping
  @Operation(summary = "Add a person", description = "Add a person to the database.")
  public ResponseEntity<Person> createPerson(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "The new person object to add.") @Valid @RequestBody Person person) {
    return ResponseEntity.status(HttpStatus.CREATED).body(personRepository.save(person));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a person", description = "Update a person by their unique identifier.")
  public ResponseEntity<Person> updatePerson(@Parameter(description = "The unique identifier for the specific person.") @PathVariable String id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "The new person object to add.") @Valid @RequestBody Person person) {
    person.setId(id);
    return ResponseEntity.status(HttpStatus.CREATED).body(personRepository.save(person));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a person", description = "Delete a person by their unique identifier.")
  public ResponseEntity<Person> deletePerson(@Parameter(description = "The unique identifier for the specific person.") @PathVariable String id) {
    personRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
