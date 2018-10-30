package edu.cnm.deepdive.triviagame.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.cnm.deepdive.triviagame.R;

public class GameFragment extends Fragment {

  private String category;
  private String difficulty;
  private String gameType;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    gameType = getArguments().getString("gameType");
    category = getArguments().getString("category");
    difficulty = getArguments().getString("difficulty");

    View view;

    switch (gameType) {
      case "relaxed":
        view = inflater.inflate(R.layout.fragment_relaxed_game, container, false);
      default:
        view = inflater.inflate(R.layout.fragment_relaxed_game, container, false);
    }
    return view;
  }
}
