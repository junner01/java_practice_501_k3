package ex_241021_ch4.homework;

//SiegeTank 클래스 (자식 클래스)
class SiegeTank extends TerranUnit {

 // 생성자
 public SiegeTank() {
     super("시즈탱크", "지상 유닛", 200);
 }

 // 이동 기능 재정의
 @Override
 public void move() {
     System.out.println(name + "가 이동합니다.");
 }

 // 공격 기능 재정의
 @Override
 public void attack() {
     System.out.println(name + "가 퉁퉁포로 공격합니다!");
 }

 // 스킬 사용 기능 재정의
 @Override
 public void useSkill() {
     System.out.println(name + "가 시즈 모드를 활성화합니다!");
 }
}
