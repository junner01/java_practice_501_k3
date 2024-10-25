package ex_241022_ch4_2.homework.jh1_sc1;

public class Jh1_Zealot extends Jh1_ProtossUnit {
  public Jh1_Zealot() {
    super("Zealot");

    size = Jh1_SC1.Size.Small;
    isAir = false;
    isWorker = false;
    mineralCost = 100;
    gasCost = 0;
    maxHP = 100;
    maxShield = 60;
    defense = 1;
    isMachine = false;
    isBionic = true;

    groundWeapon = new Jh1_Weapon("Psi Blades", 8, 1, 2);
  }
}
