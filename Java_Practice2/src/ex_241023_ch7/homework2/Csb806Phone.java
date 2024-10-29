package ex_241023_ch7.homework2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Csb806Phone {
	public static void main(String[] args) {
		HashMap<String, Csb806Class> contactMap = new HashMap<String, Csb806Class>();
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

			if (choice == 1) { // 입력
				System.out.println("이름을 입력하세요: ");
				String name = scanner.nextLine();

				System.out.println("연락처를 입력하세요:");
				String phoneNumber = scanner.nextLine();

				String registrationDate = dateFormat.format(new Date());
				Csb806Class person = new Csb806Class(name, phoneNumber, registrationDate);
				contactMap.put(name, person);
				System.out.println("연락처가 등록되었습니다.");
				System.out.println("등록 날짜: " + registrationDate);

			} else if (choice == 2) { // 출력
				if (contactMap.isEmpty()) {
					System.out.println("등록된 연락처가 없습니다.");
				} else {
					System.out.println("등록된 연락처 목록:");
					for (Csb806Class person : contactMap.values()) {
						person.printPersonInfo();
					}
				}

			} else if (choice == 3) { // 검색
				System.out.print("검색할 이름을 입력하세요: ");
				String searchName = scanner.nextLine();
				Csb806Class person = contactMap.get(searchName);

				if (person != null) {
					person.printPersonInfo();
				} else {
					System.out.println("해당 이름의 연락처가 없습니다.");
				}

			} else if (choice == 4) { // 삭제
				System.out.print("삭제할 이름을 입력하세요: ");
				String deleteName = scanner.nextLine();

				if (contactMap.remove(deleteName) != null) {
					System.out.println("연락처가 삭제되었습니다.");
				} else {
					System.out.println("해당 이름의 연락처를 찾을 수 없습니다.");
				}
			} else if (choice == 5) { // 랜덤
				if (contactMap.isEmpty()) {
					System.out.println("등록된 연락처가 없습니다.");
				} else {
					Object[] keys = contactMap.keySet().toArray(); // HashMap의 키를 배열로 변환
					String randomKey = (String) keys[random.nextInt(keys.length)];
					System.out.println("랜덤으로 선택된 연락처:");
					contactMap.get(randomKey).printPersonInfo();
				}

			} else if (choice == 6) { // 종료
				System.out.println("프로그램이 종료되었습니다.");
				break;
			}
		}

		scanner.close();

	}
}
