package edu.cnm.deepdive.triviagame.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import edu.cnm.deepdive.triviagame.model.db.TriviaDatabase;
import edu.cnm.deepdive.triviagame.model.entity.TriviaAnswers;
import edu.cnm.deepdive.triviagame.model.entity.TriviaCategory;
import edu.cnm.deepdive.triviagame.model.entity.TriviaQuestion;
import java.util.ArrayList;
import java.util.List;

public abstract class GameFragment extends Fragment {

  protected String category = "";
  protected String difficulty = "";
  protected List<TriviaQuestion> questions;
  protected List<TriviaAnswers> answers;


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    category = getArguments().getString("category");
    difficulty = getArguments().getString("difficulty");

    questions = new ArrayList<>();
    answers = new ArrayList<>();

    new QuestionTask().execute();
  }

  protected abstract void setupGame();

  private class QuestionTask extends AsyncTask<Void, Void, List<TriviaQuestion>> {

    QuestionTask() {
      super();
    }

    @Override
    protected List<TriviaQuestion> doInBackground(Void... voids) {
      TriviaDatabase db = TriviaDatabase.getInstance(getActivity());

      TriviaCategory triviaCategory = db.getTriviaCategoryDao().select(category);

      if (triviaCategory != null && difficulty.equals("all")) {
        long catId = triviaCategory.getCategoryId();
        questions.addAll(db.getTriviaQuestionDao().select(catId));

        return null;
      } else if (triviaCategory != null) {
        long catId = triviaCategory.getCategoryId();
        questions.addAll(db.getTriviaQuestionDao().select(catId, difficulty));

        return null;
      }

      return null;
    }

    @Override
    protected void onPostExecute(List<TriviaQuestion> triviaQuestions) {
      new AnswerTask().execute();
    }
  }


  private class AnswerTask extends AsyncTask<Void, Void, List<TriviaAnswers>> {

    AnswerTask() {
      super();
    }

    @Override
    protected List<TriviaAnswers> doInBackground(Void... voids) {
      TriviaDatabase db = TriviaDatabase.getInstance(getActivity());

      for (TriviaQuestion q : questions) {
        long queId = db.getTriviaQuestionDao().select(q.getQuestion()).getQuestionId();
        answers.addAll(db.getTriviaAnswersDao().select(queId));
      }

      return answers;
    }

    @Override
    protected void onPostExecute(List<TriviaAnswers> triviaAnswers) {
      setupGame();
    }
  }
}
