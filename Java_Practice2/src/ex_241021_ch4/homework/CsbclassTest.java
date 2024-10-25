package ex_241021_ch4.homework;

import java.util.Random;

public class CsbclassTest {

	public static void main(String[] args) {

		Csb806Food Food = new Csb806Food();
		Food.name = "김치찌개";
		Food.price = 7500;
		String[] FoodMaterialStrings = {"김치","돼지고기","양파"};
		Food.material = FoodMaterialStrings;
		Food.introduce();
		
		Csb806Food Food2 = new Csb806Food();
		Food2.name = "햄버거";
		Food2.price = 8000;
		String[] FoodMaterialStrings2 = {"소고기","빵","치즈"};
		Food2.material = FoodMaterialStrings2;
		Food2.introduce();
		
		Csb806Food Food3 = new Csb806Food();
		Food3.name = "삼각김밥";
		Food3.price = 1500;
		String[] FoodMaterialStrings3 = {"밥","김","참치"};
		Food3.material = FoodMaterialStrings3;
		Food3.introduce();
		
		Csb806Food menu[] = new
				Csb806Food[3];menu[0]=Food;menu[1]=Food2;menu[2]=Food3;
				
				Random random = new Random();
				int rnd = random.nextInt(3);
				menu[rnd].introduce();
	}

}
