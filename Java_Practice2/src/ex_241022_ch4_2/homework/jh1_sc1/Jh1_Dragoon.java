package ex_241022_ch4_2.homework.jh1_sc1;

public class Jh1_Dragoon extends Jh1_ProtossUnit {
  public Jh1_Dragoon() {
    super("Dragoon");

    Jh1_Weapon weapon = new Jh1_Weapon("Phase Disruptor", 20, 2, Jh1_SC1.DamageType.Explosive);
    size = Jh1_SC1.Size.Large;
    isAir = false;
    isWorker = false;
    mineralCost = 125;
    gasCost = 50;
    maxHP = 100;
    maxShield = 80;
    defense = 1;
    isMachine = true;
    isBionic = false;

    groundWeapon = weapon;
    airWeapon = weapon;
  }
}