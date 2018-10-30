package edu.cnm.deepdive.triviagame;

import android.arch.persistence.room.Query;
import android.os.AsyncTask;
import edu.cnm.deepdive.triviagame.model.db.TriviaDatabase;
import edu.cnm.deepdive.triviagame.model.entity.TriviaQuestion;

import edu.cnm.deepdive.triviagame.view.CategoriesFragment;
import java.util.List;

public class GameController {

  private String category = "Initial";
  private String difficulty = "easy";
  private String gameType = "relaxed";
  private List<TriviaQuestion> questionList;

//TODO Get rid of this after testing each gamemode
  public GameController() {
   this("Relaxed", "easy", "Initial");
  }

  public GameController(String gameType, String difficulty, String category) {

    this.gameType = gameType;
    this.difficulty = difficulty;
    this.category = category;

    switch (gameType) {
      case "relaxed":
        new RelaxedGame(questionList);
        break;
      case "sudden":
        new SuddenDeathGame(questionList);
        break;
      case "time":
        new TimeConstraintGame(questionList);
        break;
      default:
        new RelaxedGame(questionList);
        break;
    }
  }

  private class QuestionTask extends AsyncTask<Void, Void, List<TriviaQuestion>> {

    //TODO Implement this after working on RelaxedGameFragment
    @Override
    protected List<TriviaQuestion> doInBackground(Void... voids) {
      TriviaDatabase db = TriviaDatabase.getInstance(null);


      return null;
    }
  }
}
