package com.rlong1218.endlessevil.model.entity;

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

  @ColumnInfo(name = "weather_background")
  private int weather;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @NonNull
  public Date getCreated() {
    return created;
  }

  public void setCreated(@NonNull Date created) {
    this.created = created;
  }

  public long getCharacterId() {
    return characterId;
  }

  public void setCharacterId(long characterId) {
    this.characterId = characterId;
  }

  public int getPointsEarned() {
    return pointsEarned;
  }

  public void setPointsEarned(int pointsEarned) {
    this.pointsEarned = pointsEarned;
  }

  public int getLevelsBeaten() {
    return levelsBeaten;
  }

  public void setLevelsBeaten(int levelsBeaten) {
    this.levelsBeaten = levelsBeaten;
  }

  public int getWeather() {
    return weather;
  }

  public void setWeather(int weather) {
    this.weather = weather;
  }
}
