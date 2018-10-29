package edu.cnm.deepdive.triviagame.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

@Entity(
    primaryKeys = {"question_id", "difficulty", "question"},
    foreignKeys = {
        @ForeignKey(
            entity = TriviaCategory.class,
            parentColumns = "category_id",
            childColumns = "category_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class TriviaQuestion {

  @ColumnInfo(name = "question_id")
  private long questionId;

  @ColumnInfo(name = "category_id")
  private long categoryId;

  @NonNull
  @ColumnInfo(name = "difficulty")
  private String difficulty;

  @NonNull
  @ColumnInfo(name = "question")
  private String question;


  public TriviaQuestion(String question, String difficulty, long category_id) {
    this.question = question;
    this.categoryId = category_id;
    this.difficulty = "easy";
  }

  @NonNull
  public String getQuestion() {
    return question;
  }

  @Override
  public String toString() {
    return getQuestion();
  }
}
