package ch.axa.its.emorganisationbackend.controllers;

import ch.axa.its.emorganisationbackend.domain.Task;
import ch.axa.its.emorganisationbackend.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
  @Autowired
  private TaskRepository taskRepository;

  @GetMapping
  public Iterable<Task> getAllTasks() {
    return taskRepository.findAll();
  }
}
