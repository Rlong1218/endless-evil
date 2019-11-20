package com.rlong1218.endlessevil.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.rlong1218.endlessevil.model.entity.Game;

@Dao
public interface GameDao {

  @Insert
  long insert(Game game);

  @Query("SELECT * FROM Game ORDER BY game_id DESC LIMIT :count")
  LiveData<Game> getMostRecent(int count);

  @Update
  int update(Game game);

}
