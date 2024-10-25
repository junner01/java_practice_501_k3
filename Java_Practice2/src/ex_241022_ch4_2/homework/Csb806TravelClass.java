package ex_241022_ch4_2.homework;

import java.util.Random;

//실행클래스
public class Csb806TravelClass {
	public static void main(String[] args) {
		// 인스턴스
		Csb806Travel[] trips = { new Csb806TravelMain("일본", "동아시아", "도쿄타워"),
				new Csb806TravelMain("대만", "동남아시아", "타이페이101"), new Csb806TravelMain("홍콩", "동남아시아", "미드레벨 에스컬레이터") };

		System.out.println("모든 여행지:");
		for (Csb806Travel trip : trips) {
			trip.introduce();
			System.out.println();
		}

		Csb806Travel trip[] = new Csb806Travel[3];
		trip[0] = new Csb806TravelMain("일본", "동아시아", "도쿄타워");
		trip[1] = new Csb806TravelMain("대만", "동남아시아", "타이페이101");
		trip[2] = new Csb806TravelMain("홍콩", "동남아시아", "미드레벨 에스컬레이터");

		Random random = new Random();
		int rnd = random.nextInt(trip.length);

		System.out.println("랜덤 여행지:");
		trip[rnd].introduce();
	}
}