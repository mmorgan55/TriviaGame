package edu.cnm.deepdive.triviagame.view;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import edu.cnm.deepdive.triviagame.R;

public class PostGameFragment extends Fragment {

  private TextView questionsCorrectText;
  private TextView gameGrade;
  private TextView gameGradeText;
  private Button mainMenuButton;
  private int totalQuestions;
  private int correctQuestions;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_post_game, container, false);

    questionsCorrectText = view.findViewById(R.id.questions_correct);
    gameGrade = view.findViewById(R.id.game_grade);
    gameGradeText = view.findViewById(R.id.game_grade_text);
    mainMenuButton = view.findViewById(R.id.main_menu_button);
    totalQuestions = getArguments().getInt("");
    correctQuestions = getArguments().getInt("");

    mainMenuButton.setOnClickListener(v -> {

    });

    return view;
  }

  private void setTextViews(double qCorrect, double tQuestions) {
    double grade = qCorrect/tQuestions;

  }
}
