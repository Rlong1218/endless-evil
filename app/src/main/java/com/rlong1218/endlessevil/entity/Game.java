package com.rlong1218.endlessevil.entity;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class Game {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "game_id")
  private long id;
}
