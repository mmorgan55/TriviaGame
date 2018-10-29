package edu.cnm.deepdive.triviagame.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import edu.cnm.deepdive.triviagame.model.entity.TriviaAnswers;
import edu.cnm.deepdive.triviagame.model.entity.TriviaCategory;
import edu.cnm.deepdive.triviagame.model.entity.TriviaQuestion;

@Database(
    entities = {TriviaCategory.class, TriviaQuestion.class, TriviaAnswers.class},
    version = 1,
    exportSchema = true
)
public abstract class TriviaDatabase extends RoomDatabase {

  private static final String DATABASE_NAME = "trivia_game_db";

  private static TriviaDatabase instance = null;
}
