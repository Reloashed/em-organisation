package ch.axa.its.emorganisationbackend.controllers;

import ch.axa.its.emorganisationbackend.domain.Game;
import ch.axa.its.emorganisationbackend.domain.Job;
import ch.axa.its.emorganisationbackend.domain.Task;
import ch.axa.its.emorganisationbackend.repositories.GameRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/games")
public class GameController {
  @Autowired
  private GameRepository gameRepository;

  @GetMapping
  public ResponseEntity<Iterable<Game>> getAllGames() {
    return ResponseEntity.ok(gameRepository.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Game> getGameById(@PathVariable String id) {
    Optional<Game> gameOpt = gameRepository.findById(id);
    if (gameOpt.isPresent()) {
      return ResponseEntity.ok(gameOpt.get());
    }

    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<Game> createGame(@Valid @RequestBody Game game) {
    return ResponseEntity.status(HttpStatus.CREATED).body(gameRepository.save(game));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Game> updateGame(@PathVariable String id, @Valid @RequestBody Game game) {
    game.setId(id);
    return ResponseEntity.status(HttpStatus.CREATED).body(gameRepository.save(game));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Game> deleteGame(@PathVariable String id) {
    gameRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
