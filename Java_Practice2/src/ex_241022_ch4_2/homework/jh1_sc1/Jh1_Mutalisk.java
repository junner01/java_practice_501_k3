package ex_241022_ch4_2.homework.jh1_sc1;



public class Jh1_Mutalisk extends Jh1_ZergUnit {
  public Jh1_Mutalisk() {
    super("Mutalisk");

    final Jh1_Weapon weapon = new MutaliskWeapon();

    size = Jh1_SC1.Size.Small;
    isAir = true;
    isWorker = false;
    mineralCost = 75;
    gasCost = 25;
    maxHP = 80;
    defense = 0;

    groundWeapon = weapon;
    airWeapon = weapon;
  }
  
  static class MutaliskWeapon extends Jh1_Weapon {
	  public MutaliskWeapon() {
	    super("Glave Wurm", 9, 1);
	  }

	  @Override
	  public void describeMore() {
	    System.out.println(" * Attack bounces three times");
	  }

	  public double secondHit() {
	    return super.calcFullHit() / 3;
	  }

	  public double thirdHit() {
	    return super.calcFullHit() / 9;
	  }

	  public int calcFullHit() {
	    return (int) (super.calcFullHit() + secondHit() + thirdHit());
	  }

	}
}
