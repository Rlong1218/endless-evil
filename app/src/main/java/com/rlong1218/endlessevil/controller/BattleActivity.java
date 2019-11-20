package com.rlong1218.endlessevil.controller;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.rlong1218.endlessevil.R;
import com.rlong1218.endlessevil.model.pojo.CharacterStats;
import java.util.Timer;
import java.util.TimerTask;

public class BattleActivity extends AppCompatActivity {

  private static final String TAG = "battle_activity";
  private int maxLevel;
  private float percentage;
  private ProgressBar timingBar;
  private boolean increasing = true;
  private Timer timer;
  private boolean running;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    getSupportActionBar().hide();
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_battle);
    timingBar = findViewById(R.id.timing_bar);
    maxLevel = timingBar.getMax();
    timingBar.setVisibility(View.GONE);


    ImageButton pause = findViewById(R.id.pause);
    pause.setOnClickListener(v ->
        startActivity(new Intent(this, MainActivity.class)));
    pause.setVisibility(View.GONE);

    Button back = findViewById(R.id.back);
    back.setOnClickListener(
        v -> startActivity(new Intent(BattleActivity.this, MainActivity.class)));

    Button start = findViewById(R.id.start);
    start.setOnClickListener( v -> {
      pause.setVisibility(View.VISIBLE);
      pause.bringToFront();
      back.setVisibility(View.GONE);
      timingBar.setVisibility(View.VISIBLE);
      start.setVisibility(View.GONE);
      initializeTimer();

//      Button lockBar = findViewById(R.id.lock_bar);
      View lockBar = findViewById(R.id.lock_bar);
      lockBar.setOnClickListener(view -> {
        if (running) {
          String percentageClicked = String.valueOf((int) percentage);
          timer.cancel();
          Toast.makeText(this, percentageClicked, Toast.LENGTH_SHORT).show();
          running = false;
        } else {
          initializeTimer();
        }
      });
    });
  }

  private void initializeTimer() {
    timer = new Timer();
    timer.schedule(new BattleBarTask(), 0, 1);
    running = true;
  }

  private class BattleBarTask extends TimerTask {

    @Override
    public void run() {
      float incrementAmount = .1f;
      if(increasing){
        percentage += incrementAmount;
        if (percentage >= maxLevel) {
          percentage = maxLevel;
          increasing = false;
        }
      } else {
        percentage -= incrementAmount;
        if (percentage <= 0) {
          percentage = 0;
          increasing = true;
        }
      }
      runOnUiThread(() -> timingBar.setProgress(Math.round(percentage)));
    }
  }


}
