package ex_241022_ch4_2.homework;
public class Lsh0708_Texi extends Lsh0708_Vehicle {

	private String town;

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}
	
	@Override
	public void moveWhere() {
		System.out.println("도심속으로 다녀요!");
	}
	
	public void introduce() {
		System.out.println("종류 :" + super.type);

	}

	public void introduce(String town) {
		System.out.println("종류 :" + super.type + ", 가격: " + super.price + ", 가고싶은 도시 :" + this.town);
	}

	public void showInfo() {
		System.out.println("종류 :" + super.type + ", 가격 :" + super.price + ", 바퀴수 :" + super.wheel + " , 가고싶은 도시:" + this.town);
	}

	public Lsh0708_Texi(String town) {
		super();
		this.town = town;
	}

	public Lsh0708_Texi() {
		super();
	}

	public Lsh0708_Texi(String type, int price, int wheel, String town) {
		super(type, price, wheel);
		this.town = town;
	}
	
}
