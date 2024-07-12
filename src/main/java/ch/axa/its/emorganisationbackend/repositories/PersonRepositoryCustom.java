package ch.axa.its.emorganisationbackend.repositories;

import ch.axa.its.emorganisationbackend.domain.Person;

public interface PersonRepositoryCustom {
  Iterable<Person> filter(String firstname, String lastname);
}
