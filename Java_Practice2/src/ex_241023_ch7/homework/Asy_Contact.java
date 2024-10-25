package ex_241023_ch7.homework;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Asy_Contact {
	private static ArrayList<Asy_Person> contactList = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean running = true;
		while (running) {
			System.out.println("메뉴를 선택하세요.");
			System.out.println("1. 입력, 2. 출력, 3. 검색, 4. 삭제, 5. 랜덤, 6.종료");
			int choice = sc.nextInt();
			sc.nextLine();

			while (choice == 1) {
				addContact(sc);
				break;
			}
			while (choice == 2) {
				watchContact(sc);
				break;
			}
			while (choice == 3) {
				searchContact(sc);
				break;
			}
			while (choice == 4) {
				deleteContact(sc);
				break;
			}
			while (choice == 5) {
				randomContact(sc);
				break;
			}
			while (choice == 6) {
				running = false;
				System.out.println("종료 합니다.");
				break;
			}
		}
		sc.close();
	}

	private static void addContact(Scanner sc) {
		System.out.println("이름 입력 : ");
		String name = sc.nextLine();
		System.out.println("번호 입력: ");
		String number = sc.nextLine();

		Asy_Person Asy_Person = new Asy_Person(name, number);
		contactList.add(Asy_Person);
		System.out.println("연락처가 등록되었습니다.");
	}

	private static void watchContact(Scanner sc) {
		if (contactList.isEmpty()) {
			System.out.println("등록된 연락처가 없습니다.");
		} else {
			for (Asy_Person person : contactList) {
				System.out.println(person);
			}
		}
	}

	private static void searchContact(Scanner sc) {
		System.out.print("검색할 이름을 입력하세요: ");
		String name = sc.nextLine();
		boolean found = false;
		for (Asy_Person person : contactList) {
			if (person.Name().equalsIgnoreCase(name)) {
				System.out.println(person);
				found = true;
			}
		}
		if (!found) {
			System.out.println("해당 이름의 연락처가 없습니다.");
		}
	}

	private static void deleteContact(Scanner sc) {
		System.out.print("삭제할 이름을 입력하세요: ");
		String name = sc.nextLine();
		boolean removed = false;
		for (int i = 0; i < contactList.size(); i++) {
			if (contactList.get(i).Name().equalsIgnoreCase(name)) {
				contactList.remove(i);
				System.out.println("연락처가 삭제되었습니다.");
				removed = true;
				break;
			}
		}

		if (!removed) {
			System.out.println("해당 이름의 연락처가 없습니다.");
		}
	}

	private static void randomContact(Scanner sc) {
		Random random = new Random();
		int index = random.nextInt(contactList.size());
		System.out.println("랜덤 : " + contactList.get(index));
		String name = sc.nextLine();
	}

}
