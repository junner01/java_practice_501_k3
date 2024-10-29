package ex_241023_ch7.homework2;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Jih0316_HashMap_main {

	public static void contactProgram(Scanner scanner, HashMap<String, Jih0316_HashMap_Person> contacts,
			Random random) {
		boolean running = true;
		while (running) {
			// 메뉴 출력
			System.out.println("======================================================");
			System.out.println("메뉴 선택: 1. 입력 2. 출력 3. 검색 4. 삭제 5. 랜덤 출력 6. 종료");
			System.out.println("======================================================");

			int choice = -1; // 초기값 설정
			boolean validInput = false;

			// 입력값이 유효할 때까지 반복
			while (!validInput) {
				System.out.print("번호를 입력하세요: ");
				try {
					choice = scanner.nextInt();
					scanner.nextLine(); // 입력 버퍼 정리
					validInput = true; // 유효한 입력이 들어왔을 때만 종료
				} catch (InputMismatchException e) {
					System.out.println("숫자를 입력해야 합니다. 다시 입력하세요.");
					scanner.nextLine(); // 잘못된 입력 버퍼 제거
				}
			}

			switch (choice) {
			case 1: // 입력
				String name;

				// 이름 입력
				do {
					System.out.print("이름을 입력하세요: ");
					name = scanner.nextLine();
					if (name.trim().isEmpty()) {
						System.out.println("이름을 입력해야 합니다.");
					}
				} while (name.trim().isEmpty());

				// 연락처 입력
				String phone; // 전화번호를 여기에 선언합니다.
				do {
					System.out.print("연락처를 입력하세요: ");
					phone = scanner.nextLine();
				} while (phone.trim().isEmpty());

				// 등록 시간 및 Person 인스턴스 생성
				String regTime = Jih0316_DateUtil.getCurrentDateTime();
				Jih0316_HashMap_Person person = new Jih0316_HashMap_Person(name, phone, regTime);
				contacts.put(name, person);
				System.out.println("등록되었습니다.");
				break;

			case 2: // 출력
				System.out.println("\n전체 연락처 목록:");
				if (contacts.isEmpty()) {
					System.out.println("등록된 연락처가 없습니다.");
				} else {
					contacts.values().forEach(System.out::println);
				}
				break;

			case 3: // 검색
				System.out.print("검색할 이름을 입력하세요: ");
				String searchName = scanner.nextLine();
				Jih0316_HashMap_Person foundPerson = contacts.get(searchName);
				if (foundPerson != null) {
					System.out.println(foundPerson);
				} else {
					System.out.println("해당 이름의 연락처가 없습니다.");
				}
				break;

			case 4: // 삭제
				System.out.print("삭제할 이름을 입력하세요: ");
				String deleteName = scanner.nextLine();
				if (contacts.remove(deleteName) != null) {
					System.out.println(deleteName + " 연락처가 삭제되었습니다.");
				} else {
					System.out.println("해당 이름의 연락처가 없습니다.");
				}
				break;

			case 5: // 랜덤 출력
				if (!contacts.isEmpty()) {
					Object[] values = contacts.values().toArray();
					Jih0316_HashMap_Person randomPerson = (Jih0316_HashMap_Person) values[random
							.nextInt(values.length)];
					System.out.println("\n랜덤 선택된 연락처: ");
					System.out.println(randomPerson);
				} else {
					System.out.println("등록된 연락처가 없습니다.");
				}
				break;

			case 6: // 종료
				System.out.println("프로그램을 종료합니다.");
				running = false; // while 문 종료
				break;

			default:
				System.out.println("잘못된 번호입니다. 다시 입력하세요.");
				break;
			}
		}
	}

	public static void main(String[] args) {
		HashMap<String, Jih0316_HashMap_Person> contacts = new HashMap<>();
		Random random = new Random();
		Scanner scanner = new Scanner(System.in);
		contactProgram(scanner, contacts, random);
		scanner.close();
	}
}
