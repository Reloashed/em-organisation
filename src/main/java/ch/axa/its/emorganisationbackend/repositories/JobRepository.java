package ch.axa.its.emorganisationbackend.repositories;

import ch.axa.its.emorganisationbackend.domain.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends CrudRepository<Job, String> {
}
