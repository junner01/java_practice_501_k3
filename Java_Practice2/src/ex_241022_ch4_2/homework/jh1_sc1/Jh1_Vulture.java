package ex_241022_ch4_2.homework.jh1_sc1;

public class Jh1_Vulture extends Jh1_TerranUnit {
  public Jh1_Vulture() {
    super("Vulture");

    size = Jh1_SC1.Size.Medium;
    isAir = false;
    isWorker = false;
    mineralCost = 75;
    gasCost = 0;
    maxHP = 80;
    defense = 0;
    isMachine = true;
    isBionic = false;

    groundWeapon = new Jh1_Weapon("Fragmentation Grenade", 20, 2, Jh1_SC1.DamageType.Concussive);
  }
}