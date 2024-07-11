package ch.axa.its.emorganisationbackend.controllers;

import ch.axa.its.emorganisationbackend.domain.Game;
import ch.axa.its.emorganisationbackend.domain.Person;
import ch.axa.its.emorganisationbackend.repositories.GameRepository;
import ch.axa.its.emorganisationbackend.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/people")
public class PersonController {
  @Autowired
  private PersonRepository personRepository;

  @GetMapping
  public Iterable<Person> getAllPeople() {
    return personRepository.findAll();
  }
}
