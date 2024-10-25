package ex_241022_ch4_2.homework;
public class Lsh0708_Airplane extends Lsh0708_Vehicle {

	private String country;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public void moveWhere() {
		System.out.println("하늘로 날아다녀요!");
	}

	public void introduce() {
		System.out.println("종류 :" + super.type);

	}

	public void introduce(String country) {
		System.out.println("종류 :" + super.type + ", 가격: " + super.price + ", 가고싶은 나라 :" + this.country);
	}

	public void showInfo() {
		System.out.println(
				"종류 :" + super.type + ", 가격 :" + super.price + ", 바퀴수 :" + super.wheel + ", 가고싶은 나라:" + this.country);
	}

	public Lsh0708_Airplane(String country) {
		super();
		this.country = country;
	}

	public Lsh0708_Airplane() {
		super();
	}

	public Lsh0708_Airplane(String type, int price, int wheel, String country) {
		super(type, price, wheel);
		this.country = country;
	}
}
