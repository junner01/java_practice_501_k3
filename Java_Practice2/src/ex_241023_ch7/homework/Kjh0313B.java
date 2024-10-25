package ex_241023_ch7.homework;

import java.time.LocalDate;
import java.util.*;

import ex_241023_ch7.homework.Kjh0313A;

public class Kjh0313B {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Kjh0313A> person = new ArrayList<Kjh0313A>();
		int selec = 0;

		while (selec != 6) {
			System.out.println("==================================================");
			System.out.println("1. 입력, 2. 출력 , 3. 검색 , 4. 삭제, 5. 랜덤 , 6. 종료");
			System.out.println("==================================================");
			try {
				selec = scanner.nextInt();
			} catch (Exception e) {
				System.out.println("숫자만 입력해주세요");
				scanner.nextLine();
				continue;
			}

			switch (selec) {
			case 1:
				String nameInput;
				int phoneInput;
				System.out.println("====================전화번호 등록====================");
				System.out.println("이름을 입력하세요");
				nameInput = scanner.next();
				System.out.println("전화번호를 입력하세요");
				try {
					phoneInput = scanner.nextInt();
				} catch (Exception e) {
					scanner.nextLine();
					continue;
				}
				LocalDate date = LocalDate.now();

				person.add(new Kjh0313A(nameInput, phoneInput, date));
				System.out.println("등록한 이름 : " + person.getLast().getName());
				System.out.println("등록한 전화번호 : " + person.getLast().getPhone());
				System.out.println("등록한 날짜 : " + person.getLast().getDate());
			
			// 2입력
			case 2:
				System.out.println("====================현재 등록된 전화번호====================");
				for (int i = 0; i < person.size(); i++) {
					System.out.println((i + 1) + ". 이름: " + person.get(i).getName() + "  전화번호: "
							+ person.get(i).getPhone() + "  등록일: " + person.get(i).getDate());
				}
				break;

			case 3:
				Iterator<Kjh0313A> searchIterator = person.iterator();
				Boolean found = false;
				System.out.println("===============검색할 전화번호 입력===============");

				String searchInput = scanner.next();

				while (searchIterator.hasNext()) {
					Kjh0313A searchPerson = searchIterator.next();
					if (searchPerson.getName().equals(searchInput)) {
						System.out.println(searchInput + " 님의 전화번호는 " + searchPerson.getPhone() + " 입니다. "
								+ searchPerson.getDate() + " 에 등록되었습니다");
						found = true;
					}
				}
				if (!found)
					System.out.println(searchInput + " 님의 전화번호는 등록되지 않았습니다");
				break;

			case 4:
				Iterator<Kjh0313A> deleteIterator = person.iterator();
				Boolean deleted = false;
				System.out.println("===============삭제할 전화번호 입력===============");
				String deleteInput = scanner.next();

				while (deleteIterator.hasNext()) {
					Kjh0313A searchPerson = deleteIterator.next();
					if (searchPerson.getName().equals(deleteInput)) {
						deleteIterator.remove();
						System.out.println(deleteInput + " 님의 연락처가 삭제되었습니다");
						deleted = true;
					}
				}
				if (!deleted)
					System.out.println("찾으시는 연락처는 없습니다");
				break;

			case 5:
				Random random = new Random();
				try {
					int rNum = random.nextInt(person.size());
					System.out.print(person.get(rNum).getName() + " 님의 전화번호는 ");
					System.out.print(person.get(rNum).getPhone() + " 번 이고 ");
					System.out.println(person.get(rNum).getDate() + " 에 등록되었습니다");
				} catch (Exception e) {
					System.out.println("등록된 전화번호가 없습니다");
				}
				break;
			}
		}
		scanner.close();
	}

}
