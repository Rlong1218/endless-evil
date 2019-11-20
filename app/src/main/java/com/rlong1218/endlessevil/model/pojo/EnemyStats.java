package com.rlong1218.endlessevil.model.pojo;

public class EnemyStats {

  public int[] healthCalculation(int level) {
    int monsterHealth = 0;
    int monsterDamage = 0;

    if (level <= 10) {
      monsterHealth = (50 + (10 * level));
      monsterDamage = (int) (30 + (10 * Math.floor(level / 5)));
    }
    if (level <= 20) {
      monsterHealth = (50 + (20 * level));
      monsterDamage = (int) (30 + (15 * Math.floor(level / 5)));
    }
    if (level > 30) {
      monsterHealth = (50 + (30 * level));
      monsterDamage = (int) (30 + (20 * Math.floor(level / 5)));
    }
    return new int[] {monsterHealth, monsterDamage};
  }

}
