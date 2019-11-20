package com.rlong1218.endlessevil.model.dao;

import androidx.lifecycle.LiveData;
    import androidx.room.Dao;
    import androidx.room.Insert;
    import androidx.room.Query;
    import com.rlong1218.endlessevil.model.entity.Character;
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

  @Query("SELECT * FROM Character ORDER BY name ASC")
  LiveData<List<Character>> getAll();

  @Query("SELECT * FROM Character WHERE character_id = :characterId")
  LiveData<Character> getById(long characterId);
}
