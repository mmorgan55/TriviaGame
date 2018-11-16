package edu.cnm.deepdive.triviagame.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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


public class SuddenDeathGameFragment extends GameFragment {

  @BindView(R.id.sudden_correct_tally)
  TextView correctTally;
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

  private int questionsCorrect = 0;
  private List<TextView> answerTexts;
  private int questionIndex = 0;
  private OnClickListener listener;
  private TriviaAnswers correctAnswer;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_sudden_death_game, container, false);
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
    updateTally(questionsCorrect);
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
      questionIndex++;
      updateTally(questionsCorrect);
      for (TextView text : answerTexts) {
        text.setEnabled(false);
      }
      Bundle bundle = new Bundle();
      bundle.putInt("correct", questionsCorrect);
      bundle.putInt("total", questions.size());
      Fragment fragment = new PostGameFragment();
      fragment.setArguments(bundle);
      getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }
  }

  private void continueGame() {
    if (questionIndex < questions.size()) {
      updateUI();
    } else {
      updateTally(questionsCorrect);
      for (TextView text : answerTexts) {
        text.setEnabled(false);
      }
      Bundle bundle = new Bundle();
      bundle.putInt("correct", questionsCorrect);
      bundle.putInt("total", questions.size());
      Fragment fragment = new PostGameFragment();
      fragment.setArguments(bundle);
      getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }
  }

  private void setListener() {
    listener = v -> {
      switch (v.getId()) {
        case R.id.sudden_text_button1:
          updateGame(isAnswerCorrect(((TextView) v).getText().toString()));
          break;
        case R.id.sudden_text_button2:
          updateGame(isAnswerCorrect(((TextView) v).getText().toString()));
          break;
        case R.id.sudden_text_button3:
          updateGame(isAnswerCorrect(((TextView) v).getText().toString()));
          break;
        case R.id.sudden_text_button4:
          updateGame(isAnswerCorrect(((TextView) v).getText().toString()));
          break;
      }
    };
  }

  private boolean isAnswerCorrect(String answer) {
    return correctAnswer.getAnswer().equals(answer);
  }

  private void updateTally(int correct) {
    correctTally.setText(getString(R.string.tally_correct, correct));
  }
}
