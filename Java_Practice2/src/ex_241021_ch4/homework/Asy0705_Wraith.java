package ex_241021_ch4.homework;

//Wraith 클래스 (자식 클래스)
class Wraith extends TerranUnit {

 // 생성자
 public Wraith() {
     super("레이스", "공중 유닛", 150);
 }

 // 이동 기능 재정의 (날아다님)
 @Override
 public void move() {
     System.out.println(name + "가 공중에서 날아다닙니다.");
 }

 // 공격 기능 재정의 
 @Override
 public void attack() {
     System.out.println(name + "가 지상 공격을 합니다!");
 }

 // 스킬 사용 기능 재정의
 @Override
 public void useSkill() {
     System.out.println(name + "가 클로킹을 사용합니다!");
 }
}
