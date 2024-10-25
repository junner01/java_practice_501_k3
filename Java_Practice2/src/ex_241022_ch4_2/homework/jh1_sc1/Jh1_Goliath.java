package ex_241022_ch4_2.homework.jh1_sc1;

public class Jh1_Goliath extends Jh1_TerranUnit {
  public Jh1_Goliath() {
    super("Goliath");

    size = Jh1_SC1.Size.Large;
    isAir = false;
    isWorker = false;
    mineralCost = 100;
    gasCost = 50;
    maxHP = 125;
    defense = 1;
    isMachine = true;
    isBionic = false;

    groundWeapon = new Jh1_Weapon("Twin Autocannons", 12, 1);
    airWeapon = new Jh1_Weapon("Hellfire Missile Pack", 10, 2, 2, Jh1_SC1.DamageType.Explosive);
  }
}