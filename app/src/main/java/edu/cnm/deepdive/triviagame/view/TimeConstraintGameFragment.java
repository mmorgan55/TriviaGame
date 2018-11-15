package edu.cnm.deepdive.triviagame.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
  @BindView(R.id.time_text_button1)
  TextView answers1;
  @BindView(R.id.time_text_button2)
  TextView answers2;
  @BindView(R.id.time_text_button3)
  TextView answers3;
  @BindView(R.id.time_text_button4)
  TextView answers4;

  private Timer timer;
  private int currentTime = 60;
  private List<TextView> answerTexts;
  private int questionIndex = 0;
  private OnClickListener listener;
  private TriviaAnswers correctAnswer;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_time_constraint_game, container, false);
    ButterKnife.bind(this, view);

    answerTexts = new ArrayList<>();
    timer = new Timer();

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
        for (TextView text : answerTexts) {
          text.setEnabled(false);
        }
        timer.cancel();
        timer.purge();
        timerText.setText(getString(R.string.timer, currentTime));
        Bundle bundle = new Bundle();
        bundle.putInt("correct", questionsCorrect);
        bundle.putInt("total", questions.size());
        Fragment fragment = new PostGameFragment();
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
      } else {
        for (TextView text : answerTexts) {
          text.setEnabled(false);
        }
        timer.cancel();
        timer.purge();
        updateTally(questionsCorrect);
        timerText.setText(getString(R.string.timer, currentTime));
        Bundle bundle = new Bundle();
        bundle.putInt("correct", questionsCorrect);
        bundle.putInt("total", questions.size());
        Fragment fragment = new PostGameFragment();
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
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
          answerTexts.get(answerListIndex).setText(answer.getAnswer());
          answerListIndex++;
        } else {
          answerTexts.get(answerListIndex).setText(answer.getAnswer());
          answerListIndex++;
        }
      }
    }
  }

  private void setListener() {
    listener = v -> {
      switch (v.getId()) {
        case R.id.time_text_button1:
          updateGame(isAnswerCorrect(((TextView) v).getText().toString()));
          break;
        case R.id.time_text_button2:
          updateGame(isAnswerCorrect(((TextView) v).getText().toString()));
          break;
        case R.id.time_text_button3:
          updateGame(isAnswerCorrect(((TextView) v).getText().toString()));
          break;
        case R.id.time_text_button4:
          updateGame(isAnswerCorrect(((TextView) v).getText().toString()));
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
