package edu.cnm.deepdive.triviagame;

public class TriviaCategory {

  private String categoryTitle;

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

