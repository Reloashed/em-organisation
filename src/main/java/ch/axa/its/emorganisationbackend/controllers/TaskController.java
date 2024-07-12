package ch.axa.its.emorganisationbackend.controllers;

import ch.axa.its.emorganisationbackend.domain.Job;
import ch.axa.its.emorganisationbackend.domain.Person;
import ch.axa.its.emorganisationbackend.domain.Task;
import ch.axa.its.emorganisationbackend.repositories.TaskRepository;
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
@RequestMapping("/api/tasks")
@Tag(name = "Task", description = "Manage all done or future tasks.")
public class TaskController {
  @Autowired
  private TaskRepository taskRepository;

  @GetMapping
  @Operation(summary = "Get all tasks", description = "Get all tasks from the database.")
  public Iterable<Task> getAllTasks() {
    return taskRepository.findAll();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get specific task", description = "Get a task by their unique identifier.")
  public ResponseEntity<Task> getTaskById(@Parameter(description = "The unique identifier for the specific task.") @PathVariable String id) {
    Optional<Task> taskOpt = taskRepository.findById(id);
    if (taskOpt.isPresent()) {
      return ResponseEntity.ok(taskOpt.get());
    }

    return ResponseEntity.notFound().build();
  }

  @PostMapping
  @Operation(summary = "Add a task", description = "Add a task to the database.")
  public ResponseEntity<Task> createTask(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "The new task object to add.") @Valid @RequestBody Task task) {
    return ResponseEntity.status(HttpStatus.CREATED).body(taskRepository.save(task));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a task", description = "Update a task by their unique identifier.")
  public ResponseEntity<Task> updateTask(@Parameter(description = "The unique identifier for the specific task.") @PathVariable String id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "The new task object to add.") @Valid @RequestBody Task task) {
    task.setId(id);
    return ResponseEntity.status(HttpStatus.CREATED).body(taskRepository.save(task));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a task", description = "Delete a task by their unique identifier.")
  public ResponseEntity<Task> deleteTask(@Parameter(description = "The unique identifier for the specific task.") @PathVariable String id) {
    taskRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
