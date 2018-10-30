package edu.cnm.deepdive.triviagame.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import edu.cnm.deepdive.triviagame.R;

public class RelaxedGameFragment extends GameFragment {

  //private final int TOTAL_QUESTIONS = questions.size();

  private int questionsCorrect;
  private int questionsIncorrect;
  private TextView questionText;
  private Button answers1;
  private Button answers2;
  private Button answers3;
  private Button answers4;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_relaxed_game, container, false);

    questionText = view.findViewById(R.id.relaxed_question_text);
    answers1 = view.findViewById(R.id.relaxed_answer_button1);
    answers2 = view.findViewById(R.id.relaxed_answer_button2);
    answers3 = view.findViewById(R.id.relaxed_answer_button3);
    answers4 = view.findViewById(R.id.relaxed_answer_button4);

    return view;
  }

}
