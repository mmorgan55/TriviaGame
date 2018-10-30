package edu.cnm.deepdive.triviagame.view;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.cnm.deepdive.triviagame.R;
import edu.cnm.deepdive.triviagame.model.db.TriviaDatabase;
import edu.cnm.deepdive.triviagame.model.entity.TriviaAnswers;
import edu.cnm.deepdive.triviagame.model.entity.TriviaQuestion;
import java.util.List;


public class RelaxedGameFragment extends GameFragment {

  private List<TriviaQuestion> questions;
  private List<TriviaAnswers> answers;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_relaxed_game, container, false);
  }



}
