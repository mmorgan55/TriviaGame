package edu.cnm.deepdive.triviagame.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import edu.cnm.deepdive.triviagame.R;

public class MainFragment extends android.support.v4.app.Fragment {

  private String gameType;
  private OnClickListener listener;
  private DifficultyFragment difficultyFragment = new DifficultyFragment();

  @Override
  public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_main, container, false);

    Button timeButton = view.findViewById(R.id.time_button);
    Button diffButton = view.findViewById(R.id.difficulty_button);
    Button relaxButton = view.findViewById(R.id.relaxed_button);

    setListener();

    timeButton.setOnClickListener(listener);
    diffButton.setOnClickListener(listener);
    relaxButton.setOnClickListener(listener);

    return view;
  }

  private void setListener() {

    listener = v -> {
      int id = v.getId();
      float deg = v.getRotation() + -360F;
      v.animate().rotation(deg).setInterpolator(new AccelerateDecelerateInterpolator());

      switch (id) {
        case R.id.time_button:
          gameType = "time";
          break;
        case R.id.difficulty_button:
          gameType = "sudden";
          break;
        case R.id.relaxed_button:
          gameType = "relaxed";
          break;
        default:
          gameType = "relaxed";
          break;
      }
      getFragmentManager()
          .beginTransaction().replace(R.id.fragment_container, difficultyFragment)
          .addToBackStack("view")
          .commit();
    };
  }

  public String getGameType() {
    return gameType;
  }
}
