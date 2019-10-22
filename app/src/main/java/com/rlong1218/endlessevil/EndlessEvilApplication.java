package com.rlong1218.endlessevil;

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.rlong1218.endlessevil.service.EndlessEvilDatabase;

public class EndlessEvilApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    EndlessEvilDatabase.setApplicationContext(this);
    new Thread(() -> EndlessEvilDatabase.getInstance().getCharacterDao().insert()).start();
  }
}
