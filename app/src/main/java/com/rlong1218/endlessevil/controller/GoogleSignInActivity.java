package com.rlong1218.endlessevil.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.preference.PreferenceManager;
import com.rlong1218.endlessevil.BuildConfig;
import com.rlong1218.endlessevil.R;
import com.rlong1218.endlessevil.service.GoogleSignInService;
import com.rlong1218.endlessevil.service.WeatherService;
import io.reactivex.schedulers.Schedulers;

public class GoogleSignInActivity extends AppCompatActivity {

  private static final int LOGIN_REQUEST_CODE = 1000;

  private Spinner citySelect;
  private SharedPreferences preferences;
  private GoogleSignInService service;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getSupportActionBar().hide();
    preferences = PreferenceManager.getDefaultSharedPreferences(this);
    service = GoogleSignInService.getInstance();
    service.refresh()
        .addOnSuccessListener((account) -> switchToMain())
        .addOnFailureListener((ex) -> {
          setContentView(R.layout.activity_google_sign_in);
          citySelect = findViewById(R.id.city_select);
          findViewById(R.id.sign_in_button).setOnClickListener((view) ->
              service.startSignIn(this, LOGIN_REQUEST_CODE));
        });
  }
  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    if (requestCode == LOGIN_REQUEST_CODE) {
      service.completeSignIn(data)
          .addOnSuccessListener((account) -> switchToMain())
          .addOnFailureListener((ex) ->
              Toast.makeText(this, R.string.login_failure_message, Toast.LENGTH_LONG).show());
    } else {
      super.onActivityResult(requestCode, resultCode, data);
    }
  }
  private void switchToMain() {
//    WeatherService.getInstance().get((String) citySelect.getSelectedItem(), BuildConfig.APP_ID)
//        .subscribeOn(Schedulers.io())
//        .subscribe((data) -> {
//          boolean isDayTime = data.getWeather().isDaytime();
//          boolean isSnowing = data.getWeather().isSnowing();
//          //TODO store isDay and isSnow in preferences
//        });
    Intent intent = new Intent(this, MainActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }

}
