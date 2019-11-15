package com.rlong1218.endlessevil.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity
public class Character {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "character_id")
  private long id;

  @ColumnInfo(index = true)
  @NonNull
  private Date created = new Date();

  @ColumnInfo(index = true)
  @NonNull
  private String name;

  private int icon;

  private int image;

  @ColumnInfo(name = "baseline_health")
  private int baselineHealth;

  @ColumnInfo(name = "baseline_damage")
  private int baselineDamage;

  @ColumnInfo(name = "health_upgrades")
  private int healthUpgrades;

  @ColumnInfo(name = "damage_upgrades")
  private int damageUpgrades;

  @ColumnInfo(name = "total_kills")
  private int totalKills;

  @ColumnInfo(name = "high_score")
  private int highScore;

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

  @NonNull
  public String getName() {
    return name;
  }

  public void setName(@NonNull String name) {
    this.name = name;
  }

  public int getImage() {
    return image;
  }

  public void setImage(int image) {
    this.image = image;
  }

  public int getBaselineHealth() {
    return baselineHealth;
  }

  public void setBaselineHealth(int baselineHealth) {
    this.baselineHealth = baselineHealth;
  }

  public int getBaselineDamage() {
    return baselineDamage;
  }

  public void setBaselineDamage(int baselineDamage) {
    this.baselineDamage = baselineDamage;
  }

  public int getIcon() {
    return icon;
  }

  public void setIcon(int icon) {
    this.icon = icon;
  }

  public int getHealthUpgrades() {
    return healthUpgrades;
  }

  public void setHealthUpgrades(int healthUpgrades) {
    this.healthUpgrades = healthUpgrades;
  }

  public int getDamageUpgrades() {
    return damageUpgrades;
  }

  public void setDamageUpgrades(int damageUpgrades) {
    this.damageUpgrades = damageUpgrades;
  }

  public int getTotalKills() {
    return totalKills;
  }

  public void setTotalKills(int totalKills) {
    this.totalKills = totalKills;
  }

  public int getHighScore() {
    return highScore;
  }

  public void setHighScore(int highScore) {
    this.highScore = highScore;
  }
}
