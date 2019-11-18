package com.rlong1218.endlessevil.model.pojo;

public class CharacterStats {

  int lilithBaseHealth = 100;
  int lilithBaseDamage = 50;
  int lanceBaseHealth = 200;
  int lanceBaseDamage = 30;
  int zyrelaBaseHealth = 75;
  int zyrelaBaseDamage = 75;
  int healthUpgrades;
  int damageUpgrades;
  int[] stats = new int[2];

  public int[] lilithStats(int healthUpgrades, int damageUpgrades) {
   int health = lilithBaseHealth + 10 * healthUpgrades;
   int damage = lilithBaseDamage + 10 * damageUpgrades;
   stats = new int[] {health, damage};
   return stats;
  }

  public int[] lanceStats(int healthUpgrades, int damageUpgrades) {
    int health = lanceBaseHealth + 20 * healthUpgrades;
    int damage = lanceBaseDamage + 5 * damageUpgrades;
    stats = new int[] {health, damage};
    return stats;
  }

  public int[] zyrelaStats(int healthUpgrades, int damageUpgrades) {
    int health = zyrelaBaseHealth + 5 * healthUpgrades;
    int damage = zyrelaBaseDamage + 15 * damageUpgrades;
    stats = new int[] {health, damage};
    return stats;
  }

}
