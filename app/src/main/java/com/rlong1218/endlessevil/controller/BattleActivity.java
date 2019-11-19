package com.rlong1218.endlessevil.controller;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;
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

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    getSupportActionBar().hide();
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_battle);
    timingBar = findViewById(R.id.timing_bar);
    maxLevel = timingBar.getMax();

    Button back = findViewById(R.id.back);
    back.setOnClickListener(
        v -> startActivity(new Intent(BattleActivity.this, MainActivity.class)));

    Timer timer = new Timer();
    timer.schedule(new BattleBarTask(), 1000, 10);

    Button lockBar = findViewById(R.id.lock_bar);
    lockBar.setOnClickListener(view -> {
      String percentageClicked = String.valueOf(percentage);
      Toast.makeText(this, percentageClicked, Toast.LENGTH_SHORT).show();
    });
  }

  private class BattleBarTask extends TimerTask {

    @Override
    public void run() {
      if(increasing){
        percentage += 1;
        if (percentage >= maxLevel) {
          percentage = maxLevel;
          increasing = false;
        }
      } else {
        percentage -= 1;
        if (percentage <= 0) {
          percentage = 0;
          increasing = true;
        }
      }
      runOnUiThread(() -> timingBar.setProgress(Math.round(percentage)));
    }
  }


}
