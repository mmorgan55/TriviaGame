package edu.cnm.deepdive.triviagame.view;

import  android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import edu.cnm.deepdive.triviagame.R;
import edu.cnm.deepdive.triviagame.controller.MainActivity;
import edu.cnm.deepdive.triviagame.model.dao.TriviaCategoryDao;
import edu.cnm.deepdive.triviagame.model.db.TriviaDatabase;
import edu.cnm.deepdive.triviagame.model.entity.TriviaCategory;
import java.util.ArrayList;
import java.util.List;

/**
 *  This class displays the categories that are currently in the database that can be
 *  selected and also holds the floating action button that can be used to add more
 *  categories.
 */
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
      fragment.setCategoriesFragment(this);
      fragment.show(getFragmentManager(), null);
    });
  }

  private void setListViewListener() {
    categoryListView.setOnItemClickListener((parent, view, position, id) -> {
      categorySelected = categoryListView.getItemAtPosition(position).toString();
      MainActivity.getBundle().putString(getString(R.string.category_string_key), categorySelected);
      getFragmentManager().beginTransaction().replace(R.id.fragment_container, difficultyFragment)
          .addToBackStack(null).commit();
    });
  }

  @Override
  public void onResume() {
    super.onResume();
    new CheckCategoryTask().execute();
  }

  void refreshData() {
    new CheckCategoryTask().execute();
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
      categories.clear();
      categories.addAll(cDao.allTitles());
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
