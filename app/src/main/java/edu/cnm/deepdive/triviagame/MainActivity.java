package edu.cnm.deepdive.triviagame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  private Button timeButton;
  private Button diffButton;
  private Button relaxButton;
  private TextView titleText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    titleText = findViewById(R.id.textView);
    titleText.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        float deg = titleText.getRotation() + -360F;
        titleText.animate().rotation(deg).setInterpolator(new AccelerateDecelerateInterpolator());
      }
    });

    timeButton = findViewById(R.id.time_button);
    timeButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        float deg = timeButton.getRotation() + -360F;
        timeButton.animate().rotation(deg).setInterpolator(new AccelerateDecelerateInterpolator());
      }
    });

    diffButton = findViewById(R.id.difficulty_button);
    diffButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        float deg = diffButton.getRotation() + -360F;
        diffButton.animate().rotation(deg).setInterpolator(new AccelerateDecelerateInterpolator());
      }
    });

    relaxButton = findViewById(R.id.relaxed_button);
    relaxButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        float deg = relaxButton.getRotation() + -360F;
        relaxButton.animate().rotation(deg).setInterpolator(new AccelerateDecelerateInterpolator());
      }
    });

  }
}
