package ex_241022_ch4_2.homework.jh1_sc1;

public class Jh1_SpiderMine extends Jh1_TerranUnit {
  public Jh1_SpiderMine() {
    super("Spider Mine");

    size = Jh1_SC1.Size.Small;
    isAir = false;
    isWorker = false;
    mineralCost = 0;
    gasCost = 0;
    maxHP = 20;
    defense = 0;
    isMachine = true;
    isBionic = false;

    groundWeapon = new Jh1_Weapon("Spider Mines", 125, 0, Jh1_SC1.DamageType.Explosive);
  }
}