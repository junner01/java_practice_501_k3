package ex_241022_ch4_2.homework.jh1_sc1;

public class Jh1_SiegeTank extends Jh1_TerranUnit {
  public Jh1_SiegeTank() {
    super("Siege Tank");

    size = Jh1_SC1.Size.Large;
    isAir = false;
    isWorker = false;
    mineralCost = 150;
    gasCost = 100;
    maxHP = 150;
    defense = 1;
    isMachine = true;
    isBionic = false;

    groundWeapon = new Jh1_Weapon("Arclite Cannon", 30, 3, Jh1_SC1.DamageType.Explosive);
  }
}