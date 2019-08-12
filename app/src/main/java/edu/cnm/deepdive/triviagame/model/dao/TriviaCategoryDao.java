package edu.cnm.deepdive.triviagame.model.dao;

import androidx.room.Query;
import androidx.room.Dao;
import androidx.room.Insert;
import edu.cnm.deepdive.triviagame.model.entity.TriviaCategory;
import java.util.List;

/**
 * This is the Data Access Object for the TriviaCategory Entity.
 */
@Dao
public interface TriviaCategoryDao {

  @Insert
  long insert (TriviaCategory category);

  @Query("SELECT * FROM TriviaCategory ORDER BY category_id")
  List<TriviaCategory> select();

  @Query("SELECT * FROM TriviaCategory WHERE category_title =:categoryTitle")
  TriviaCategory select(String categoryTitle);

  @Query("SELECT category_title FROM TriviaCategory ORDER BY category_id")
  List<String> allTitles();
}
