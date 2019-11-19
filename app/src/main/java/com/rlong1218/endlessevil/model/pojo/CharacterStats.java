package com.rlong1218.endlessevil.model.pojo;

public class CharacterStats {

  public int[] lilithStats(int healthUpgrades, int damageUpgrades) {
    int health = 100 + 10 * healthUpgrades;
    int damage = 50 + 10 * damageUpgrades;
    return new int[] {health, damage};
  }

  public int[] lanceStats(int healthUpgrades, int damageUpgrades) {
    int health = 200 + 20 * healthUpgrades;
    int damage = 30 + 5 * damageUpgrades;
    return new int[] {health, damage};
  }

  public int[] zyrelaStats(int healthUpgrades, int damageUpgrades) {
    int health = 75 + 5 * healthUpgrades;
    int damage = 75 + 15 * damageUpgrades;
    return new int[] {health, damage};
  }

}
