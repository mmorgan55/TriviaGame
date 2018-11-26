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
 * This class is for the Relaxed game type. Keeps track of how many questions
 * the user got correct/incorrect and only ends if all questions are answered
 * or the user goes back to the main menu.
 */
public class RelaxedGameFragment extends GameFragment {

  @BindView(R.id.relaxed_correct_tally)
  TextView correctTally;
  @BindView(R.id.relaxed_incorrect_tally)
  TextView incorrectTally;
  @BindView(R.id.relaxed_question_text)
  TextView questionText;
  @BindView(R.id.relaxed_text_button1)
  TextView answers1;
  @BindView(R.id.relaxed_text_button2)
  TextView answers2;
  @BindView(R.id.relaxed_text_button3)
  TextView answers3;
  @BindView(R.id.relaxed_text_button4)
  TextView answers4;

  private int questionsCorrect;
  private int questionsIncorrect;
  private List<TextView> answerTexts;
  private int questionIndex;
  private OnClickListener listener;
  private TriviaAnswers correctAnswer;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_relaxed_game, container, false);
    ButterKnife.bind(this, view);


    answerTexts = new ArrayList<>();
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
  }

  private void updateUI() {
    updateTally(questionsCorrect, questionsIncorrect, correctTally, incorrectTally);
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
    } else {
      questionIndex++;
      questionsIncorrect++;
    }

    if (questionIndex < questions.size()) {
      updateUI();
    } else {
      updateTally(questionsCorrect, questionsIncorrect, correctTally, incorrectTally);
      for (TextView text : answerTexts) {
        text.setEnabled(false);
      }
      moveToPostGame(questionsCorrect, getString(R.string.relaxed_game_string_key));
    }
  }

  private void setListener() {
    listener = v -> {
      switch (v.getId()) {
        case R.id.relaxed_text_button1:
          updateGame(isAnswerCorrect(((TextView) v).getText().toString(), correctAnswer));
          break;
        case R.id.relaxed_text_button2:
          updateGame(isAnswerCorrect(((TextView) v).getText().toString(), correctAnswer));
          break;
        case R.id.relaxed_text_button3:
          updateGame(isAnswerCorrect(((TextView) v).getText().toString(), correctAnswer));
          break;
        case R.id.relaxed_text_button4:
          updateGame(isAnswerCorrect(((TextView) v).getText().toString(), correctAnswer));
          break;
      }
    };
  }
}
