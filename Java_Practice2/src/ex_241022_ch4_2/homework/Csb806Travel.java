package ex_241022_ch4_2.homework;

//부모클래스
public class Csb806Travel {

	protected final String name;
	protected String location;
	protected String spot;

	public Csb806Travel(String name, String location, String spot) {
		this.name = name;
		this.location = location;
		this.spot = spot;
	}

	public void introduce() {
		System.out.println("나라:" + name);
		System.out.println("위치:" + location);
		System.out.println("주요 관광지:" + spot);
	}
}
