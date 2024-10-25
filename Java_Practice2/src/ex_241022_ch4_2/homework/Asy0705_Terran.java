package ex_241022_ch4_2.homework;

// 테란 유닛 클래스 (부모 클래스)
	class TerranUnit {
	    // 멤버 변수 (이름, 종류, 가격)
	    public String name;   // 유닛 이름
	    public String type;   // 유닛 종류 (지상 공중)
	    public int cost;      // 유닛 가격

	    
	    public TerranUnit(String name, String type, int cost) {
	        this.name = name;
	        this.type = type;
	        this.cost = cost;
	    }// 생성자

	 
	    public void move() {
	        System.out.println(name + "가 이동합니다.");
	    }// 이동 기능 (자식 클래스에서 재정의 가능)

	    public void attack() {
	        System.out.println(name + "가 공격합니다!");
	    }// 공격 기능 (자식 클래스에서 재정의 가능)

	    public void useSkill() {
	        System.out.println(name + "가 기본 스킬을 사용합니다.");
	    }// 스킬 사용 기능 (자식 클래스에서 재정의 가능)

	    
	    public void showInfo() {
	        System.out.println("이름: " + name + ", 종류: " + type + ", 가격: " + cost);
	    }// 유닛의 정보를 출력하는 기능
	}

