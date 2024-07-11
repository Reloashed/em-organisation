package ch.axa.its.emorganisationbackend;

import ch.axa.its.emorganisationbackend.domain.Game;
import ch.axa.its.emorganisationbackend.domain.Job;
import ch.axa.its.emorganisationbackend.domain.Person;
import ch.axa.its.emorganisationbackend.domain.Task;
import ch.axa.its.emorganisationbackend.repositories.GameRepository;
import ch.axa.its.emorganisationbackend.repositories.JobRepository;
import ch.axa.its.emorganisationbackend.repositories.PersonRepository;
import ch.axa.its.emorganisationbackend.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
public class Dataloader implements ApplicationRunner {
  @Autowired
  private GameRepository gameRepository;
  @Autowired
  private JobRepository jobRepository;
  @Autowired
  private PersonRepository personRepository;
  @Autowired
  private TaskRepository taskRepository;


  @Override
  public void run(ApplicationArguments args) throws Exception {
    Job job = new Job();
    job.setDesignation("Security");
    jobRepository.save(job);

    Task task = new Task();
    task.setDesignation("Check bags");
    task.setDescription("Check the bags of visitors");
    task.setJob(job);
    taskRepository.save(task);

    Person person = new Person();
    person.setFirstname("Max");
    person.setLastname("Muster");
    person.setEmail("max.muster@gmail.com");
    person.setPhoneNumber("0774543288");
    Set<Job> jobSet = new HashSet<>();
    jobSet.add(job);
    person.setPersonJobSet(jobSet);
    personRepository.save(person);

    Game game = new Game();
    game.setStadium("Hallenstadion Zuerich");
    game.setDate(LocalDate.now());
    game.setJobGameSet(jobSet);
    gameRepository.save(game);
  }
}
