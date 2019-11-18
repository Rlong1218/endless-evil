package com.rlong1218.endlessevil.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.rlong1218.endlessevil.model.entity.Character;
import com.rlong1218.endlessevil.service.EndlessEvilDatabase;

public class CharacterInfoViewModel extends AndroidViewModel {

  private EndlessEvilDatabase database;
  private MutableLiveData<Long> id = new MutableLiveData<>();
  private LiveData<Character> character;

  public CharacterInfoViewModel(@NonNull Application application) {
    super(application);
    database = EndlessEvilDatabase.getInstance();
    character = Transformations.switchMap(id, (id) -> database.getCharacterDao().getById(id));
  }

  public void setId(long id) {
    this.id.setValue(id);
  }

  public LiveData<Character> getCharacter() {
    return character;
  }
}
