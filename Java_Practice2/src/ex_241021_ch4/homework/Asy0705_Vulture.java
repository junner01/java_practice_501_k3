package ex_241021_ch4.homework;

//Vulture 클래스 (자식 클래스)
class Vulture extends TerranUnit {

 // 생성자
 public Vulture() {
     super("벌쳐", "지상 유닛", 75);
 }

 // 이동 기능 재정의 (날아다님)
 @Override
 public void move() {
     System.out.println(name + "가 빠르게 달립니다.");
 }

 // 공격 기능 재정의 (마인 설치)
 @Override
 public void attack() {
     System.out.println(name + "가 마인을 설치합니다!");
 }

 // 스킬 사용 기능 재정의
 @Override
 public void useSkill() {
     System.out.println(name + "가 마인을 터트립니다!");
 }
}
