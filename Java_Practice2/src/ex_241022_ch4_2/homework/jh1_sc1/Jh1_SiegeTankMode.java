package ex_241022_ch4_2.homework.jh1_sc1;

public class Jh1_SiegeTankMode extends Jh1_TerranUnit {
  public Jh1_SiegeTankMode() {
    super("Siege Tank (Siege Mode)");

    size = Jh1_SC1.Size.Large;
    isAir = false;
    isWorker = false;
    mineralCost = 150;
    gasCost = 100;
    maxHP = 150;
    defense = 1;
    isMachine = true;
    isBionic = false;

    groundWeapon = new Jh1_Weapon("Arclite Shock Cannon", 70, 5, Jh1_SC1.DamageType.Explosive);
  }
  
  @Override
	public void showSpecialInfo() {
		System.out.println(" * Immobile");
	}
}

class SiegeModeWeapon extends Jh1_Weapon {
  public SiegeModeWeapon() {
    super("Arclite Shock Cannon", 70, 5, Jh1_SC1.DamageType.Explosive);
  }

  @Override
  public void describeMore() {
    System.out.println("* Splash damage (can injure ally units)");
  }
}