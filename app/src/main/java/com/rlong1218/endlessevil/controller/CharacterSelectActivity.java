package com.rlong1218.endlessevil.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import com.rlong1218.endlessevil.R;
import com.rlong1218.endlessevil.entity.Character;
import com.rlong1218.endlessevil.viewmodel.CharacterSelectViewModel;

public class CharacterSelectActivity extends AppCompatActivity implements
    OnNavigationItemSelectedListener {

  private CharacterSelectViewModel viewModel;

  private TextView mTextMessage;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    getSupportActionBar().hide();
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_character_select);

    mTextMessage = (TextView) findViewById(R.id.message);
    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(this);

    Button back = (Button) findViewById(R.id.back);
    back.setOnClickListener(new View.OnClickListener(){
      public void onClick(View v){
        startActivity(new Intent(CharacterSelectActivity.this, MainActivity.class));
      }
    });
   viewModel = ViewModelProviders.of(this).get(CharacterSelectViewModel.class);
   // Dynamically add characters to bottom navigation
   viewModel.getCharacters().observe(this, (characters) -> {
     Menu menu = navigation.getMenu();
     int order = 0;
     for (Character character : characters) {
       MenuItem item = menu.add(0,(int) character.getId(), order++, character.getName());
       if (character.getIcon() != 0) {
         item.setIcon(character.getIcon());
       }
     }
   });
  }


  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
    // TODO Respond to character selection
    return false;
  }
}
