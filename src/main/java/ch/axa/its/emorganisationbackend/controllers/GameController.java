package ch.axa.its.emorganisationbackend.controllers;

import ch.axa.its.emorganisationbackend.domain.Game;
import ch.axa.its.emorganisationbackend.repositories.GameRepository;
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
@RequestMapping("/api/games")
@Tag(name = "Games", description = "Manage all played or future games.")
public class GameController {
  @Autowired
  private GameRepository gameRepository;

  @GetMapping
  @Operation(summary = "Get all games", description = "Get all games from the database.")
  public ResponseEntity<Iterable<Game>> getAllGames() {
    return ResponseEntity.ok(gameRepository.findAll());
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get specific game", description = "Get a game by their unique identifier.")
  public ResponseEntity<Game> getGameById(@Parameter(description = "The unique identifier for the specific game.") @PathVariable String id) {
    Optional<Game> gameOpt = gameRepository.findById(id);
    if (gameOpt.isPresent()) {
      return ResponseEntity.ok(gameOpt.get());
    }

    return ResponseEntity.notFound().build();
  }

  @PostMapping
  @Operation(summary = "Add a game", description = "Add a game to the database.")
  public ResponseEntity<Game> createGame(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "The new game object to add.") @Valid @RequestBody Game game) {
    return ResponseEntity.status(HttpStatus.CREATED).body(gameRepository.save(game));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a game", description = "Update a game by their unique identifier.")
  public ResponseEntity<Game> updateGame(@Parameter(description = "The unique identifier for the specific game.") @PathVariable String id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "The new game object to add.") @Valid @RequestBody Game game) {
    game.setId(id);
    return ResponseEntity.status(HttpStatus.CREATED).body(gameRepository.save(game));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a game", description = "Delete a game by their unique identifier.")
  public ResponseEntity<Game> deleteGame(@Parameter(description = "The unique identifier for the specific game.") @PathVariable String id) {
    gameRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
