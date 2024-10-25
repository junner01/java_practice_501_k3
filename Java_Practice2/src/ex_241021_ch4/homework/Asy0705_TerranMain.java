package ex_241021_ch4.homework;

import java.util.Random;

public class Asy0705_TerranMain {
    public static void main(String[] args) {
        // 유닛 인스턴스 생성
        TerranUnit vulture = new Vulture();
        TerranUnit wraith = new Wraith();
        TerranUnit siegeTank = new SiegeTank();

        // 배열에 유닛 담기
        TerranUnit[] unitArray = {vulture, wraith, siegeTank};

        // 랜덤 유닛 추천
        Random random = new Random();
        int randomIndex = random.nextInt(unitArray.length);
        TerranUnit selectedUnit = unitArray[randomIndex];

        // 추천된 유닛 정보 출력
        System.out.println("추천된 유닛:");
        selectedUnit.showInfo();
        selectedUnit.move();
        selectedUnit.attack();
        selectedUnit.useSkill();
    }
}
