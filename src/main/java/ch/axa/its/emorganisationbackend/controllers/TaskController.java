package ch.axa.its.emorganisationbackend.controllers;

import ch.axa.its.emorganisationbackend.domain.Job;
import ch.axa.its.emorganisationbackend.domain.Person;
import ch.axa.its.emorganisationbackend.domain.Task;
import ch.axa.its.emorganisationbackend.repositories.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
  @Autowired
  private TaskRepository taskRepository;

  @GetMapping
  public Iterable<Task> getAllTasks() {
    return taskRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Task> getTaskById(@PathVariable String id) {
    Optional<Task> taskOpt = taskRepository.findById(id);
    if (taskOpt.isPresent()) {
      return ResponseEntity.ok(taskOpt.get());
    }

    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
    return ResponseEntity.status(HttpStatus.CREATED).body(taskRepository.save(task));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Task> updateTask(@PathVariable String id, @Valid @RequestBody Task task) {
    task.setId(id);
    return ResponseEntity.status(HttpStatus.CREATED).body(taskRepository.save(task));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Task> deleteTask(@PathVariable String id) {
    taskRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
