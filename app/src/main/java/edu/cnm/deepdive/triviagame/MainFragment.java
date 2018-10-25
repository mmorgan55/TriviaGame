package edu.cnm.deepdive.triviagame;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


public class MainFragment extends android.support.v4.app.Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_main, container, false);
  }

  private class MainAdapter extends ArrayAdapter<Void> {

    public MainAdapter(@NonNull Context context, int resource) {
      super(context, resource);
    }
  }
}
