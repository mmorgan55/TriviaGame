package edu.cnm.deepdive.triviagame.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import edu.cnm.deepdive.triviagame.R;
import edu.cnm.deepdive.triviagame.model.entity.TriviaAnswers;
import java.util.ArrayList;
import java.util.List;


public class SuddenDeathGameFragment extends GameFragment {

  @BindView(R.id.sudden_correct_tally)
  TextView correctTally;
  @BindView(R.id.sudden_incorrect_tally)
  TextView incorrectTally;
  @BindView(R.id.sudden_question_text)
  TextView questionText;
  @BindView(R.id.sudden_answer_button1)
  Button answers1;
  @BindView(R.id.sudden_answer_button2)
  Button answers2;
  @BindView(R.id.sudden_answer_button3)
  Button answers3;
  @BindView(R.id.sudden_answer_button4)
  Button answers4;

  private int questionsCorrect = 0;
  private int questionsIncorrect = 0;
  private List<Button> answerButtons;
  private int questionIndex = 0;
  private OnClickListener listener;
  private TriviaAnswers correctAnswer;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_sudden_death_game, container, false);
    ButterKnife.bind(this, view);

    answerButtons = new ArrayList<>();

    setListener();

    answers1.setOnClickListener(listener);
    answers2.setOnClickListener(listener);
    answers3.setOnClickListener(listener);
    answers4.setOnClickListener(listener);
    answerButtons.add(answers1);
    answerButtons.add(answers2);
    answerButtons.add(answers3);
    answerButtons.add(answers4);

    return view;
  }

  @Override
  protected void setupGame() {
    updateUI();
  }

  private void updateUI() {
    updateTally(questionsCorrect, questionsIncorrect);
    long qId = questions.get(questionIndex).getQuestionId();
    questionText.setText(questions.get(questionIndex).getQuestion());
    int answerListIndex = 0;
    for (TriviaAnswers answer : answers) {
      if (answer.getQuestionId() == qId) {
        if (answer.isCorrect()) {
          correctAnswer = answer;
          answerButtons.get(answerListIndex).setText(answer.getAnswer());
          answerListIndex++;
        } else {
          answerButtons.get(answerListIndex).setText(answer.getAnswer());
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
      questionIndex++;
      questionsIncorrect++;
      updateTally(questionsCorrect, questionsIncorrect);
      for (Button button : answerButtons) {
        button.setEnabled(false);
      }
      Toast.makeText(getContext(), R.string.sudden_lose_text, Toast.LENGTH_LONG).show();
    }
  }

  private void continueGame() {
    if (questionIndex < questions.size()) {
      updateUI();
    } else {
      updateTally(questionsCorrect, questionsIncorrect);
      for (Button button : answerButtons) {
        button.setEnabled(false);
      }
      Toast.makeText(getContext(), R.string.sudden_win_text, Toast.LENGTH_LONG).show();
    }
  }

  private void setListener() {
    listener = v -> {
      switch (v.getId()) {
        case R.id.sudden_answer_button1:
          updateGame(isAnswerCorrect(((Button) v).getText().toString()));
          break;
        case R.id.sudden_answer_button2:
          updateGame(isAnswerCorrect(((Button) v).getText().toString()));
          break;
        case R.id.sudden_answer_button3:
          updateGame(isAnswerCorrect(((Button) v).getText().toString()));
          break;
        case R.id.sudden_answer_button4:
          updateGame(isAnswerCorrect(((Button) v).getText().toString()));
          break;
      }
    };
  }

  private boolean isAnswerCorrect(String answer) {
    return correctAnswer.getAnswer().equals(answer);
  }

  private void updateTally(int correct, int incorrect) {
    correctTally.setText(getString(R.string.tally_correct, correct));
    incorrectTally.setText(getString(R.string.tally_incorrect, incorrect));
  }
}
