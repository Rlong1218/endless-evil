package com.rlong1218.endlessevil.controller;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.rlong1218.endlessevil.R;
import java.util.Timer;
import java.util.TimerTask;

public class BattleActivity extends AppCompatActivity {

  private static final String TAG = "battle_activity";
  private int maxLevel;
  private float level;
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
      Log.d(TAG, "onCreate: "+ level);
    });
  }

  private class BattleBarTask extends TimerTask {

    @Override
    public void run() {
      if(increasing){
        level += 1;
        if (level >= maxLevel) {
          level = maxLevel;
          increasing = false;
        }
      } else {
        level -= 1;
        if (level <= 0) {
          level = 0;
          increasing = true;
        }
      }
      runOnUiThread(() -> timingBar.setProgress(Math.round(level)));
    }
  }
}
