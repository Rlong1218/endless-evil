package com.rlong1218.endlessevil.entity;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class Character {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "character_id")
  private long id;
}
