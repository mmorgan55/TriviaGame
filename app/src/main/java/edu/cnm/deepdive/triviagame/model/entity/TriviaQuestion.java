package edu.cnm.deepdive.triviagame.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;
import edu.cnm.deepdive.triviagame.model.entity.TriviaCategory;
import java.util.List;

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

  @NonNull
  @ColumnInfo(name = "difficulty")
  private String difficulty;

  @NonNull
  @ColumnInfo(name = "question")
  private String question;

  private String correctAnswer;
  private List<String> answers;

  public TriviaQuestion(String question, String correctAnswer, List<String> answers) {
    this.question = question;
    this.correctAnswer = correctAnswer;
    this.answers = answers;
    this.difficulty = "easy";
  }

  @NonNull
  public String getQuestion() {
    return question;
  }

  public String getCorrectAnswer() {
    return correctAnswer;
  }

  public List<String> getAnswers() {
    return answers;
  }

  @Override
  public String toString() {
    return getQuestion();
  }
}
