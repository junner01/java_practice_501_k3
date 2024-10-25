package ex_241022_ch4_2.homework.jh1_sc1;

public class Jh1_Corsair extends Jh1_ProtossUnit {
	public Jh1_Corsair() {
		super("Corsair");
		
	    size = Jh1_SC1.Size.Medium;
	    isAir = true;
	    isWorker = false;
	    mineralCost = 150;
	    gasCost = 100;
	    maxHP = 100;
	    maxShield = 80;
	    defense = 1;
	    isMachine = true;
	    isBionic = false;

	    airWeapon = new Jh1_Weapon("Neutron Flare", 5, 1);
	}
}
