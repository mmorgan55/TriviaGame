package edu.cnm.deepdive.triviagame.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.ButterKnife;
import edu.cnm.deepdive.triviagame.R;
import edu.cnm.deepdive.triviagame.TriviaApplication;
import edu.cnm.deepdive.triviagame.model.db.TriviaDatabase;
import edu.cnm.deepdive.triviagame.view.GameFragment;
import edu.cnm.deepdive.triviagame.view.MainFragment;
import edu.cnm.deepdive.triviagame.view.PostGameFragment;

public class MainActivity extends AppCompatActivity {

  public static Bundle bundle = new Bundle();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    new InitializeDatabaseTask().execute();

    MainFragment mainFragment = new MainFragment();

    getSupportFragmentManager()
        .beginTransaction().replace(R.id.fragment_container, mainFragment).commit();
  }

  @Override
  public void onBackPressed() {
    if (getSupportFragmentManager()
        .findFragmentById(R.id.fragment_container) instanceof PostGameFragment) {
      Fragment fragment = new MainFragment();
      getSupportFragmentManager()
          .popBackStack("main", android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
      getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment)
          .commit();

    } else if (getSupportFragmentManager()
        .findFragmentById(R.id.fragment_container) instanceof GameFragment) {
      AlertDialog.Builder builder = new Builder(this);
      builder.setTitle("Back to Game Select");
      builder.setMessage("Are you sure to want to go back to game select?");

      builder.setPositiveButton("Yes", (dialog, which) -> {
        Fragment fragment = new MainFragment();
        getSupportFragmentManager()
            .popBackStack("main", android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment)
            .commit();
      });

      builder.setNegativeButton("No", (dialog, which) -> {
        /*Nothing here; pressing "No" will go back to the game this was called
         on, which is the desired functionality.*/
      });
      builder.show();

    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.options, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    boolean handled = true;
    switch (item.getItemId()) {
      case R.id.sign_out:
        signOut();
        break;
      default:
        handled = super.onOptionsItemSelected(item);
    }
    return handled;
  }

  private void signOut() {
    TriviaApplication app = TriviaApplication.getInstance();
    app.getClient().signOut().addOnCompleteListener(this, (task) -> {
      app.setAccount(null);
      Intent intent = new Intent(this, LoginActivity.class);
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
      startActivity(intent);
    });
  }

  private class InitializeDatabaseTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
      TriviaDatabase.getInstance(MainActivity.this).getTriviaAnswersDao().select(0);
      return null;
    }
  }
}
