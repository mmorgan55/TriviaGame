package edu.cnm.deepdive.triviagame.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import edu.cnm.deepdive.triviagame.model.entity.TriviaAnswers;
import java.util.List;

/**
 * This is the Dao for the TriviaAnswers entity.
 */
@Dao
public interface TriviaAnswersDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(TriviaAnswers answers);


  @Query("SELECT * FROM TriviaAnswers WHERE question_id = :questionId")
  List<TriviaAnswers> select(long questionId);
}
