package edu.cnm.deepdive.triviagame.model.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import edu.cnm.deepdive.triviagame.model.entity.TriviaQuestion;
import java.util.List;

/**
 * This is the Data Access Object for the TriviaQuestions entity.
 */
@Dao
public interface TriviaQuestionDao {

  @Insert()
  long insert (TriviaQuestion question);

  @Query("SELECT * FROM TriviaQuestion WHERE category_id = :categoryId")
  List<TriviaQuestion> select(long categoryId);

  @Query("SELECT * FROM TriviaQuestion WHERE category_id = :categoryId AND difficulty = :difficulty")
  List<TriviaQuestion> select(long categoryId, String difficulty);

  @Query("SELECT * FROM TriviaQuestion WHERE question = :question")
  TriviaQuestion select(String question);

}
