package edu.cnm.deepdive.triviagame.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import edu.cnm.deepdive.triviagame.R;
import edu.cnm.deepdive.triviagame.model.entity.TriviaAnswers;
import edu.cnm.deepdive.triviagame.model.entity.TriviaQuestion;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RelaxedGameFragment extends GameFragment {

  private int questionsCorrect;
  private int questionsIncorrect;
  private TextView questionText;
  private Button answers1;
  private Button answers2;
  private Button answers3;
  private Button answers4;
  private List<Button> answerButtons;
  private OnClickListener listener;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_relaxed_game, container, false);

    questionText = view.findViewById(R.id.relaxed_question_text);
    answers1 = view.findViewById(R.id.relaxed_answer_button1);
    answers2 = view.findViewById(R.id.relaxed_answer_button2);
    answers3 = view.findViewById(R.id.relaxed_answer_button3);
    answers4 = view.findViewById(R.id.relaxed_answer_button4);
    answers1.setText(String.valueOf(questions.size()));
    answers2.setText(String.valueOf(answers.size()));
    answerButtons = new ArrayList<>();
    answerButtons.add(answers1);
    answerButtons.add(answers2);
    answerButtons.add(answers3);
    answerButtons.add(answers4);

    //playGame();

    return view;
  }

//  private void playGame() {
//    boolean gameOver = false;
//    int questionIndex = 0;
//
//    while (!gameOver) {
//      long qId = questions.get(questionIndex).getQuestionId();
//      questionText.setText(questions.get(questionIndex).getQuestion());
//      int listIndex = 0;
//      for (TriviaAnswers answer : answers) {
//        if (answer.getQuestionId() == qId) {
//          answerButtons.get(listIndex).setText(answer.getAnswer());
//          listIndex++;
//        }
//      }
//      gameOver = true;
//    }
//  }

}
