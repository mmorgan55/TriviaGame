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
import edu.cnm.deepdive.triviagame.R;
import edu.cnm.deepdive.triviagame.model.entity.TriviaAnswers;
import java.util.ArrayList;
import java.util.List;

public class RelaxedGameFragment extends GameFragment {

  private int questionsCorrect = 0;
  private int questionsIncorrect = 0;
  private TextView correctTally;
  private TextView incorrectTally;
  private TextView questionText;
  private List<Button> answerButtons;
  private int questionIndex = 0;
  private OnClickListener listener;
  private TriviaAnswers correctAnswer;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_relaxed_game, container, false);

    questionText = view.findViewById(R.id.relaxed_question_text);
    correctTally = view.findViewById(R.id.relaxed_correct_tally);
    incorrectTally = view.findViewById(R.id.relaxed_incorrect_tally);
    Button answers1 = view.findViewById(R.id.relaxed_answer_button1);
    Button answers2 = view.findViewById(R.id.relaxed_answer_button2);
    Button answers3 = view.findViewById(R.id.relaxed_answer_button3);
    Button answers4 = view.findViewById(R.id.relaxed_answer_button4);
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
    } else {
      questionIndex++;
      questionsIncorrect++;
    }

    if (questionIndex < questions.size()) {
      updateUI();
    } else {
      updateTally(questionsCorrect, questionsIncorrect);
      for (Button button : answerButtons) {
        button.setEnabled(false);
      }
      Toast.makeText(getContext(), R.string.relaxed_win_text, Toast.LENGTH_LONG).show();
    }
  }

  private void setListener() {
    listener = v -> {
      switch (v.getId()) {
        case R.id.relaxed_answer_button1:
          updateGame(isAnswerCorrect(((Button) v).getText().toString()));
          break;
        case R.id.relaxed_answer_button2:
          updateGame(isAnswerCorrect(((Button) v).getText().toString()));
          break;
        case R.id.relaxed_answer_button3:
          updateGame(isAnswerCorrect(((Button) v).getText().toString()));
          break;
        case R.id.relaxed_answer_button4:
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
