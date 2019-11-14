package com.rlong1218.endlessevil.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import com.rlong1218.endlessevil.R;

public class CharacterSelectActivity extends AppCompatActivity {

  private TextView mTextMessage;

  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
      = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
//        case R.id.navigation_home:
//          mTextMessage.setText(R.string.title_home);
//          return true;
//        case R.id.navigation_dashboard:
//          mTextMessage.setText(R.string.title_dashboard);
//          return true;
//        case R.id.navigation_notifications:
//          mTextMessage.setText(R.string.title_notifications);
//          return true;
       }
      return false;
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    getSupportActionBar().hide();
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_character_select);

    mTextMessage = (TextView) findViewById(R.id.message);
    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    Button back = (Button) findViewById(R.id.back);
    back.setOnClickListener(new View.OnClickListener(){
      public void onClick(View v){
        startActivity(new Intent(CharacterSelectActivity.this, MainActivity.class));
      }
    });
  }

}
