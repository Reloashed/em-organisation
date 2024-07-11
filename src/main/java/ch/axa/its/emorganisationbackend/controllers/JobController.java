package ch.axa.its.emorganisationbackend.controllers;

import ch.axa.its.emorganisationbackend.domain.Job;
import ch.axa.its.emorganisationbackend.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
  @Autowired
  private JobRepository jobRepository;

  @GetMapping
  public Iterable<Job> getAllJobs() {
    return jobRepository.findAll();
  }
}
