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
import java.util.Timer;
import java.util.TimerTask;

public class TimeConstraintGameFragment extends GameFragment {

  private static final int TIMER_START_DELAY = 0;
  private static final int TIMER_PERIOD = 1000;

  private int questionsCorrect = 0;

  @BindView(R.id.time_question_text)
  TextView questionText;
  @BindView(R.id.time_correct_tally)
  TextView correctTally;
  @BindView(R.id.time_timer)
  TextView timerText;
  @BindView(R.id.time_answer_button1)
  Button answers1;
  @BindView(R.id.time_answer_button2)
  Button answers2;
  @BindView(R.id.time_answer_button3)
  Button answers3;
  @BindView(R.id.time_answer_button4)
  Button answers4;

  private Timer timer;
  private int currentTime = 60;
  private List<Button> answerButtons;
  private int questionIndex = 0;
  private OnClickListener listener;
  private TriviaAnswers correctAnswer;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_time_constraint_game, container, false);
    ButterKnife.bind(this, view);

    answerButtons = new ArrayList<>();
    timer = new Timer();

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
    setTaskAndTimer();
    updateUI();
  }


  private void updateGame(boolean isCorrect) {
    if (isCorrect) {
      questionIndex++;
      questionsCorrect++;
      currentTime += 5;
      Toast.makeText(getContext(), R.string.time_correct_text, Toast.LENGTH_LONG).show();
      continueGame();
    } else {
      currentTime -= 15;
      Toast.makeText(getContext(), R.string.time_incorrect_text, Toast.LENGTH_LONG).show();
      continueGame();
    }
  }

  private void continueGame() {
    if ((questionIndex < questions.size()) && currentTime > 0) {
      updateUI();
    } else {
      if (currentTime <= 0) {
        for (Button button : answerButtons) {
          button.setEnabled(false);
        }
        timerText.setText(getString(R.string.timer, currentTime));
        Toast.makeText(getContext(), R.string.time_lose_text, Toast.LENGTH_LONG).show();
      } else {
        for (Button button : answerButtons) {
          button.setEnabled(false);
        }
        timer.cancel();
        timer.purge();
        updateTally(questionsCorrect);
        timerText.setText(getString(R.string.timer, currentTime));
        Toast.makeText(getContext(), R.string.sudden_win_text, Toast.LENGTH_LONG).show();
      }
    }
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
          answerButtons.get(answerListIndex).setText(answer.getAnswer());
          answerListIndex++;
        } else {
          answerButtons.get(answerListIndex).setText(answer.getAnswer());
          answerListIndex++;
        }
      }
    }
  }

  private void setListener() {
    listener = v -> {
      switch (v.getId()) {
        case R.id.time_answer_button1:
          updateGame(isAnswerCorrect(((Button) v).getText().toString()));
          break;
        case R.id.time_answer_button2:
          updateGame(isAnswerCorrect(((Button) v).getText().toString()));
          break;
        case R.id.time_answer_button3:
          updateGame(isAnswerCorrect(((Button) v).getText().toString()));
          break;
        case R.id.time_answer_button4:
          updateGame(isAnswerCorrect(((Button) v).getText().toString()));
          break;
      }
    };
  }

  private void setTaskAndTimer() {
    TimerTask task = new TimerTask() {
      @Override
      public void run() {
        currentTime--;
        if (currentTime <= 0) {
          timer.cancel();
          timer.purge();
          getActivity().runOnUiThread(() -> continueGame());
          return;
        } else {
          getActivity()
              .runOnUiThread(() -> timerText.setText(getString(R.string.timer, currentTime)));
        }
      }
    };

    timer.scheduleAtFixedRate(task, TIMER_START_DELAY, TIMER_PERIOD);
  }

  private boolean isAnswerCorrect(String answer) {
    return correctAnswer.getAnswer().equals(answer);
  }

  private void updateTally(int correct) {
    correctTally.setText(getString(R.string.tally_correct, correct));
  }
}
