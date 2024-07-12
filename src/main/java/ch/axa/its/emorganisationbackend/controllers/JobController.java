package ch.axa.its.emorganisationbackend.controllers;

import ch.axa.its.emorganisationbackend.domain.Job;
import ch.axa.its.emorganisationbackend.repositories.JobRepository;
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
@RequestMapping("/api/jobs")
@Tag(name = "Jobs", description = "Manage all done or future jobs.")
public class JobController {
  @Autowired
  private JobRepository jobRepository;

  @GetMapping
  @Operation(summary = "Get all jobs", description = "Get all jobs with a filter for the designation.")
  public Iterable<Job> getAllJobs(@Parameter(description = "The desired designation to filter for.") @RequestParam(required = false) String designation) {
    return jobRepository.filter(designation);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get specific job", description = "Get a job by their unique identifier.")
  public ResponseEntity<Job> getJobById(@Parameter(description = "The unique identifier for the specific job.") @PathVariable String id) {
    Optional<Job> jobOpt = jobRepository.findById(id);
    if (jobOpt.isPresent()) {
      return ResponseEntity.ok(jobOpt.get());
    }

    return ResponseEntity.notFound().build();
  }

  @PostMapping
  @Operation(summary = "Add a job", description = "Add a job to the database.")
  public ResponseEntity<Job> createJob(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "The new job object to add.") @Valid @RequestBody Job job) {
    return ResponseEntity.status(HttpStatus.CREATED).body(jobRepository.save(job));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a job", description = "Update a job by their unique identifier.")
  public ResponseEntity<Job> updateJob(@Parameter(description = "The unique identifier for the specific job.") @PathVariable String id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "The new job object to add.") @Valid @RequestBody Job job) {
    job.setId(id);
    return ResponseEntity.status(HttpStatus.CREATED).body(jobRepository.save(job));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a job", description = "Delete a job by their unique identifier.")
  public ResponseEntity<Job> deleteJob(@PathVariable String id) {
    jobRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
