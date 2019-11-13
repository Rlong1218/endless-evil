package com.rlong1218.endlessevil.pojo;

public class EnemyStats {
  int baseHealth = 50;
  int level;
  int baseDamage = 30;
  int monsterHealth;
  int monsterDamage;
  int[] stats = new int[] {monsterHealth, monsterDamage};

  public int[] healthCalculation(int level) {
    if (level <= 10) {
      monsterHealth = (baseHealth + (10 * level));
      monsterDamage = (int) (baseDamage + (10 * Math.floor(level / 5)));
    }
    if (level <= 20) {
      monsterHealth = (baseHealth + (20 * level));
      monsterDamage = (int) (baseDamage + (15 * Math.floor(level / 5)));
    }
    if (level > 30) {
      monsterHealth = (baseHealth + (30 * level));
      monsterDamage = (int) (baseDamage + (20 * Math.floor(level / 5)));
    }
    return stats;
  }
}
