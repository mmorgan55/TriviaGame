package edu.cnm.deepdive.triviagame.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import edu.cnm.deepdive.triviagame.R;
import edu.cnm.deepdive.triviagame.model.entity.TriviaAnswers;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is for the Sudden Death game type. Goes until the user goes through
 * all questions or gets one wrong. Keeps track of the amount the user has gotten
 * correct.
 */
public class ThreeLivesGameFragment extends GameFragment {

  @BindView(R.id.sudden_correct_tally)
  TextView correctTally;
  @BindView(R.id.sudden_lives_counter)
  TextView livesCounter;
  @BindView(R.id.sudden_question_text)
  TextView questionText;
  @BindView(R.id.sudden_text_button1)
  TextView answers1;
  @BindView(R.id.sudden_text_button2)
  TextView answers2;
  @BindView(R.id.sudden_text_button3)
  TextView answers3;
  @BindView(R.id.sudden_text_button4)
  TextView answers4;

  private int questionsCorrect;
  private int lives;
  private List<TextView> answerTexts;
  private int questionIndex;
  private OnClickListener listener;
  private TriviaAnswers correctAnswer;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_three_lives_game, container, false);
    ButterKnife.bind(this, view);

    answerTexts = new ArrayList<>();
    lives = 3;

    setListener();

    answers1.setOnClickListener(listener);
    answers2.setOnClickListener(listener);
    answers3.setOnClickListener(listener);
    answers4.setOnClickListener(listener);
    answerTexts.add(answers1);
    answerTexts.add(answers2);
    answerTexts.add(answers3);
    answerTexts.add(answers4);

    return view;
  }

  @Override
  protected void setupGame() {
    updateUI();
    updateLives(false);
  }

  private void updateUI() {
    updateTally(questionsCorrect, correctTally);
    long qId = questions.get(questionIndex).getQuestionId();
    questionText.setText(questions.get(questionIndex).getQuestion());
    int answerListIndex = 0;
    for (TriviaAnswers answer : answers) {
      if (answer.getQuestionId() == qId) {
        if (answer.isCorrect()) {
          correctAnswer = answer;
          answerTexts.get(answerListIndex).setText(answer.getAnswer());
          answerListIndex++;
        } else {
          answerTexts.get(answerListIndex).setText(answer.getAnswer());
          answerListIndex++;
        }
      }
    }
  }

  private void updateGame(boolean isCorrect) {
    if (isCorrect) {
      questionIndex++;
      questionsCorrect++;
      continueGame();
    } else {
      updateLives(true);
      if (lives == 0) {
        for (TextView text : answerTexts) {
          text.setEnabled(false);
        }
        moveToPostGame(questionsCorrect, getString(R.string.three_lives_string_key));
      } else {
        continueGame();
      }

    }
  }

  private void continueGame() {
    if (questionIndex < questions.size()) {
      updateUI();
    } else {
      updateTally(questionsCorrect, correctTally);
      for (TextView text : answerTexts) {
        text.setEnabled(false);
      }
      moveToPostGame(questionsCorrect, getString(R.string.three_lives_string_key));
    }
  }

  private void updateLives(boolean answerWrong) {
    if (answerWrong) {
      lives--;
    }

    livesCounter.setText(getString(R.string.lives_counter, lives));
  }

  private void setListener() {
    listener = v -> {
      switch (v.getId()) {
        case R.id.sudden_text_button1:
          updateGame(isAnswerCorrect(((TextView) v).getText().toString(), correctAnswer));
          break;
        case R.id.sudden_text_button2:
          updateGame(isAnswerCorrect(((TextView) v).getText().toString(), correctAnswer));
          break;
        case R.id.sudden_text_button3:
          updateGame(isAnswerCorrect(((TextView) v).getText().toString(), correctAnswer));
          break;
        case R.id.sudden_text_button4:
          updateGame(isAnswerCorrect(((TextView) v).getText().toString(), correctAnswer));
          break;
      }
    };
  }
}
