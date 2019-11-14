package com.rlong1218.endlessevil.controller;

import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.rlong1218.endlessevil.R;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getSupportActionBar().hide();
    setContentView(R.layout.activity_main);

    Button tavern = (Button) findViewById(R.id.tavern);
    tavern.setOnClickListener(
        v -> startActivity(new Intent(MainActivity.this, CharacterSelectActivity.class)));

    Button blacksmith = (Button) findViewById(R.id.blacksmith);
    blacksmith.setOnClickListener(
        v -> startActivity(new Intent(MainActivity.this, UpgradesActivity.class)));

    Button market = (Button) findViewById(R.id.market);
    Button playGame = (Button) findViewById(R.id.play_game);
  }

}
