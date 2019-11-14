package com.rlong1218.endlessevil;

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.rlong1218.endlessevil.service.EndlessEvilDatabase;
import com.rlong1218.endlessevil.service.GoogleSignInService;

public class EndlessEvilApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    GoogleSignInService.setApplicationContext(this);
    Stetho.initializeWithDefaults(this);
    EndlessEvilDatabase.setApplicationContext(this);
    new Thread(() -> EndlessEvilDatabase.getInstance().getCharacterDao().insert()).start();
  }
}
