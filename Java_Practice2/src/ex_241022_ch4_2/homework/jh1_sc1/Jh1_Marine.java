package ex_241022_ch4_2.homework.jh1_sc1;

public class Jh1_Marine extends Jh1_TerranUnit {
  public Jh1_Marine() {
    super("Marine");

    final Jh1_Weapon weapon = new Jh1_Weapon("Gauss Rifle", 6, 1);

    size = Jh1_SC1.Size.Small;
    isAir = false;
    isWorker = false;
    mineralCost = 50;
    gasCost = 0;
    maxHP = 40;
    defense = 0;
    isMachine = false;
    isBionic = true;

    groundWeapon = weapon;
    airWeapon = weapon;
  }
}