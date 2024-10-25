package ex_241022_ch4_2.homework;


public class Hcb0402 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//등장인물 2차배열
		String [] [] characters = {
				{"등장인물1", "등장인물2", "등장인물3"},
				{"등장인물1", "등장인물2", "등장인물3", "등장인물4"},
				{"등장인물1", "등장인물2"},
				{"등장인물1"},
				{"등장인물1", "등장인물2"}
		};
		
		//인스턴스 생성, 배열로 만든 포인터에 할당 
		Hcb0402_Movie movie [] = {
				new Hcb0402_Movie(),
				new Hcb0402_Movie("영화1", "감독1", 19960421, characters[0]),
				new Hcb0402_Movie("영화2", "감독2", 19960421, characters[1])
		};
		Hcb0402_Novel novel [] = {
			new Hcb0402_Novel(),
			new Hcb0402_Novel("책1", "작가1", 20201120, characters[2], "무슨상", 432)
		};
		Hcb0402_Game game [] = {
				new Hcb0402_Game(),
				new Hcb0402_Game("게임1", "제작사1", 20240726, characters[3], "PS5", 80)
		};
		
		//method 실험
		System.out.println("=====method overload=====");
		movie[2].introduce();
		movie[2].introduce("영화2","감독2");
		System.out.println("=====method overriding=====");
		movie[1].introduce();
		novel[1].introduce();
		game[1].introduce();
		
		//getter,setter 실험
		int score;
		System.out.println("=====getter=====");
		score = game[1].getScore();
		System.out.println("1번게임의 메타스코어는" + score + "점");
		System.out.println("나는 90점이라고 생각해");
		game[1].setScore(90);
		score=game[1].getScore();
		System.out.println("1번게임의 메타스코어는" + score + "점");
	}

}
