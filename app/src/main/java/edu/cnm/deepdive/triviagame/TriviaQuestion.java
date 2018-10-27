package edu.cnm.deepdive.triviagame;

import java.util.List;

public class TriviaQuestion {

  private String category;
  private String difficulty;
  private String question;
  private String correctAnswer;
  private List<String> answers;

  public TriviaQuestion(String question, String correctAnswer, List<String> answers) {
    this.question = question;
    this.correctAnswer = correctAnswer;
    this.answers = answers;
  }

  public String getQuestion() {
    return question;
  }

  public String getCorrectAnswer() {
    return correctAnswer;
  }

  public List<String> getAnswers() {
    return answers;
  }

  @Override
  public String toString() {
    return getQuestion();
  }
}
