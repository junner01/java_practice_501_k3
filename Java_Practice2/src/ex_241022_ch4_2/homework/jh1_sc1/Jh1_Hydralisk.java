package ex_241022_ch4_2.homework.jh1_sc1;

public class Jh1_Hydralisk extends Jh1_ZergUnit {
  public Jh1_Hydralisk() {
    super("Hydralisk");

    final Jh1_Weapon weapon = new Jh1_Weapon("Needle Spines", 10, 1, Jh1_SC1.DamageType.Explosive);

    size = Jh1_SC1.Size.Medium;
    isAir = false;
    isWorker = false;
    mineralCost = 75;
    gasCost = 25;
    maxHP = 80;
    defense = 0;

    groundWeapon = weapon;
    airWeapon = weapon;
  }
}
