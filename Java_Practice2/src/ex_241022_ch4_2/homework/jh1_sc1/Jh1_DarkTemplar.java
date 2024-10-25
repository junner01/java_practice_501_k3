package ex_241022_ch4_2.homework.jh1_sc1;

public class Jh1_DarkTemplar extends Jh1_ProtossUnit {
  public Jh1_DarkTemplar() {
    super("Dark Templar");

    size = Jh1_SC1.Size.Small;
    isAir = false;
    isWorker = false;
    mineralCost = 125;
    gasCost = 100;
    maxHP = 80;
    maxShield = 40;
    defense = 1;
    isMachine = false;
    isBionic = true;

    groundWeapon = new Jh1_Weapon("Warp Blades", 40, 3);
  }

  @Override
  public void showSpecialInfo() {
    System.out.println(" * Permanently Cloaked");
  }
}