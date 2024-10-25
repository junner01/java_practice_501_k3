package ex_241022_ch4_2.homework.jh1_sc1;


public class Jh1_Lurker extends Jh1_ZergUnit {
	public Jh1_Lurker() {
		super("Lurker");
		
	    size = Jh1_SC1.Size.Medium;
	    
	    isAir = false;
	    isWorker = false;
	    mineralCost = 50;
	    gasCost = 100;
	    maxHP = 125;
	    defense = 0;
	    
		groundWeapon = new LurkerWeapon();
	}
	
	static class LurkerWeapon extends Jh1_Weapon {

		public LurkerWeapon() {
			super("Subterranean Spines", 20, 2);
		}
		
		@Override
		public void describeMore() {
			System.out.println(" * Deals linear splash damage");
			System.out.println(" * Can attack only burrowed");
		}
	}

}
