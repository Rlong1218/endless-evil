package com.rlong1218.endlessevil.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.preference.PreferenceManager;
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
import com.rlong1218.endlessevil.controller.CharacterInfoFragment.CharacterSelector;
import com.rlong1218.endlessevil.model.entity.Character;
import com.rlong1218.endlessevil.viewmodel.CharacterSelectViewModel;

public class CharacterSelectActivity extends AppCompatActivity implements
    OnNavigationItemSelectedListener, CharacterSelector {

  private SharedPreferences preferences;
  private CharacterSelectViewModel viewModel;
  private TextView mTextMessage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    getSupportActionBar().hide();
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_character_select);

    preferences = PreferenceManager.getDefaultSharedPreferences(this);

    mTextMessage = (TextView) findViewById(R.id.message);
    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    // TODO set no button selected
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
     Resources res = getResources();
     String pkg = getPackageName();
     Menu menu = navigation.getMenu();
     int order = 0;
     for (Character character : characters) {
       MenuItem item = menu.add(0,(int) character.getId(), order++, character.getName());
       if (character.getIcon() != null && !character.getIcon().isEmpty()) {
         int id = res.getIdentifier(character.getIcon(), "drawable", pkg);
         item.setIcon(id);
       }
     }
   });
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
    CharacterInfoFragment fragment = CharacterInfoFragment.newInstance(menuItem.getItemId());
    fragment.show(getSupportFragmentManager(), fragment.getClass().getSimpleName());
    return false;
  }

  @Override
  public void select(long id) {
    Editor editor = preferences.edit();
    editor.putLong("character_id", id);
    editor.commit();
    startActivity(new Intent(this, MainActivity.class));
  }

}
