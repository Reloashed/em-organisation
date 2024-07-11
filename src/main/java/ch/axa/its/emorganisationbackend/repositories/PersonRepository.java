package ch.axa.its.emorganisationbackend.repositories;

import ch.axa.its.emorganisationbackend.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {
}
