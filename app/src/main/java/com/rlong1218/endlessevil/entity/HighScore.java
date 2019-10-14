package com.rlong1218.endlessevil.entity;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class HighScore {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "HighScore_id")
  private long id;

}
