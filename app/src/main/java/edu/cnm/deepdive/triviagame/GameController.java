package edu.cnm.deepdive.triviagame;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import edu.cnm.deepdive.triviagame.view.GameFragment;
import edu.cnm.deepdive.triviagame.view.RelaxedGameFragment;
import edu.cnm.deepdive.triviagame.view.SuddenDeathGameFragment;
import edu.cnm.deepdive.triviagame.view.TimeConstraintGameFragment;

public class GameController {

  public GameController(FragmentActivity context, Bundle bundle)  {
    GameFragment fragment;

    String gameType = bundle.getString("gameType");

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
