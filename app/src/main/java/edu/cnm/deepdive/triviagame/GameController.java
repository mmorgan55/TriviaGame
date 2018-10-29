package edu.cnm.deepdive.triviagame;

import edu.cnm.deepdive.triviagame.model.entity.TriviaQuestion;

import java.util.List;

public class GameController {

  private String category = "Initial";
  private String difficulty = "easy";
  private String gameType = "relaxed";
  private List<TriviaQuestion> questionList;


  public GameController(String gameType) {

    this.gameType = gameType;

    switch (gameType) {
      case "relaxed":
        new RelaxedGame(questionList);
        break;
      case "sudden":
        new SuddenDeathGame(questionList);
        break;
      case "time":
        new TimeConstraintGame(questionList);
        break;
      default:
        new RelaxedGame(questionList);
        break;
    }
  }
}
