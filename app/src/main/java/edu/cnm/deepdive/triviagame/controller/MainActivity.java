package edu.cnm.deepdive.triviagame.controller;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import edu.cnm.deepdive.triviagame.R;
import edu.cnm.deepdive.triviagame.model.db.TriviaDatabase;
import edu.cnm.deepdive.triviagame.view.MainFragment;

public class MainActivity extends AppCompatActivity {

  public static Bundle bundle = new Bundle();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    new InitializeDatabase().execute();

    MainFragment mainFragment = new MainFragment();

    getSupportFragmentManager()
        .beginTransaction().replace(R.id.fragment_container, mainFragment).commit();
  }

  private class InitializeDatabase extends AsyncTask<Void, Void, Void>{

    @Override
    protected Void doInBackground(Void... voids) {
      TriviaDatabase.getInstance(MainActivity.this).getTriviaAnswersDao().select(0);
      return null;
    }
  }
}
