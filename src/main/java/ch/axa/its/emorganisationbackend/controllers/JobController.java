package ch.axa.its.emorganisationbackend.controllers;

import ch.axa.its.emorganisationbackend.domain.Game;
import ch.axa.its.emorganisationbackend.domain.Job;
import ch.axa.its.emorganisationbackend.domain.Person;
import ch.axa.its.emorganisationbackend.domain.Task;
import ch.axa.its.emorganisationbackend.repositories.GameRepository;
import ch.axa.its.emorganisationbackend.repositories.JobRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
  @Autowired
  private JobRepository jobRepository;

  @GetMapping
  public Iterable<Job> getAllJobs() {
    return jobRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Job> getJobById(@PathVariable String id) {
    Optional<Job> jobOpt = jobRepository.findById(id);
    if (jobOpt.isPresent()) {
      return ResponseEntity.ok(jobOpt.get());
    }

    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<Job> createJob(@Valid @RequestBody Job job) {
    return ResponseEntity.status(HttpStatus.CREATED).body(jobRepository.save(job));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Job> updateJob(@PathVariable String id, @Valid @RequestBody Job job) {
    job.setId(id);
    return ResponseEntity.status(HttpStatus.CREATED).body(jobRepository.save(job));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Job> deleteJob(@PathVariable String id) {
    jobRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
