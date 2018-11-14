package edu.cnm.deepdive.triviagame.view;

import static edu.cnm.deepdive.triviagame.controller.MainActivity.bundle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import edu.cnm.deepdive.triviagame.GameController;
import edu.cnm.deepdive.triviagame.R;

public class DifficultyFragment extends Fragment {

  @BindView(R.id.easy_button)
  Button easyButton;
  @BindView(R.id.medium_button)
  Button mediumButton;
  @BindView(R.id.hard_button)
  Button hardButton;
  @BindView(R.id.all_button)
  Button allButton;

  private OnClickListener listener;
  private String difficulty;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_difficulty, container, false);
    ButterKnife.bind(this, view);

    setListener();

    easyButton.setOnClickListener(listener);
    mediumButton.setOnClickListener(listener);
    hardButton.setOnClickListener(listener);
    allButton.setOnClickListener(listener);

    return view;
  }

  private void setListener() {

    listener = (v) -> {
      int id = v.getId();

      switch (id) {
        case R.id.easy_button:
          difficulty = "easy";
          bundle.putString("difficulty", difficulty);
          break;
        case R.id.medium_button:
          difficulty = "medium";
          bundle.putString("difficulty", difficulty);
          break;
        case R.id.hard_button:
          difficulty = "hard";
          bundle.putString("difficulty", difficulty);
          break;
        case R.id.all_button:
          difficulty = "all";
          bundle.putString("difficulty", difficulty);
          break;
        default:
          difficulty = "medium";
          bundle.putString("difficulty", difficulty);
          break;
      }

      new GameController(getActivity(), bundle);
    };
  }
}
