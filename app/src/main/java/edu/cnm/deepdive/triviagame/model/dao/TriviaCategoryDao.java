package edu.cnm.deepdive.triviagame.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.triviagame.model.entity.TriviaCategory;
import java.util.List;

@Dao
public interface TriviaCategoryDao {

  @Insert
  long insert (TriviaCategory category);

  @Query("SELECT * FROM TriviaCategory ORDER BY category_id")
  List<TriviaCategory> select();
}
