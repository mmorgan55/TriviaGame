package edu.cnm.deepdive.triviagame;


import android.content.res.Resources;
import java.util.List;

public class TriviaQuestion {

  private String[] questions;
  private Resources resources;

  public TriviaQuestion(String category, String difficulty, List<String> answers) {
    questions = resources.getStringArray(R.array.initial_questions);
  }
}
