package edu.cnm.deepdive.triviagame;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import edu.cnm.deepdive.triviagame.model.entity.TriviaQuestion;

import edu.cnm.deepdive.triviagame.view.GameFragment;
import edu.cnm.deepdive.triviagame.view.RelaxedGameFragment;
import edu.cnm.deepdive.triviagame.view.SuddenDeathGameFragment;
import edu.cnm.deepdive.triviagame.view.TimeConstraintGameFragment;
import java.util.List;

public class GameController {

  private String category = "Initial";
  private Context context;
  private String difficulty = "easy";
  private String gameType = "relaxed";
  private List<TriviaQuestion> questionList;
  private List<TriviaQuestion> answersList;

  //TODO Get rid of this after testing each gamemode
  public GameController(FragmentActivity context) {
    this("relaxed", "easy", "Initial", context);
  }

  public GameController(String gameType, String difficulty, String category,
      FragmentActivity context) {

    this.gameType = gameType;
    this.difficulty = difficulty;
    this.category = category;
    this.context = context;

    Bundle bundle = new Bundle();
    bundle.putString("gameType", gameType);
    bundle.putString("category", category);
    bundle.putString("difficulty", difficulty);

    GameFragment fragment;

    switch (gameType) {
      case "relaxed":
        fragment = new RelaxedGameFragment();
        break;
      case "sudden":
        fragment = new SuddenDeathGameFragment();
        break;
      case "time":
        fragment = new TimeConstraintGameFragment();
        break;
      default:
        fragment = new RelaxedGameFragment();
        break;
    }

    fragment.setArguments(bundle);

    context.getSupportFragmentManager().beginTransaction()
        .replace(R.id.fragment_container, fragment).commit();
  }
}
