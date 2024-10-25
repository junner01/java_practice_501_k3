package ex_241022_ch4_2.homework.jh1_sc1;

public class Jh1_Zergling extends Jh1_ZergUnit {
  public Jh1_Zergling() {
    super("Zergling");

    size = Jh1_SC1.Size.Small;
    isAir = false;
    isWorker = false;
    mineralCost = 50;
    gasCost = 0;
    maxHP = 35;
    defense = 0;

    groundWeapon = new Jh1_Weapon("Claws", 5, 1);
  }
}
