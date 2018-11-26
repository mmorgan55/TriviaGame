package edu.cnm.deepdive.triviagame.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * This is the entity class for an answer to a TriviaQuestion object. Each
 * TriviaAnswers object is associated with exactly one question.
 */
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

  @ColumnInfo(name = "is_correct")
  private boolean isCorrect;

  /**
   * Initializes a TriviaAnswer object and associates it with a TriviaQuestion
   * object.
   * @param answer The text of the answer.
   * @param isCorrect Whether or not the answer is correct. True if correct, false otherwise.
   * @param questionId The id of the question it is associated with.
   */
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