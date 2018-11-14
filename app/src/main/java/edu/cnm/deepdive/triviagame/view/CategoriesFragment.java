package edu.cnm.deepdive.triviagame.view;

import static edu.cnm.deepdive.triviagame.controller.MainActivity.bundle;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import butterknife.BindView;
import butterknife.ButterKnife;
import edu.cnm.deepdive.triviagame.R;
import edu.cnm.deepdive.triviagame.model.dao.TriviaCategoryDao;
import edu.cnm.deepdive.triviagame.model.db.TriviaDatabase;
import edu.cnm.deepdive.triviagame.model.entity.TriviaCategory;
import java.util.ArrayList;
import java.util.List;

public class CategoriesFragment extends Fragment {

  @BindView(R.id.category_list_view)
  ListView categoryListView;
  @BindView(R.id.fab)
  FloatingActionButton fab;

  private String categorySelected;
  private List<String> categories;
  private DifficultyFragment difficultyFragment;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View layout = inflater.inflate(R.layout.fragment_categories, container, false);
    ButterKnife.bind(this, layout);
    initialize();
    new CheckCategoryTask().execute();
    return layout;
  }

  private void initialize() {
    categories = new ArrayList<>();
    difficultyFragment = new DifficultyFragment();
  }

  private void setAdapter() {
    CategoryAdapter adapter = new CategoryAdapter(getActivity(), R.layout.category_item,
        categories);
    categoryListView.setAdapter(adapter);
  }

  private void setFabListener() {
    fab.setOnClickListener(v -> {
      AddCategoriesFragment fragment = new AddCategoriesFragment();
      fragment.show(getFragmentManager(), "add categories dialog");
    });
  }

  private void setListViewListener() {
    categoryListView.setOnItemClickListener((parent, view, position, id) -> {
      categorySelected = categoryListView.getItemAtPosition(position).toString();
      bundle.putString("category", categorySelected);
      getFragmentManager().beginTransaction().replace(R.id.fragment_container, difficultyFragment)
          .addToBackStack("categories").commit();
    });
  }

  private class CategoryAdapter extends ArrayAdapter<String> {

    CategoryAdapter(@NonNull Context context, int resource,
        @NonNull List<String> objects) {
      super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      return super.getView(position, convertView, parent);
    }
  }

  private class CheckCategoryTask extends AsyncTask<Void, Void, TriviaCategory> {

    @Override
    protected TriviaCategory doInBackground(Void... voids) {
      TriviaDatabase db = TriviaDatabase.getInstance(getActivity());
      TriviaCategoryDao cDao = db.getTriviaCategoryDao();

      for (TriviaCategory tc : cDao.select()) {
        categories.add(tc.getCategoryTitle());
      }
      return null;
    }

    @Override
    protected void onPostExecute(TriviaCategory triviaCategory) {
      setAdapter();
      setFabListener();
      setListViewListener();
    }
  }
}
