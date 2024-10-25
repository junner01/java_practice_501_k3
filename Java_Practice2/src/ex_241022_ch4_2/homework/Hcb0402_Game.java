package ex_241022_ch4_2.homework;

public class Hcb0402_Game extends Hcb0402_Movie {
	//property
	String platform;
	private int metaScore;
	
	//constructor
		Hcb0402_Game(String name, String author, int release, String[] character, String platform, int metaScore){
			this.name = name;
			this.author = author;
			this.release = release;
			this.character = character;
			this.platform = platform;
			this.metaScore = metaScore;
		}
		Hcb0402_Game(){
			this.name = tbd;
			this.author = tbd;
			this.release = 0000;
			this.character = tbdArray;
			this.platform = tbd;
			this.metaScore = 00;
		}
		
		//method getter setter
		public int getScore() {
			return metaScore;
		}
		public void setScore(int s) {
			this.metaScore = s;
		}
		
		//method overriding
		public void introduce() {
			System.out.println("이 게임의 제목은 " + name + "입니다");
			System.out.println("이 게임의 제작사는 " + author + "입니다");
			System.out.println("이 게임의 발매일은 " + release + "입니다");
			for(String c:character) {
				if(c == character[0] && c == character[character.length-1]) {
					System.out.println("이 게임의 등장인물은 " + c + " 입니다");
				} else if (c == character[0]) {
					System.out.print("이 게임의 등장인물은 " + c + ", ");
				} else if (c == character[character.length-1]) {
					System.out.println(c + " 입니다");
				} else {
					System.out.print(c + ", ");
				}
			}
			System.out.println("이 게임은 " + platform + "에서 플레이 가능합니다");
			System.out.println("이 게임은 " + metaScore + "점의 점수를 받았습니다");
		}
	}
