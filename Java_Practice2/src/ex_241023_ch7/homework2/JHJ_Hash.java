package ex_241023_ch7.homework2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;


public class JHJ_Hash {

	public static void main(String[] args) {

		HashMap<String, JHJ_Person> dic = new HashMap<String, JHJ_Person>();

		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		while (true) {
			System.out.println("===========================================");
			System.out.println("1. 입력, 2. 출력 , 3. 검색 , 4. 삭제, 5. 랜덤 , 6. 종료");
			System.out.println("===========================================");
			System.out.print("선택: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				System.out.print("이름 입력: ");
				String name = scanner.nextLine();

				System.out.print("연락처 입력: ");
				String number = scanner.nextLine();


				dic.put(name, new JHJ_Person(number));
				System.out.println("연락처가 등록되었습니다.");
				break;

			case 2:
				if (dic.isEmpty()) {
					System.out.println("등록된 연락처가 없습니다.");
				} else {
					System.out.println("등록된 연락처");

					Set<String> keys = dic.keySet();
					Iterator<String> it = keys.iterator();
					while (it.hasNext()) {
						String key = it.next();
						System.out.println("이름: " + key);
						dic.get(key).displayPersonInfo();
					}
				}
				break;

			case 3:
				System.out.print("검색할 이름 입력: ");
				String search = scanner.nextLine();
				JHJ_Person kor = dic.get(search);
				
				if (kor == null)
					System.out.println("해당 이름의 연락처가 없습니다.");
				else {
					System.out.println("이름: " + search);
					dic.get(search).displayPersonInfo();
				}
				break;
//					

			case 4:
				System.out.print("삭제할 이름 입력: ");
				String deleteName = scanner.nextLine();
				JHJ_Person dname = dic.remove(deleteName);


				if (dname != null) {
					System.out.println("연락처가 삭제되었습니다.");
				} else {
					System.out.println("해당 이름의 연락처가 없습니다.");
				}
				break;

			case 5:
				if (!dic.isEmpty()) {
					ArrayList<String> keysList = new ArrayList<>(dic.keySet());
					int randomIndex = random.nextInt(keysList.size());
					String randomKey = keysList.get(randomIndex); 

					System.out.println("이름: " + randomKey);
					dic.get(randomKey).displayPersonInfo();
				} else {
					System.out.println("등록된 연락처가 없습니다.");
				}
				break;

			case 6:
				System.out.println("프로그램을 종료합니다.");
				scanner.close();
				return;

			default:
				System.out.println("올바른 번호를 입력하세요.");
			}
		}
	}

}
