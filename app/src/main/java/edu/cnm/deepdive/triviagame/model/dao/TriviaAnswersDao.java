package edu.cnm.deepdive.triviagame.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.triviagame.model.entity.TriviaAnswers;
import java.util.List;

@Dao
public interface TriviaAnswersDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(TriviaAnswers answers);


  @Query("SELECT * FROM TriviaAnswers WHERE question_id = :questionId")
  List<TriviaAnswers> select(long questionId);
}
