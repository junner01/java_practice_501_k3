package ex_241022_ch4_2.homework;
public class Lsh0708_Vehicle {

	String type;
	int wheel;
	int price;
	
	static String color="Yellow";
	
	public void moveWhere() {
		System.out.println("땅으로 다녀요!");
	}
	
	public Lsh0708_Vehicle(String type, int price, int wheel) {
		super();
		this.type = type;
		this.price = price;
		this.wheel = wheel;
	}
	
	public Lsh0708_Vehicle() {};
	
	
	
	
}
