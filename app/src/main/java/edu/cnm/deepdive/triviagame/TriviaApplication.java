package edu.cnm.deepdive.triviagame;

import android.app.Application;
import com.facebook.stetho.Stetho;

public class TriviaApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
  }
}
