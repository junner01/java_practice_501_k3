package ex_241022_ch4_2.homework;

//자식클래스
public class Csb806TravelMain extends Csb806Travel {

	public Csb806TravelMain(String name, String location, String spot) {
		super(name, location, spot);
	}

	@Override
	public void introduce() {
		System.out.println("여기는" + name + "입니다.");
		System.out.println(name + "은" + location + "에 위치하고 있고,");
		System.out.println("주요 관광지는" + spot + "입니다.");
	}

}