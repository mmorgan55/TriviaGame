package edu.cnm.deepdive.triviagame;

import java.util.List;

public class TriviaCategory {

  private String categoryTitle;
  private List<TriviaQuestion> categoryQuestions;

  public TriviaCategory(String categoryTitle) {
    this.categoryTitle = categoryTitle;
  }

  public String getCategoryTitle() {
    return categoryTitle;
  }

  @Override
  public String toString() {
    return getCategoryTitle();
  }
}

