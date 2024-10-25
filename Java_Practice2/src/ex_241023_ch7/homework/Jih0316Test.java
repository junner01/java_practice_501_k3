package ex_241023_ch7.homework;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// Person 클래스 정의
class PersonJih0316 {
	private String name;
	private String phone;
	private String registrationTime;

	public PersonJih0316(String name, String phone) {
		this.name = name;
		this.phone = phone;
		this.registrationTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getRegistrationTime() {
		return registrationTime;
	}

	@Override
	public String toString() {
		return "이름: " + name + ", 연락처: " + phone + ", 등록 시간: " + registrationTime;
	}
}

public class Jih0316Test {

	public static void main(String[] args) {
		ArrayList<PersonJih0316> people = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		boolean running = true;
		while (running) {
			// 메뉴 출력
			System.out.println("======================================================");
			System.out.println("메뉴 선택: 1. 입력 2. 출력 3. 검색 4. 삭제 5. 랜덤 출력 6. 종료");
			System.out.println("======================================================");
			System.out.print("번호를 입력하세요: ");

			int choice = scanner.nextInt();
			scanner.nextLine(); // 입력 버퍼 정리

			if (choice == 1) {
				// 입력
				String name;
				String phone;
				boolean isDuplicate;

				do {
					System.out.print("이름을 입력하세요: ");
					name = scanner.nextLine();

					isDuplicate = false;
					for (PersonJih0316 person : people) {
						if (person.getName().equals(name)) {
							System.out.println("이미 등록된 이름입니다.");
							isDuplicate = true;
							break;
						}
					}

					if (name.trim().isEmpty()) {
						System.out.println("이름을 입력해야 합니다.");
					}
				} while (name.trim().isEmpty() || isDuplicate);

				do {
					System.out.print("연락처를 입력하세요: ");
					phone = scanner.nextLine();

					isDuplicate = false;
					for (PersonJih0316 person : people) {
						if (person.getPhone().equals(phone)) {
							System.out.println("이미 등록된 연락처입니다.");
							isDuplicate = true;
							break;
						}
					}

					if (phone.trim().isEmpty()) {
						System.out.println("연락처를 입력해야 합니다.");
					}
				} while (phone.trim().isEmpty() || isDuplicate);

				people.add(new PersonJih0316(name, phone));
				System.out.println("등록되었습니다.");
			} else if (choice == 2) {
				// 출력
				System.out.println("\n전체 연락처 목록:");
				if (people.isEmpty()) {
					System.out.println("등록된 연락처가 없습니다.");
				} else {
					for (PersonJih0316 person : people) {
						System.out.println(person);
					}
				}
			} else if (choice == 3) {
				// 검색
				System.out.print("검색할 이름을 입력하세요: ");
				String searchName = scanner.nextLine();
				boolean found = false;
				for (PersonJih0316 person : people) {
					if (person.getName().equals(searchName)) {
						System.out.println(person);
						found = true;
					}
				}
				if (!found) {
					System.out.println("해당 이름의 연락처가 없습니다.");
				}
			} else if (choice == 4) {
				// 삭제
				System.out.print("삭제할 이름을 입력하세요: ");
				String deleteName = scanner.nextLine();
				boolean found = false;
				for (int i = 0; i < people.size(); i++) {
					if (people.get(i).getName().equals(deleteName)) {
						people.remove(i);
						System.out.println(deleteName + " 연락처가 삭제되었습니다.");
						found = true;
						break;
					}
				}
				if (!found) {
					System.out.println("해당 이름의 연락처가 없습니다.");
				}
			} else if (choice == 5) {
				// 랜덤 출력
				if (people.size() > 0) {
					PersonJih0316 randomPerson = people.get(random.nextInt(people.size()));
					System.out.println("\n랜덤 선택된 연락처: ");
					System.out.println(randomPerson);
				} else {
					System.out.println("등록된 연락처가 없습니다.");
				}
			} else if (choice == 6) {
				// 종료
				System.out.println("프로그램을 종료합니다.");
				running = false; // while문 종료
			} else {
				System.out.println("잘못된 번호입니다. 다시 입력하세요.");
			}
		}

		scanner.close();
	}
}
