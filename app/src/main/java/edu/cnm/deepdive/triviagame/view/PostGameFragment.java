package edu.cnm.deepdive.triviagame.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import edu.cnm.deepdive.triviagame.R;

public class PostGameFragment extends android.support.v4.app.Fragment {

  @BindView(R.id.questions_correct)
  TextView questionsCorrectText;
  @BindView(R.id.high_score_text)
  TextView highScoreText;
  @BindView(R.id.game_grade)
  TextView gameGrade;
  @BindView(R.id.game_grade_text)
  TextView gameGradeText;
  @BindView(R.id.main_menu_button)
  Button mainMenuButton;

  int questionsCorrect;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_post_game, container, false);
    ButterKnife.bind(this, view);

    int totalQuestions = getArguments().getInt("total");
    questionsCorrect = getArguments().getInt("correct");
    String gameType = getArguments().getString("gameType");
    Fragment fragment = new MainFragment();

    mainMenuButton.setOnClickListener(v -> {
      getFragmentManager()
          .popBackStack("main", android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
      getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    });

    questionsCorrectText.setText(getString(R.string.questions_correct, questionsCorrect));
    getHighScore(gameType);
    setGrade(questionsCorrect, totalQuestions);

    return view;
  }

  private void setGrade(double qCorrect, double tQuestions) {
    double percent = qCorrect / tQuestions;

    String grade;

    if (percent >= .90) {
      grade = "A";
      gameGrade.setText(getString(R.string.game_grade, grade));
    } else if (percent >= .80 && percent <= .89) {
      grade = "B";
      gameGrade.setText(getString(R.string.game_grade, grade));
    } else if (percent >= .70 && percent <= .79) {
      grade = "C";
      gameGrade.setText(getString(R.string.game_grade, grade));
    } else if (percent >= .60 && percent <= .69) {
      grade = "D";
      gameGrade.setText(getString(R.string.game_grade, grade));
    } else if (percent >= .50 && percent <= .59) {
      grade = "F";
      gameGrade.setText(getString(R.string.game_grade, grade));
    } else {
      grade = "F-";
      gameGrade.setText(getString(R.string.game_grade, grade));
    }

    setGradeText(grade);
  }

  private void setGradeText(String grade) {

    String text;

    switch (grade) {
      case "A":
        text = "AWESOME";
        gameGradeText.setText(getString(R.string.game_grade_text, text));
        break;
      case "B":
        text = "NOT BAD";
        gameGradeText.setText(getString(R.string.game_grade_text, text));
        break;
      case "C":
        text = "THAT'LL GET YOU A DEGREE";
        gameGradeText.setText(getString(R.string.game_grade_text, text));
        break;
      case "D":
        text = "I THINK YOU TRIED";
        gameGradeText.setText(getString(R.string.game_grade_text, text));
        break;
      case "F":
        text = "GO BACK TO SCHOOL";
        gameGradeText.setText(getString(R.string.game_grade_text, text));
        break;
      case "F-":
        text = "AHAHAHAHAHAHA";
        gameGradeText.setText(getString(R.string.game_grade_text, text));
        break;
      default:
        text = "this didn't work";
        gameGradeText.setText(getString(R.string.game_grade_text, text));
        break;
    }
  }

  private void getHighScore(String gameType) {
    switch (gameType) {
      case "relaxed":
        highScoreText.setText(getString(R.string.relaxed_high_score,
            checkHighScore(R.string.relaxed_high_score_key)));
        break;
      case "sudden":
        highScoreText.setText(getString(R.string.sudden_high_score,
            checkHighScore(R.string.sudden_high_score_key)));
        break;
      case "time":
        highScoreText.setText(getString(R.string.time_high_score,
            checkHighScore(R.string.time_high_score_key)));
        break;
    }
  }

  private int checkHighScore(int stringId) {
    SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
    if (questionsCorrect > sharedPref.getInt(getString(stringId), 0)) {
      SharedPreferences.Editor editor = sharedPref.edit();
      editor.putInt(getString(stringId), questionsCorrect);
      editor.apply();
    }
    return sharedPref.getInt(getString(stringId), 0);
  }
}
