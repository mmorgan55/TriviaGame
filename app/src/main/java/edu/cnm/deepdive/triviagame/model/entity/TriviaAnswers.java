package edu.cnm.deepdive.triviagame.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;
import java.util.List;

@Entity(
    primaryKeys = {"answers_id", "correct_answer", "answers"},
    foreignKeys = {
        @ForeignKey(
            entity = TriviaQuestion.class,
            parentColumns = "question_id",
            childColumns = "question_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class TriviaAnswers {
  @ColumnInfo(name = "answers_id")
  private long answersId;

  @NonNull
  @ColumnInfo(name = "correct_answer")
  private String correctAnswer;

  @NonNull
  @ColumnInfo(name = "answers")
  private List<String> answers;

  public TriviaAnswers(@NonNull String correctAnswer, @NonNull List<String> answers) {
    this.correctAnswer = correctAnswer;
    this.answers = answers;
  }

  public long getAnswersId() {
    return answersId;
  }

  public void setAnswersId(long answersId) {
    this.answersId = answersId;
  }

  @NonNull
  public String getCorrectAnswer() {
    return correctAnswer;
  }

  public void setCorrectAnswer(@NonNull String correctAnswer) {
    this.correctAnswer = correctAnswer;
  }

  @NonNull
  public List<String> getAnswers() {
    return answers;
  }

  public void setAnswers(@NonNull List<String> answers) {
    this.answers = answers;
  }
}
