package com.rlong1218.endlessevil.controller;

import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.rlong1218.endlessevil.R;

public class UpgradesActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    getSupportActionBar().hide();
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_upgrades);

    Button back = (Button) findViewById(R.id.back);
    back.setOnClickListener(
        v -> startActivity(new Intent(UpgradesActivity.this, MainActivity.class)));
  }

}
