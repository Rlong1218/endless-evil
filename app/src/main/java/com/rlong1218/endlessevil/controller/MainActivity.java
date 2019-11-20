package com.rlong1218.endlessevil.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceManager;
import com.rlong1218.endlessevil.R;
import com.rlong1218.endlessevil.viewmodel.CharacterInfoViewModel;

public class MainActivity extends AppCompatActivity {

  private View view;
  private CharacterInfoViewModel viewModel;
  private ImageView sprite;
  private SharedPreferences preferences;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getSupportActionBar().hide();
    setContentView(R.layout.activity_main);

    Button tavern = findViewById(R.id.tavern);
    tavern.setOnClickListener(
        v -> startActivity(new Intent(MainActivity.this, CharacterSelectActivity.class)));

    Button blacksmith = findViewById(R.id.blacksmith);
    blacksmith.setOnClickListener(
        v -> startActivity(new Intent(MainActivity.this, UpgradesActivity.class)));

    Button playGame = findViewById(R.id.play_game);
    playGame.setOnClickListener(
        v -> startActivity(new Intent(MainActivity.this, BattleActivity.class)));

    ImageView sprite = findViewById(R.id.sprite);

    View mainBackground = findViewById(R.id.main_background);
//TODO Use preferences to find weather
    // e.g. mainBackground.setBackground(...);
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
  }

}
