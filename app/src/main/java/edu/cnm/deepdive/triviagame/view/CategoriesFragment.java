package edu.cnm.deepdive.triviagame.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import edu.cnm.deepdive.triviagame.GameController;
import edu.cnm.deepdive.triviagame.R;
import edu.cnm.deepdive.triviagame.model.entity.TriviaCategory;
import java.util.ArrayList;
import java.util.List;

public class CategoriesFragment extends Fragment {

  private List<TriviaCategory> categories;
  private ListView categoryListView;
  private CategoryAdapter adapter;

  public String getCategorySelected() {
    return categorySelected;
  }

  private String categorySelected;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    categories = new ArrayList<>();

    for (String str : getResources().getStringArray(R.array.categories)) {
      categories.add(new TriviaCategory(str));
    }

    View layout = inflater.inflate(R.layout.fragment_categories, container, false);
    categoryListView = layout.findViewById(R.id.category_list_view);
    adapter = new CategoryAdapter(getActivity(), R.layout.category_item, categories);
    categoryListView.setAdapter(adapter);

    categoryListView.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        categorySelected = "Initial";
        GameController game = new GameController();
      }
    });
    return layout;
  }

  private class CategoryAdapter extends ArrayAdapter<TriviaCategory> {

    public CategoryAdapter(@NonNull Context context, int resource,
        @NonNull List<TriviaCategory> objects) {
      super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      return super.getView(position, convertView, parent);
    }
  }

}
