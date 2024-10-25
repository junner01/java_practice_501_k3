package ex_241022_ch4_2.homework;

import java.util.Random;

public class Lsh0708_MainClassVehicle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Lsh0708_Airplane airplane = new Lsh0708_Airplane();
		airplane.type = "비행기";
		airplane.price = 1000000;
		airplane.wheel = 14;
		airplane.setCountry("미국");
		airplane.moveWhere();
		airplane.introduce();
		airplane.introduce("중국");
		
		
		Lsh0708_Airplane airplane2= new Lsh0708_Airplane("보잉707",2000000,26,"포르투갈");
		airplane2.showInfo();
		
		Lsh0708_Texi texi = new Lsh0708_Texi("모범택시",10000,4,"부산");
		texi.showInfo();
		
		Lsh0708_Train train = new Lsh0708_Train("KTX",50000,200,"서울");
		train.showInfo();
		
		Lsh0708_Vehicle vehicle[] = new Lsh0708_Vehicle[3];
		vehicle[0] = airplane2;
		vehicle[1] = texi;
		vehicle[2] = train;
		
		Random random = new Random();
		int rnd = random.nextInt(3);
		
		
		System.out.print("추천 여행 - ");
		Lsh0708_Vehicle selectVehicle = vehicle[rnd];
		if(selectVehicle instanceof Lsh0708_Airplane) {
			((Lsh0708_Airplane)selectVehicle).showInfo();
		}
		else if(selectVehicle instanceof Lsh0708_Texi) {
			((Lsh0708_Texi)selectVehicle).showInfo();
		}
		else {
			((Lsh0708_Train)selectVehicle).showInfo();
		}
		
	}

}
