package ex_241023_ch7.homework;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Lsh0708Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<Lsh0708Person> a = new ArrayList<Lsh0708Person>();

		Scanner scanner = new Scanner(System.in);
		boolean run = true;
		while (run) {
			System.out.println("===============================================");
			System.out.println("연락처 프로그램 만들기, ArrayList 버전");
			System.out.println("1.입력, 2.출력, 3.검색, 4.삭제, 5.랜덤, 6.종료");
			System.out.println("===============================================");
			String num = scanner.next();
			switch (num) {
			case "1":
				System.out.print("이름을 입력하세요 : ");
				String name = scanner.next();
				System.out.print("번호을 입력하세요 : ");
				String number = scanner.next();
				Lsh0708Person person = new Lsh0708Person(name, number);
				a.add(person);
				break;
			case "2":
				for (int i = 0; i < a.size(); i++) {
					((Lsh0708Person) a.get(i)).showInfo();
				}
				break;
			case "3":
				System.out.print("검색할 사용자를 입력하세요 :");
				String searchUser = scanner.next();
				int selectIndex = 0;
				for (int i = 0; i < a.size(); i++) {
					String selectName = ((Lsh0708Person) a.get(i)).getName();
					if (searchUser.equals(selectName)) {
						((Lsh0708Person) a.get(i)).showInfo();
					}
				}
				break;
			case "4":
				System.out.print("삭제할 사용자를 입력하세요 :");
				String removeUser = scanner.next();
				int selectIndex1 = 0;
				int removeCount=0;
				for (int i = 0; i < a.size(); i++) {
					String selectName = ((Lsh0708Person) a.get(i)).getName();
					if (removeUser.equals(selectName)) {
						a.remove(i);
						System.out.println(removeUser+"사용자를 삭제했습니다.");
						removeCount++;
					}
				}
				if(removeCount==0) {
					System.out.println("존재하지 않는 사용자입니다. 사용사 삭제에 실패했습니다!");
				}
				break;
			case "5":
				Random random = new Random();
				int randomUser = random.nextInt(a.size());
				System.out.println("추천 사용자!");
				((Lsh0708Person) a.get(randomUser)).showInfo();
				break;
			case "6":
				System.out.println("프로그램을 종료합니다!!!!!!");
				run=false;
				break;
			default:
				System.out.println("메뉴를 다시 선택하세요!");
			}
		}
	}
}
