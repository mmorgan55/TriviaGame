package edu.cnm.deepdive.triviagame.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(
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

  @PrimaryKey(autoGenerate = true)
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

  public TriviaQuestion() {
  }

  public TriviaQuestion(String question, String difficulty, long category_id) {
    this.question = question;
    this.categoryId = category_id;
    this.difficulty = difficulty;
  }

  @NonNull
  public String getQuestion() {
    return question;
  }

  @Override
  public String toString() {
    return getQuestion();
  }

  public long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(long questionId) {
    this.questionId = questionId;
  }

  public long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
  }

  @NonNull
  public String getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(@NonNull String difficulty) {
    this.difficulty = difficulty;
  }

  public void setQuestion(@NonNull String question) {
    this.question = question;
  }
}
