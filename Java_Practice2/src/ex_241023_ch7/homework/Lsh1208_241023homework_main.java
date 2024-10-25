package ex_241023_ch7.homework;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Lsh1208_241023homework_main extends Lsh1208_241023homework_class {

	static ArrayList<Lsh1208_241023homework_class> phoneNumber = new ArrayList<>();

	static Scanner scan1 = new Scanner(System.in);

	public static void main(String[] args) {

		while (true) {
			int menuNumber;
			System.out.println("===================================================\r\n"
					+ "연락처 프로그램 만들기, ArrayList 버전.\r\n" + "1. 입력, 2. 출력 , 3. 검색 , 4. 삭제, 5. 랜덤 , 6. 종료\r\n"
					+ "===================================================");
			System.out.print("원하시는 메뉴번호를 입력해주세요>>>");
			menuNumber = scan1.nextInt();
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
			select();
		} else if (num == 4) {
			delete();
		} else if (num == 5) {
			random();
		} else {
			System.out.println("잘못된 메뉴입니다. 다시 입력해주세요.");
		}
	}

	public static void insert() {
		System.out.print("몇명을 등록할건가요?");
		int insertnum = scan1.nextInt();
		for (int i = 0; i < insertnum; i++) {
			Lsh1208_241023homework_class contact = new Lsh1208_241023homework_class();
			System.out.print("이름>>");
			String name = scan1.next();
			contact.setName(name);

			System.out.print("전화번호");
			String number = scan1.next();
			contact.setNumber(number);

			Date currentDate = new Date(); // 현재 시간 저장
			contact.setDate(currentDate);

			phoneNumber.add(contact);
			System.out.println(name + "연락처가 등록되었습니다.");
		}
		System.out.println(insertnum + "명이 등록되었습니다.");
	}

	public static void display() {
		if (phoneNumber.size() == 0) {
			System.out.println("등록된 사람이 없습니다.");
		}
		for (int i = 0; i < phoneNumber.size(); i++) {
			Lsh1208_241023homework_class contact = phoneNumber.get(i);
			System.out.print("이름: " + contact.getName() + "\n전화번호: " + contact.getNumber() + "\n등록일: "
					+ contact.getDate() + "\n");
		}
	}

	public static void select() {
		System.out.print("이름을 검색하세요>>");
		String search = scan1.next();
		for (int i = 0; i < phoneNumber.size(); i++) {
			Lsh1208_241023homework_class contact = phoneNumber.get(i);
			if (contact.getName().equals(search)) {
				System.out.println("[" + search + "]의 검색결과");
				System.out.print("이름: " + contact.getName() + "\n전화번호: " + contact.getNumber() + "\n등록일: "
						+ contact.getDate() + "\n");
				break;
			} else {
				System.out.println("[" + search + "]의 검색결과 없음");
			}
		}
	}

	public static void delete() {
		System.out.print("삭제할 이름을 입력하세요.>>");
		String deleteName = scan1.next();
		for (int i = 0; i < phoneNumber.size(); i++) {
			Lsh1208_241023homework_class contact = phoneNumber.get(i);
			if (contact.getName().equals(deleteName)) {
				System.out.print(deleteName + "를 삭제하시겠습니까(y/n)>>");
				if (scan1.next().equals("y")) {
					phoneNumber.remove(i);
					System.out.println("[" + deleteName + "]연락처가 삭제 되었습니다.");
					break;
				} else if (scan1.next().equals("n")) {
					delete();
				} else {
					System.out.println("잘못된 입력입니다.");
					break;
				}
			}
		}
	}

	public static void random() {
		Random randomName = new Random();
		int randomIndex = randomName.nextInt(phoneNumber.size());
		Lsh1208_241023homework_class contact = phoneNumber.get(randomIndex);
		System.out.println("랜덤결과");
		System.out.print(
				"이름: " + contact.getName() + "\n전화번호: " + contact.getNumber() + "\n등록일: " + contact.getDate() + "\n");
	}
}
