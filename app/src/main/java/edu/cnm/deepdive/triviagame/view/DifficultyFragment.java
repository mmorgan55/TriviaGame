package edu.cnm.deepdive.triviagame.view;

import static edu.cnm.deepdive.triviagame.MainActivity.bundle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import edu.cnm.deepdive.triviagame.R;

public class DifficultyFragment extends Fragment {

  private OnClickListener listener;
  private String difficulty;
  private CategoriesFragment categoriesFragment = new CategoriesFragment();

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_difficulty, container, false);

    Button easyButton = view.findViewById(R.id.easy_button);
    Button mediumButton = view.findViewById(R.id.medium_button);
    Button hardButton = view.findViewById(R.id.hard_button);

    setListener();

    easyButton.setOnClickListener(listener);
    mediumButton.setOnClickListener(listener);
    hardButton.setOnClickListener(listener);

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
        default:
          difficulty = "medium";
          bundle.putString("difficulty", difficulty);
          break;
      }

      categoriesFragment.setArguments(bundle);
      getFragmentManager()
          .beginTransaction().replace(R.id.fragment_container, categoriesFragment)
          .addToBackStack("diff")
          .commit();
    };
  }
}
