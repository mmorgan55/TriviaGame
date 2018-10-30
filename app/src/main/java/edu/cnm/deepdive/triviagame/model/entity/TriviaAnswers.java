package edu.cnm.deepdive.triviagame.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(
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

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "answers_id")
  private long answersId;

  @ColumnInfo(name = "question_id")
  private long questionId;

  @NonNull
  @ColumnInfo(name = "answer")
  private String answer;

  @ColumnInfo
  private boolean isCorrect;

  public TriviaAnswers(@NonNull String answer, boolean isCorrect, long questionId) {
    this.answer = answer;
    this.isCorrect = isCorrect;
    this.questionId = questionId;
  }

  public long getAnswersId() {
    return answersId;
  }

  public void setAnswersId(long answersId) {
    this.answersId = answersId;
  }

  @NonNull
  public String getAnswer() {
    return answer;
  }

  public void setAnswer(@NonNull String correctAnswer) {
    this.answer = correctAnswer;
  }

  public boolean isCorrect() {
    return isCorrect;
  }

  public void setCorrect(boolean correct) {
    isCorrect = correct;
  }

  public long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(long questionId) {
    this.questionId = questionId;
  }
}