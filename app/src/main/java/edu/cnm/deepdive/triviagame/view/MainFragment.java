package edu.cnm.deepdive.triviagame.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import edu.cnm.deepdive.triviagame.R;
import edu.cnm.deepdive.triviagame.controller.MainActivity;

/**
 * This class is used to keep track of the game that the user would like to play.
 */
public class MainFragment extends android.support.v4.app.Fragment {

  @BindView(R.id.time_button)
  Button timeButton;
  @BindView(R.id.difficulty_button)
  Button diffButton;
  @BindView(R.id.relaxed_button)
  Button relaxButton;

  private String gameType;
  private OnClickListener listener;
  private CategoriesFragment categoriesFragment = new CategoriesFragment();

  @Override
  public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_main, container, false);
    ButterKnife.bind(this, view);

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
          gameType = getString(R.string.time_game_string_key);
          MainActivity.getBundle().putString(getString(R.string.game_type_string_key), gameType);
          break;
        case R.id.difficulty_button:
          gameType = getString(R.string.sudden_game_string_key);
          MainActivity.getBundle().putString(getString(R.string.game_type_string_key), gameType);
          break;
        case R.id.relaxed_button:
          gameType = getString(R.string.relaxed_game_string_key);
          MainActivity.getBundle().putString(getString(R.string.game_type_string_key), gameType);
          break;
        default:
          gameType = getString(R.string.relaxed_game_string_key);
          MainActivity.getBundle().putString(getString(R.string.game_type_string_key), gameType);
          break;
      }

      categoriesFragment.setArguments(MainActivity.getBundle());
      getFragmentManager()
          .beginTransaction().replace(R.id.fragment_container, categoriesFragment)
          .addToBackStack(getString(R.string.main_fragment_tag))
          .commit();
    };
  }
}
