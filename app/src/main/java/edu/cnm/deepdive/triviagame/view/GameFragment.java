package edu.cnm.deepdive.triviagame.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import edu.cnm.deepdive.triviagame.model.db.TriviaDatabase;
import edu.cnm.deepdive.triviagame.model.entity.TriviaAnswers;
import edu.cnm.deepdive.triviagame.model.entity.TriviaQuestion;
import java.util.ArrayList;
import java.util.List;

public class GameFragment extends Fragment {

  protected String category;
  protected String difficulty;
  protected String gameType;
  protected List<TriviaQuestion> questions;
  protected List<TriviaAnswers> answers;


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    gameType = getArguments().getString("gameType");
    category = getArguments().getString("category");
    difficulty = getArguments().getString("difficulty");

    questions = new ArrayList<>();
    answers = new ArrayList<>();

    questions = new QuestionTask().doInBackground();
    answers = new AnswerTask().doInBackground();
  }

  private class QuestionTask extends AsyncTask<Void, Void, List<TriviaQuestion>> {

    @Override
    protected List<TriviaQuestion> doInBackground(Void... voids) {
      TriviaDatabase db = TriviaDatabase.getInstance(getActivity());

      long catId = db.getTriviaCategoryDao().select(category).getCategoryId();

      return db.getTriviaQuestionDao().select(catId);
    }
  }

  private class AnswerTask extends AsyncTask<Void, Void, List<TriviaAnswers>> {

    @Override
    protected List<TriviaAnswers> doInBackground(Void... voids) {
      TriviaDatabase db = TriviaDatabase.getInstance(getActivity());

      List<TriviaAnswers> answers = new ArrayList<>();
      for (TriviaQuestion q : questions) {
        long queId = db.getTriviaQuestionDao().select(q).getQuestionId();
        answers.addAll(db.getTriviaAnswersDao().select(queId));
      }

      return answers;
    }
  }
}
