package ex_241023_ch7.homework2;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Lsh1208_241024homework_main extends Lsh1208_241024homework_class {

	static HashMap<String, Lsh1208_241024homework_class> phoneNumber = new HashMap<String, Lsh1208_241024homework_class>();

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		while (true) {
			int menuNumber;
			System.out.println("===================연락처 프로그램=====================\r\n"
					+ "1. 입력, 2. 출력 , 3. 검색 , 4. 삭제, 5. 랜덤 , 6. 종료\r\n"
					+ "===================================================");
			System.out.print("원하시는 메뉴번호를 입력해주세요>>>");
			menuNumber = scan.nextInt();
			if (menuNumber == 6) {
				System.out.println("종료되었습니다.");
				break;
			}
			menu(menuNumber);
		}
	}

	public static void menu(int menuNumber) {
		int num = menuNumber;
		if (num == 1) {
			insert();
		} else if (num == 2) {
			display();
		} else if (num == 3) {
			search();
		} else if (num == 4) {
			delete();
		} else if (num == 5) {
			random();
		} else {
			System.out.println("잘못된 메뉴입니다. 다시 입력해주세요.");
		}
	}

	public static void insert() {
		Lsh1208_241024homework_class contact = new Lsh1208_241024homework_class();
		System.out.print("이름) ");
		String name = scan.next();
		contact.setName(name);

		System.out.print("전화번호) ");
		String number = scan.next();
		contact.setNumber(number);

		// 현재 시간저장
		Date currentDate = new Date();
		contact.setDate(currentDate);

		// hash저장
		phoneNumber.put(name, contact);
		System.out.println("[" + name + "]연락처가 등록되었습니다.");
	}

	public static void display() {
		System.out.println("-------------------저장된 전화번호부--------------------\n");
		for (Lsh1208_241024homework_class contact : phoneNumber.values()) {
			System.out.println(
					"이름: " + contact.getName() + " 전화번호: " + contact.getNumber() + " 등록일: " + contact.getDate());
		}
		System.out.println("\n---------------------------------------------------");
	}

	public static void search() {
		String searchName;
		if (phoneNumber.size() == 0) {
			System.out.println("전화번호부가 없습니다.");
		}
		System.out.print("검색할 이름) ");
		searchName = scan.next();
		if (phoneNumber.containsKey(searchName)) {
			Lsh1208_241024homework_class search = phoneNumber.get(searchName);
			System.out
					.println("이름: " + search.getName() + " 전화번호: " + search.getNumber() + " 등록일: " + search.getDate());
		}
	}

	public static void delete() {
		String deleteName;

		if (phoneNumber.size() == 0) {
			System.out.println("전화번호부가 없습니다.");
		}
		while (true) {
			System.out.print("삭제할 이름 입력) ");
			deleteName = scan.next();

			if (phoneNumber.containsKey(deleteName)) {
				System.out.print("[" + deleteName + "] 정보를 삭제하시겠습니까?(y/n)");
				if (scan.next().equals("y")) {
					phoneNumber.remove(deleteName);
					System.out.println("[" + deleteName + "]연락처가 삭제 되었습니다.");
					break;
				} else if (scan.next().equals("n")) {
					System.out.println("삭제가 취소되었습니다.");
					break;
				} else {
					System.out.println("잘못된 입력입니다.");
				}
			} else {

				System.out.println("[" + deleteName + "] 은/는 없는 전화번호 입니다.");
			}
		}
	}

	public static void random() {
		if (phoneNumber.size() == 0) {
			System.out.println("전화번호부가 없습니다.");
		}
		String[] keys = phoneNumber.keySet().toArray(new String[0]);

		Random random = new Random();

		int randomIndex = random.nextInt(keys.length);

		String randomKey = keys[randomIndex];
		Lsh1208_241024homework_class randomContact = phoneNumber.get(randomKey);

		System.out.println("-------------------랜덤 출력 전화번호--------------------\n");
		System.out.println("이름: " + randomContact.getName() + " 전화번호: " + randomContact.getNumber() + " 등록일: "
				+ randomContact.getDate());
		System.out.println("\n---------------------------------------------------");
	}

}
