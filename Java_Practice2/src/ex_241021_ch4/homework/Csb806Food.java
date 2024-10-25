package ex_241021_ch4.homework;

public class Csb806Food {

	String name;
	int price;
	String[] material;

	public Csb806Food() {
	};

	void introduce() {
		System.out.println("음식의 이름은 :" + name);
		for (String string : material) {
			System.out.println("음식의 재료들은:" + string);
		}
		System.out.println("음식의 가격은:" + price);
	}

	public Csb806Food(String name, int price, String[] material) {
		this.name = name;
		this.price = price;
		this.material = material;
	}
}