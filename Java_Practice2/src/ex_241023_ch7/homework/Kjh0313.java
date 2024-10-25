package ex_241023_ch7.homework;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Kjh0313 {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		ArrayList<String> personArray = new ArrayList<>();

		int set = 0;

		do {
			System.out.println("연락처 프로그램");
			System.out.println("1.입력, 2.출력 , 3.검색 , 4.삭제, 5.랜덤 , 6.종료");
			System.out.println("===================================================");

			try {
				set = scanner.nextInt(); // 사용자 입력 받기

				switch (set) {
				case 1:
					System.out.println("입력을 선택했습니다.");
					for (int i = 0; i < 1; i++) {
						System.out.println("이름을 입력하세요: ");
						String addPerson = scanner.next();
						personArray.add(addPerson);
						System.out.println("연락처를 입력하세요: ");
						String addPerson2 = scanner.next();
						personArray.add(addPerson2);
					}
					System.out.println(personArray);
			
					break;
				case 2:
					System.out.println("출력을 선택했습니다.");
					System.out.println(personArray);
					break;
				case 3:
					System.out.println("검색을 선택했습니다.");
					System.out.println("검색하실 내용을 입력하세요: ");
					String search = scanner.next();
					if (personArray.contains(search)) {
						System.out.println("검색 결과: " + personArray.get(personArray.indexOf(search)));
					} else {
						System.out.println("검색 결과가 없습니다.");
					}
					break;
				case 4:
					System.out.println("삭제를 선택했습니다.");
					System.out.println("삭제하실 이름을 입력하세요: ");
					String remove = scanner.next();
					if (personArray.contains(remove)) {
						personArray.remove(personArray.indexOf(remove));
					}
					System.out.println(personArray);
					break;
				case 5:
					System.out.println("랜덤을 선택했습니다.");

					if (personArray.size() > 0) {
						Random randClass = new Random();
						int randomIndex = randClass.nextInt(personArray.size());
						System.out.println("랜덤으로 선택된 사람: " + personArray.get(randomIndex));
					} else {
						System.out.println("리스트가 비어 있습니다.");
					}
					break;
				case 6:
					System.out.println("연락처 프로그램을 종료합니다.");
					break;
				default:
					System.out.println("잘못된 입력입니다. 다시 선택해 주세요.");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("잘못된 입력입니다. 숫자를 입력해 주세요.");
				scanner.next();
			}

		} while (set != 6);

		scanner.close();
	}
}