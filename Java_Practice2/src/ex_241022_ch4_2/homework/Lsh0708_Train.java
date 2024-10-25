package ex_241022_ch4_2.homework;
public class Lsh0708_Train extends Lsh0708_Vehicle {

	private String neighborhood;

	public String getTown() {
		return neighborhood;
	}

	public void setneighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	
	@Override
	public void moveWhere() {
		System.out.println("도심속으로 다녀요!");
	}
	
	public void introduce() {
		System.out.println("종류 :" + super.type);

	}

	public void introduce(String neighborhood) {
		System.out.println("종류 :" + super.type + ", 가격: " + super.price + ", 가고싶은 동네 :" + this.neighborhood);
	}

	public void showInfo() {
		System.out.println("종류 :" + super.type + ", 가격 :" + super.price + ", 바퀴수 :" + super.wheel + " , 가고싶은 동네:" + this.neighborhood);
	}

	public Lsh0708_Train(String neighborhood) {
		super();
		this.neighborhood = neighborhood;
	}

	public Lsh0708_Train() {
		super();
	}

	public Lsh0708_Train(String type, int price, int wheel, String neighborhood) {
		super(type, price, wheel);
		this.neighborhood = neighborhood;
	}
}
