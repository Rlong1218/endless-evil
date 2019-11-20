package com.rlong1218.endlessevil.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceManager;
import com.rlong1218.endlessevil.R;
import com.rlong1218.endlessevil.model.entity.Character;
import com.rlong1218.endlessevil.model.pojo.CharacterStats;
import com.rlong1218.endlessevil.model.pojo.EnemyStats;
import com.rlong1218.endlessevil.viewmodel.CharacterInfoViewModel;
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
  private boolean attackPhase;
  private SharedPreferences preferences;
  private CharacterInfoViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    getSupportActionBar().hide();
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_battle);

    timingBar = findViewById(R.id.timing_bar);
    maxLevel = timingBar.getMax();
    timingBar.setVisibility(View.GONE);

    ImageView sprite = findViewById(R.id.character_sprite);

    viewModel = ViewModelProviders.of(this).get(CharacterInfoViewModel.class);
    viewModel.getCharacter().observe(this, (character) -> {
      if (character != null && character.getImage() != null && !character.getImage().trim().isEmpty()) {
        Resources res = getResources();
        String pkg = getPackageName();
        int id = res.getIdentifier(character.getImage().trim(), "drawable", pkg);
        sprite.setImageResource(id);
      }
    });
    preferences = PreferenceManager.getDefaultSharedPreferences(this);
    if (preferences.contains("character_id")) {
      viewModel.setId(preferences.getLong("character_id", 0));
    }

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
      attackPhase = true;
      initializeTimer();

//      Button lockBar = findViewById(R.id.lock_bar);
      View lockBar = findViewById(R.id.lock_bar);
      lockBar.setOnClickListener(view -> {
        if (running) {
//          String percentageClicked = String.valueOf((int) percentage);
          timer.cancel();
//          Toast.makeText(this, percentageClicked, Toast.LENGTH_SHORT).show();
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
