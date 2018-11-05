package edu.cnm.deepdive.triviagame.view;

import static edu.cnm.deepdive.triviagame.MainActivity.bundle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
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

      switch (id) {
        case R.id.time_button:
          gameType = "time";
          bundle.putString("gameType", gameType);
          break;
        case R.id.difficulty_button:
          gameType = "sudden";
          bundle.putString("gameType", gameType);
          break;
        case R.id.relaxed_button:
          gameType = "relaxed";
          bundle.putString("gameType", gameType);
          break;
        default:
          gameType = "relaxed";
          bundle.putString("gameType", gameType);
          break;
      }

      difficultyFragment.setArguments(bundle);
      getFragmentManager()
          .beginTransaction().replace(R.id.fragment_container, difficultyFragment)
          .addToBackStack("main")
          .commit();
    };
  }
}
