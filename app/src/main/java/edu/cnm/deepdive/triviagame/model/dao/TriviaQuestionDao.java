package edu.cnm.deepdive.triviagame.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.triviagame.model.entity.TriviaQuestion;
import java.util.List;

/**
 * This is the Data Access Object for the TriviaQuestions entity.
 */
@Dao
public interface TriviaQuestionDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert (TriviaQuestion question);

  @Query("SELECT * FROM TriviaQuestion WHERE category_id = :categoryId")
  List<TriviaQuestion> select(long categoryId);

  @Query("SELECT * FROM TriviaQuestion WHERE category_id = :categoryId AND difficulty = :difficulty")
  List<TriviaQuestion> select(long categoryId, String difficulty);

  @Query("SELECT * FROM TriviaQuestion WHERE question = :question")
  TriviaQuestion select(String question);

}
