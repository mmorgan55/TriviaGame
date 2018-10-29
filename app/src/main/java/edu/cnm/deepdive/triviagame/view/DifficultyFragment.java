package edu.cnm.deepdive.triviagame.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import edu.cnm.deepdive.triviagame.R;
import edu.cnm.deepdive.triviagame.view.CategoriesFragment;


public class DifficultyFragment extends Fragment {

  private Button easyButton;
  private Button mediumButton;
  private Button hardButton;
  private OnClickListener listener;
  private String difficulty;
  private CategoriesFragment categoriesFragment = new CategoriesFragment();

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_difficulty, container, false);

    easyButton = view.findViewById(R.id.easy_button);
    mediumButton = view.findViewById(R.id.medium_button);
    hardButton = view.findViewById(R.id.hard_button);

    setListener();

    easyButton.setOnClickListener(listener);
    mediumButton.setOnClickListener(listener);
    hardButton.setOnClickListener(listener);

    return view;
  }

  private void setListener() {

    listener = (v) -> {

      int id = v.getId();
      float deg = v.getRotation() + -360F;
      v.animate().rotation(deg).setInterpolator(new AccelerateDecelerateInterpolator());

      switch (id) {
        case R.id.easy_button:
          difficulty = "easy";
          break;
        case R.id.medium_button:
          difficulty = "medium";
          break;
        case R.id.hard_button:
          difficulty = "hard";
          break;
        default:
          difficulty = "medium";
          break;
      }
      getFragmentManager()
          .beginTransaction().replace(R.id.fragment_container, categoriesFragment)
          .addToBackStack("diff")
          .commit();


    };
  }

  public String getDifficulty() {
    return difficulty;
  }
}
