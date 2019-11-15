package com.rlong1218.endlessevil.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.rlong1218.endlessevil.entity.Character;
import com.rlong1218.endlessevil.service.EndlessEvilDatabase;
import java.util.List;

public class CharacterSelectViewModel extends AndroidViewModel {

  private EndlessEvilDatabase database;

  public CharacterSelectViewModel(@NonNull Application application) {
    super(application);
    database = EndlessEvilDatabase.getInstance();

  }

  public LiveData<List<Character>> getCharacters() {
    return database.getCharacterDao().getAll();
  }
}
