package com.rlong1218.endlessevil.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.rlong1218.endlessevil.entity.Character;
import java.util.Collection;
import java.util.List;

@Dao
public interface CharacterDao {

  @Insert
  long insert(Character character);

  @Insert
  long[] insert(Character... characters);

  @Insert
  List<Long> insert(Collection<Character> characters);

  @Query("SELECT * FROM Character ORDER BY character_id ASC")
  LiveData<List<Character>> getAll();

  @Query("SELECT * FROM Character WHERE character_id = :characterId")
  LiveData<Character> getById(long characterId);
}
