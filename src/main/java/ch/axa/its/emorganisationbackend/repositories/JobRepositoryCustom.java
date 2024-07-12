package ch.axa.its.emorganisationbackend.repositories;

import ch.axa.its.emorganisationbackend.domain.Job;

public interface JobRepositoryCustom {
  Iterable<Job> filter(String designation);
}
