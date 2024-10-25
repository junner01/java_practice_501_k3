package ex_241022_ch4_2.homework.jh1_sc1;

public class Jh1_Weapon {
  String name;
  int damage;
  int bonus;
  int hitPerAttack;
  int upgradeLevel = 0;
  Jh1_SC1.DamageType dtype;

  public Jh1_Weapon(String name, int damage, int bonus, int hitPerAttack, Jh1_SC1.DamageType dtype) {
    this.name = name;
    this.damage = damage;
    this.bonus = bonus;
    this.hitPerAttack = hitPerAttack;
    this.dtype = dtype;
  }

  public Jh1_Weapon(String name, int damage, int bonus, Jh1_SC1.DamageType dtype) {
    this(name, damage, bonus, 1, dtype);
  }

  public Jh1_Weapon(String name, int damage, int bonus) {
    this(name, damage, bonus, 1, Jh1_SC1.DamageType.Normal);
  }

  public Jh1_Weapon(String name, int damage, int bonus, int hitPerAttack) {
    this(name, damage, bonus, hitPerAttack, Jh1_SC1.DamageType.Normal);
  }

  public int calcDmgOnce() {
    return damage + bonus * upgradeLevel;
  }

  public int calcFullHit() {
    return calcDmgOnce() * hitPerAttack;
  }

  @Override
  public String toString() {
    String useBonus = upgradeLevel > 0 ? 
      String.format(" [= (%d + %d x %d) x %d ]", damage, bonus, upgradeLevel, hitPerAttack)
      : "";
    return String.format("<%s %d%s>", name, calcFullHit(), useBonus);
  }

  public void describe() {
    System.out.println("Name: " + name);
    System.out.println("Attack type: " + dtype.name());
    System.out.print("Damage: " + damage);
    if (bonus > 0) System.out.printf(" (+%d/up)\n", bonus);
    else System.out.println();
    if (hitPerAttack > 1) System.out.println("Hit per attack: " + hitPerAttack);
    if (upgradeLevel > 0) System.out.println("[upgrade level = " + upgradeLevel + "]");
    describeMore();
    System.out.println(" => Full hit damage: " + calcFullHit());
    
  }

  public void describeMore() {

  }
  
}
