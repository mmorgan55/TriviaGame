package edu.cnm.deepdive.triviagame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import edu.cnm.deepdive.triviagame.view.MainFragment;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    MainFragment mainFragment = new MainFragment();

    getSupportFragmentManager()
        .beginTransaction().replace(R.id.fragment_container, mainFragment).commit();
  }
}
