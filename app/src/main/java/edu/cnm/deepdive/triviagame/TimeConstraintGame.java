package edu.cnm.deepdive.triviagame;

import edu.cnm.deepdive.triviagame.model.entity.TriviaQuestion;
import java.util.List;

public class TimeConstraintGame {

  private List<TriviaQuestion> questionList;

  public TimeConstraintGame(List<TriviaQuestion> questionList) {
    this.questionList = questionList;
  }
}
