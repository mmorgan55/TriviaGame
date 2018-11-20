package edu.cnm.deepdive.triviagame.model.pojo;

import java.util.List;
import com.squareup.moshi.Json;

public class TriviaPojo {


  @Json(name = "response_code")
  private Integer responseCode;
  @Json(name = "results")
  private List<TriviaResult> results = null;

  public Integer getResponseCode() {
    return responseCode;
  }

  public void setResponseCode(Integer responseCode) {
    this.responseCode = responseCode;
  }

  public List<TriviaResult> getResults() {
    return results;
  }

  public void setResults(List<TriviaResult> results) {
    this.results = results;
  }

  public static class TriviaResult {

    @Json(name = "category")
    private String category;
    @Json(name = "difficulty")
    private String difficulty;
    @Json(name = "question")
    private String question;
    @Json(name = "correct_answer")
    private String correctAnswer;
    @Json(name = "incorrect_answers")
    private List<String> incorrectAnswers = null;

    public String getCategory() {
      return category;
    }

    public void setCategory(String category) {
      this.category = category;
    }

    public String getDifficulty() {
      return difficulty;
    }

    public void setDifficulty(String difficulty) {
      this.difficulty = difficulty;
    }

    public String getQuestion() {
      return question;
    }

    public void setQuestion(String question) {
      this.question = question;
    }

    public String getCorrectAnswer() {
      return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
      this.correctAnswer = correctAnswer;
    }

    public List<String> getIncorrectAnswers() {
      return incorrectAnswers;
    }

    public void setIncorrectAnswers(List<String> incorrectAnswers) {
      this.incorrectAnswers = incorrectAnswers;
    }
  }

}
