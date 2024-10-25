package ex_241023_ch7.homework;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Csb806Phone {
	public static void main(String[] args) {
		ArrayList<Csb806Class> contactList = new ArrayList<Csb806Class>();
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		while (true) {
			System.out.println("===================");
			System.out.println("연락처 프로그램 만들기, Arryalist 버전");
			System.out.println("1.입력, 2.출력, 3.검색, 4.삭제, 5.랜덤, 6.종료");
			System.out.println("==============");
			System.out.println("선택: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			if (choice == 1) { //입력
				System.out.println("이름을 입력하세요: ");
				String name = scanner.nextLine();
				
				System.out.println("연락처를 입력하세요:");
				String phoneNumber = scanner.nextLine();
				
				String registrationDate = dateFormat.format(new Date());
				Csb806Class person = new Csb806Class(name, phoneNumber, registrationDate);
                contactList.add(person);
                System.out.println("연락처가 등록되었습니다.");
                System.out.println("등록 날짜: " + registrationDate);
				
			} else if (choice == 2) { // 출력
				if (contactList.isEmpty()) {
					System.out.println("등록된 연락처가 없습니다.");
				} else {
					System.out.println("등록된 연락처 목록:");
					for (Csb806Class person : contactList) {
						person.printPersonInfo();
					}
				}

			} else if (choice == 3) { // 검색
				System.out.print("검색할 이름을 입력하세요: ");
				String searchName = scanner.nextLine();
				boolean found = false;
				for (Csb806Class person : contactList) {
					if (person.getName().equals(searchName)) {
						person.printPersonInfo();
						found = true;
					}
				}
				if (!found) {
					System.out.println("해당 이름의 연락처가 없습니다.");
				}

			} else if (choice == 4) { //삭제
				System.out.print("삭제할 이름을 입력하세요: ");
				String deleteName = scanner.nextLine();
				boolean removed = false;
				for (int i = 0; i < contactList.size(); i++) {
					if (contactList.get(i).getName().equals(deleteName)) {
						contactList.remove(i);
						System.out.println("연락처가 삭제되었습니다.");
						removed = true;
						break;
					}
				}
				if (!removed) {
					System.out.println("해당 이름의 연락처를 찾을 수 없습니다.");
				}

			} else if (choice == 5) { // 랜덤
				if (contactList.isEmpty()) {
					System.out.println("등록된 연락처가 없습니다.");
				} else {
					int randomIndex = random.nextInt(contactList.size());
					System.out.println("랜덤으로 선택된 연락처:");
					contactList.get(randomIndex).printPersonInfo();
				}

			} else if (choice == 6) { // 종료
				System.out.println("프로그램이 종료되었습니다.");
				break;
			}
		}

		scanner.close();
	}
}
