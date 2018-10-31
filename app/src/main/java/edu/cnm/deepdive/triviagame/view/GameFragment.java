package edu.cnm.deepdive.triviagame.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import edu.cnm.deepdive.triviagame.R;
import edu.cnm.deepdive.triviagame.model.db.TriviaDatabase;
import edu.cnm.deepdive.triviagame.model.entity.TriviaAnswers;
import edu.cnm.deepdive.triviagame.model.entity.TriviaCategory;
import edu.cnm.deepdive.triviagame.model.entity.TriviaQuestion;
import java.util.ArrayList;
import java.util.List;

public abstract class GameFragment extends Fragment {

  protected String category = "";
  protected String difficulty = "";
  protected String gameType = "";
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

    new QuestionTask().execute();
    new AnswerTask().execute();

  }

  private class QuestionTask extends AsyncTask<Void, Void, List<TriviaQuestion>> {

    @Override
    protected List<TriviaQuestion> doInBackground(Void... voids) {
      TriviaDatabase db = TriviaDatabase.getInstance(getActivity());

      TriviaCategory triviaCategory = db.getTriviaCategoryDao().select(category);
      if (triviaCategory != null) {
        long catId = triviaCategory.getCategoryId();
        return db.getTriviaQuestionDao().select(catId);
      }

      return null;
    }
  }

  private class AnswerTask extends AsyncTask<Void, Void, List<TriviaAnswers>> {

    @Override
    protected List<TriviaAnswers> doInBackground(Void... voids) {
      TriviaDatabase db = TriviaDatabase.getInstance(getActivity());

      List<TriviaAnswers> answers = new ArrayList<>();
      for (TriviaQuestion q : questions) {
        long queId = db.getTriviaQuestionDao().select(q.getQuestion()).getQuestionId();
        answers.addAll(db.getTriviaAnswersDao().select(queId));
      }

      return answers;
    }
  }
}
