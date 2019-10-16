package com.rlong1218.endlessevil.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(
    foreignKeys = {
        @ForeignKey(
            entity = Character.class,
            childColumns = "character_id",
            parentColumns = "character_id",
            onDelete = ForeignKey.NO_ACTION
        )
    }
)
public class Game {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "game_id")
  private long id;

  @ColumnInfo(index = true)
  @NonNull
  private Date created = new Date();

  @ColumnInfo(name = "character_id", index = true)
  private long characterId;

  @ColumnInfo(name = "points_earned")
  private int pointsEarned;

  @ColumnInfo(name = "levels_beaten")
  private int levelsBeaten;

}
