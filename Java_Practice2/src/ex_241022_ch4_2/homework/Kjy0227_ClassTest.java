package ex_241022_ch4_2.homework;

import java.util.Random;

public class Kjy0227_ClassTest {

	public static void main(String[] args) {
		// 상속받은 메서드 사용
		Kjy0227_Cat cat = new Kjy0227_Cat("고양이", "13~20년");
		cat.introduce();
		cat.sound();
		
		// 부모(Animal) 타입으로 배열 생성 및 랜덤 추천
		Kjy0227_Bird bird = new Kjy0227_Bird("카나리아", "20년");
		Kjy0227_Frog frog = new Kjy0227_Frog("개구리", "10년");
		
		Kjy0227_Animal[] animals = {cat, bird, frog};
		randomAnimal(animals);
	}
	
	public static void randomAnimal(Kjy0227_Animal[] animals) {
		Random random = new Random();
		Kjy0227_Animal chosenAnimal = animals[random.nextInt(3)];
		chosenAnimal.introduce();
		chosenAnimal.sound();
	}

}
