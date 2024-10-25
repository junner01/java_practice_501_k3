package ex_241023_ch7.homework;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Shw1013_MainPerson {
	public static void main(String[] args) {
		ArrayList<Shw1013_Person> personList = new ArrayList<Shw1013_Person>();
		Scanner scanner = new Scanner(System.in);		

		while (true) {
			System.out.println("\n===================================================");
			System.out.println("1. 입력 | 2. 출력 | 3. 검색 | 4. 삭제 | 5. 랜덤 | 6. 종료");
			System.out.println("===================================================");
			System.out.print("메뉴를 선택하세요: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				System.out.print("이름을 입력하세요: ");
				String name = scanner.nextLine();
				System.out.print("연락처를 입력하세요: ");
				String phone = scanner.nextLine();
				Shw1013_Person person = new Shw1013_Person(name, phone);
				personList.add(person);
				System.out.println("연락처가 등록되었습니다.");
				break;

			case 2:
				if (personList.isEmpty()) {
					System.out.println("등록된 연락처가 없습니다.");
				} else {
					System.out.println("\n등록된 연락처:");
					for (Shw1013_Person p : personList) {
						p.displayInfo();
					}
				}
				break;

			case 3:
				System.out.print("검색할 이름을 입력하세요: ");
				String searchName = scanner.nextLine();
				boolean found = false;
				for (Shw1013_Person p : personList) {
					if (p.getName().equals(searchName)) {
						System.out.println("검색된 연락처:");
						p.displayInfo();
						found = true;
						break;
					}
				}
				if (!found) {
					System.out.println("해당 이름의 연락처가 없습니다.");
				}
				break;

			case 4:
				System.out.print("삭제할 이름을 입력하세요: ");
				String deleteName = scanner.nextLine();
				boolean deleted = false;

				for (int i = 0; i < personList.size(); i++) {
					if (personList.get(i).getName().equals(deleteName)) {
						personList.remove(i);
						deleted = true;
						System.out.println("연락처가 삭제되었습니다.");
						break;
					}
				}

				if (!deleted) {
					System.out.println("해당 이름의 연락처가 없습니다.");
				}
				break;

			case 5:
				randomPick(personList);
				break;

			case 6:
				System.out.println("프로그램을 종료합니다.");
				scanner.close();
				return;

			default:
				System.out.println("잘못된 입력입니다. 다시 시도하세요.");
			}
		}
	}

	public static void randomPick(ArrayList<Shw1013_Person> personList) {
        if (personList.isEmpty()) {
            System.out.println("등록된 연락처가 없습니다.");
        } else {
            Random random = new Random();
            int randomIndex = random.nextInt(personList.size()); 
            System.out.println("랜덤숫자 확인: " + randomIndex);
            Shw1013_Person randomPerson = personList.get(randomIndex);
            System.out.println("랜덤으로 선택된 연락처:");
            randomPerson.displayInfo();
        }
	}
}
