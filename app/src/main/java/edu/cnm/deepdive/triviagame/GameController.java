package edu.cnm.deepdive.triviagame;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import edu.cnm.deepdive.triviagame.model.db.TriviaDatabase;
import edu.cnm.deepdive.triviagame.model.entity.TriviaQuestion;

import edu.cnm.deepdive.triviagame.view.GameFragment;
import java.util.List;

public class GameController {

  private String category = "Initial";
  private Context context;
  private String difficulty = "easy";
  private String gameType = "relaxed";
  private List<TriviaQuestion> questionList;
  private List<TriviaQuestion> answersList;

//TODO Get rid of this after testing each gamemode
  public GameController(Context context) {
   this("Relaxed", "easy", "Initial", context);
  }

  public GameController(String gameType, String difficulty, String category, Context context) {

    this.gameType = gameType;
    this.difficulty = difficulty;
    this.category = category;
    this.context = context;

    Bundle bundle = new Bundle();
    bundle.putString("gameType", gameType);
    bundle.putString("category", category);
    bundle.putString("difficulty", difficulty);

    new GameFragment().setArguments(bundle);
//    switch (gameType) {
//      case "relaxed":
//        Bundle bundle = new Bundle();
//        bundle.putString("category", category);
//        bundle.putString("difficulty", difficulty);
//        new RelaxedGameFragment().setArguments(bundle);
//        break;
//      case "sudden":
//        new SuddenDeathGame(questionList);
//        break;
//      case "time":
//        new TimeConstraintGame(questionList);
//        break;
//      default:
//        new RelaxedGame(questionList);
//        break;
//    }
  }

  private class QuestionTask extends AsyncTask<Void, Void, List<TriviaQuestion>> {

    //TODO Implement this after working on RelaxedGameFragment
    @Override
    protected List<TriviaQuestion> doInBackground(Void... voids) {
      TriviaDatabase db = TriviaDatabase.getInstance(context);



      return null;
    }
  }
}
