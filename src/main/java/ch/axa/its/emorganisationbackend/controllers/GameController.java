package ch.axa.its.emorganisationbackend.controllers;

import ch.axa.its.emorganisationbackend.domain.Game;
import ch.axa.its.emorganisationbackend.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/games")
public class GameController {
  @Autowired
  private GameRepository gameRepository;

  @GetMapping
  public Iterable<Game> getAllGames() {
    return gameRepository.findAll();
  }
}
