package com.rlong1218.endlessevil.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import java.util.Date;

public class Character {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "character_id")
  private long id;

  @ColumnInfo(index = true)
  @NonNull
  private Date created = new Date();

  @ColumnInfo(name = "health_upgrades", index = true)
  private int healthUpgrades;

  @ColumnInfo(name = "damage_upgrades", index = true)
  private int damageUpgrades;

  @ColumnInfo(name = "total_kills", index = true)
  private int totalKills;

  @ColumnInfo(name = "high_score", index = true)
  private int highScore;
}
