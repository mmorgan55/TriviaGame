package edu.cnm.deepdive.triviagame.view;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import edu.cnm.deepdive.triviagame.R;
import edu.cnm.deepdive.triviagame.model.dao.TriviaAnswersDao;
import edu.cnm.deepdive.triviagame.model.dao.TriviaCategoryDao;
import edu.cnm.deepdive.triviagame.model.dao.TriviaQuestionDao;
import edu.cnm.deepdive.triviagame.model.db.TriviaDatabase;
import edu.cnm.deepdive.triviagame.model.entity.TriviaAnswers;
import edu.cnm.deepdive.triviagame.model.entity.TriviaCategory;
import edu.cnm.deepdive.triviagame.model.entity.TriviaQuestion;
import edu.cnm.deepdive.triviagame.model.pojo.TriviaPojo;
import edu.cnm.deepdive.triviagame.model.pojo.TriviaPojo.TriviaResult;
import edu.cnm.deepdive.triviagame.service.TriviaService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.text.StringEscapeUtils;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * This class is for adding new categories to the game. It uses Retrofit to make
 * the api call. Once the call is made, it uses the created TriviaPojo object
 * to put the questions into the database.
 */
public class AddCategoriesFragment extends DialogFragment {

  private static final Integer QUESTIONS_REQUESTED = 50;
  private static final String QUESTIONS_TYPE = "multiple";

  @BindView(R.id.add_category_list_view)
  ListView addList;
  @BindView(R.id.progress_spinner)
  ProgressBar progressSpinner;

  private List<TriviaCategory> prevAddedCategories;
  private Map<String, Integer> categoryMap;
  private String categoryName;
  private List<TriviaCategory> categories;
  private AddCategoryAdapter adapter;
  private CategoriesFragment categoriesFragment;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_add_categories, container, false);
    ButterKnife.bind(this, view);
    initialize();
    new CheckAddCategoriesTask().execute();

    return view;
  }

  @Override
  public void onStop() {
    super.onStop();
    if (categoriesFragment != null) {
      categoriesFragment.refreshData();
    }
  }

  private void setItemListener() {
    addList.setOnItemClickListener((parent, view, position, id) -> {
      categoryName = addList.getItemAtPosition(position).toString();
      new AddCategoryTask().execute(categoryMap.get(categoryName), categoryName);
    });
  }

  private void setMap() {
    categoryMap.put("Books", 10);
    categoryMap.put("Movies", 11);
    categoryMap.put("Music", 12);
    categoryMap.put("TV Shows", 14);
    categoryMap.put("Videogames", 15);
    categoryMap.put("Science & Nature", 17);
    categoryMap.put("Computers", 18);
    categoryMap.put("Sports", 21);
    categoryMap.put("Geography", 22);
    categoryMap.put("History", 23);
    categoryMap.put("Vehicles", 28);
    categoryMap.put("Anime & Manga", 31);
    categoryMap.put("Cartoons", 32);

    for (TriviaCategory tc : prevAddedCategories) {
      categoryMap.remove(tc.getCategoryTitle());
    }
  }

  private void updateMap(List<TriviaCategory> prevAddedCategories, String categoryName) {
    prevAddedCategories.add(new TriviaCategory(categoryName));

    for (TriviaCategory tc : prevAddedCategories) {
      categoryMap.remove(tc.getCategoryTitle());
    }

    for (String str : categoryMap.keySet()) {
      categories.add(new TriviaCategory(str));
    }
  }

  void setCategoriesFragment(CategoriesFragment categoriesFragment) {
    this.categoriesFragment = categoriesFragment;
  }

  private void initialize() {
    categories = new ArrayList<>();
    prevAddedCategories = new ArrayList<>();
    progressSpinner.setVisibility(View.GONE);
    categoryMap = new HashMap<>();
  }

  private class AddCategoryAdapter extends ArrayAdapter<TriviaCategory> {

    AddCategoryAdapter(@NonNull Context context, int resource,
        @NonNull List<TriviaCategory> objects) {
      super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      return super.getView(position, convertView, parent);
    }
  }

  private class AddCategoryTask extends AsyncTask<Object, Void, TriviaPojo> {

    @Override
    protected void onPreExecute() {
      progressSpinner.setVisibility(View.VISIBLE);
    }

    @Override
    protected TriviaPojo doInBackground(Object... params) {
      TriviaPojo pojo = null;
      TriviaDatabase db = TriviaDatabase.getInstance(getActivity());
      Retrofit retrofit = new Builder()
          .baseUrl(getString(R.string.base_url))
          .addConverterFactory(MoshiConverterFactory.create())
          .build();

      TriviaService service = retrofit.create(TriviaService.class);
      try {
        Response<TriviaPojo> response = service
            .get(QUESTIONS_REQUESTED, (int) params[0], QUESTIONS_TYPE).execute();
        if (response.isSuccessful()) {
          pojo = response.body();
          TriviaCategoryDao cDao = db.getTriviaCategoryDao();
          TriviaQuestionDao qDao = db.getTriviaQuestionDao();
          TriviaAnswersDao aDao = db.getTriviaAnswersDao();

          long catId = cDao.insert(new TriviaCategory((String) params[1]));

          for (TriviaResult result : pojo.getResults()) {
            long queId = qDao
                .insert(new TriviaQuestion(StringEscapeUtils.unescapeHtml4(result.getQuestion()),
                    result.getDifficulty(), catId));
            aDao.insert(
                new TriviaAnswers(StringEscapeUtils.unescapeHtml4(result.getCorrectAnswer()), true,
                    queId));
            for (String s : result.getIncorrectAnswers()) {
              s = StringEscapeUtils.unescapeHtml4(s);
              aDao.insert(new TriviaAnswers(s, false, queId));
            }
          }
        }
      } catch (IOException e) {
        //Do nothing; pojo is already null;
        throw new RuntimeException(e);
      } finally {
        if (pojo == null) {
          cancel(true);
        }
      }
      return null;
    }

    @Override
    protected void onPostExecute(TriviaPojo triviaPojo) {
      progressSpinner.setVisibility(View.GONE);
      categories.clear();
      updateMap(prevAddedCategories, categoryName);
      adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCancelled() {
      progressSpinner.setVisibility(View.GONE);
    }
  }

  private class CheckAddCategoriesTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... lists) {
      TriviaDatabase db = TriviaDatabase.getInstance(getActivity());
      TriviaCategoryDao cDao = db.getTriviaCategoryDao();
      prevAddedCategories.addAll(cDao.select());
      return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
      setMap();
      setItemListener();

      for (String str : categoryMap.keySet()) {
        categories.add(new TriviaCategory(str));
      }

      adapter = new AddCategoryAdapter(getActivity(), R.layout.add_category_item,
          categories);
      addList.setAdapter(adapter);
    }
  }
}
