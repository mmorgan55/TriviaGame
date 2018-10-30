package edu.cnm.deepdive.triviagame.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.cnm.deepdive.triviagame.R;


public class RelaxedGameFragment extends GameFragment {

  private String category;
  private  String difficulty;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    category = getArguments().getString("category");
    difficulty = getArguments().getString("difficulty");
    return inflater.inflate(R.layout.fragment_relaxed_game, container, false);
  }

}
