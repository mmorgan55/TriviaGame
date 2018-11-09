package edu.cnm.deepdive.triviagame.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import edu.cnm.deepdive.triviagame.R;
import edu.cnm.deepdive.triviagame.model.entity.TriviaCategory;
import java.util.ArrayList;
import java.util.List;


public class AddCategoriesFragment extends DialogFragment {

  private ListView addList;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_add_categories, container, false);
    List<TriviaCategory> categories = new ArrayList<>();
    addList = view.findViewById(R.id.add_category_list_view);

    for (String str : getResources().getStringArray(R.array.categories)) {
      categories.add(new TriviaCategory(str));
    }

    AddCategoryAdapter adapater = new AddCategoryAdapter(getActivity(), R.layout.add_category_item,
        categories);
    addList.setAdapter(adapater);

    return view;
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
}
