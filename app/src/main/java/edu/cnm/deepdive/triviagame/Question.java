package edu.cnm.deepdive.triviagame;


import android.content.res.Resources;

public class Question {

  private String[] questions;
  private Resources resources;

  public Question (String type) {
    questions = resources.getStringArray(R.array.initial_questions);
  }
}
