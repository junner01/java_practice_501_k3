package ex_241023_ch7.homework;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Pkh_0827_PersonManager {
	public static void main(String[] args) {
		ArrayList<Pkh_0827_Person> persons = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		while (true) {
			System.out.println("\n1. 입력");
			System.out.println("2. 출력");
			System.out.println("3. 검색");
			System.out.println("4. 삭제");
			System.out.println("5. 랜덤");
			System.out.println("6. 종료");
			System.out.print("메뉴 선택: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); 

			switch (choice) {
			case 1: 
				System.out.print("이름을 입력하세요: ");
				String name = scanner.nextLine();
				System.out.print("연락처를 입력하세요: ");
				String num = scanner.nextLine(); // num으로 변경

				persons.add(new Pkh_0827_Person(name, num, name));
				System.out.println("입력이 완료되었습니다.");
				break;

			case 2:
				if (persons.isEmpty()) {
					System.out.println("출력할 데이터가 없습니다.");
				} else {
					for (Pkh_0827_Person person : persons) {
						
						System.out.println("Name: " + person.getName() + ", Num: " + person.getNum() + ", Date: "
								+ person.getDate());
					}
				}
				break;

			case 3: 
				System.out.print("검색할 이름을 입력하세요: ");
				String searchName = scanner.nextLine();
				boolean found = false;
				for (Pkh_0827_Person person : persons) {
					if (person.getName().equals(searchName)) {
						System.out.println("검색 결과: Name: " + person.getName() + ", Num: " + person.getNum() + ", Date: "
								+ person.getDate());
						found = true;
						break;
					}
				}
				if (!found) {
					System.out.println("해당 이름의 데이터가 없습니다.");
				}
				break;

			case 4: 
				System.out.print("삭제할 이름을 입력하세요: ");
				String deleteName = scanner.nextLine();
				boolean removed = false;
				for (int i = 0; i < persons.size(); i++) {
					if (persons.get(i).getName().equals(deleteName)) {
						persons.remove(i);
						System.out.println("삭제가 완료되었습니다.");
						removed = true;
						break;
					}
				}
				if (!removed) {
					System.out.println("해당 이름의 데이터가 없습니다.");
				}
				break;

			case 5: 
				if (persons.isEmpty()) {
					System.out.println("랜덤으로 조회할 데이터가 없습니다.");
				} else {
					Pkh_0827_Person randomPerson = persons.get(random.nextInt(persons.size()));
					System.out.println("랜덤 조회 결과: Name: " + randomPerson.getName() + ", Num: " + randomPerson.getNum()
							+ ", Date: " + randomPerson.getDate());
				}
				break;

			case 6: 
				System.out.println("프로그램을 종료합니다.");
				scanner.close();
				return;

			default:
				System.out.println("잘못된 입력입니다. 다시 선택하세요.");
				break;
			}
		}
	}

}
