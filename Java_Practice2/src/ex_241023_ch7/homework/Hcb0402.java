package ex_241023_ch7.homework;

import java.time.LocalDate;
import java.util.*;

public class Hcb0402 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// Person클래스로 ArrayList
		ArrayList<Hcb0402_Person> person = new ArrayList<Hcb0402_Person>();
		// 이 변수로 입력받은 수에 맞춰 진행
		int selec = 0;

		// 6이면 종료
		while (selec != 6) {
			System.out.println("==================================================");
			System.out.println("연락처 프로그램 만들기, ArrayList 버전.");
			System.out.println("1. 입력, 2. 출력 , 3. 검색 , 4. 삭제, 5. 랜덤 , 6. 종료");
			System.out.println("==================================================");
			try {
				selec = scanner.nextInt();
			} catch (Exception e) {
				System.out.println("숫자만 입력해주세요");
				scanner.nextLine();
				continue;
			}

			// 스위치문
			switch (selec) {
			// 1 입력
			case 1:
				String nameInput;
				int phoneInput;
				System.out.println("====================전화번호부 등록====================");
				System.out.println("이름을 입력하세요");
				nameInput = scanner.next();
				System.out.println("전화번호를 입력하세요");
				try {
					phoneInput = scanner.nextInt();
				} catch (Exception e) {
					System.out.println("전화번호는 숫자로 입력해주세요");
					// 위의 try에서 엔터를 누르는게 scanner버퍼에 남아서 22행의 scanner에 줄바꿈이 들어가는것으로 보임
					scanner.nextLine();
					continue;
				}
				LocalDate date = LocalDate.now();
				// 입력받은 정보를 인스턴스로 ArrayList에 추가
				person.add(new Hcb0402_Person(nameInput, phoneInput, date));
				// 마지막으로 추가된 ArrayList 확인
				System.out.println("등록한 이름은 " + person.getLast().getName());
				System.out.println("등록한 전화번호는 " + person.getLast().getPhone());
				System.out.println("등록한 날짜는 " + person.getLast().getDate());
				System.out.println("맞으시면 10을 입력해주세요");
				try {
					selec = scanner.nextInt();
				} catch (Exception e) {
					person.removeLast();
					System.out.println("전화번호는 숫자로 입력해주세요");
					scanner.nextLine();
					continue;
				}
				// 맞으면 그대로 진행, 틀리면 마지막으로 입력받았던 인스턴스는 지움
				if (selec != 10) {
					person.removeLast();
					System.out.println("처음으로 돌아갑니다.");
				} else {
					System.out.println("등록되었습니다");
				}
				break;

			// 2입력
			case 2:
				// 간단한 ArrayList 전체 불러내기
				System.out.println("====================현재 등록된 전화번호부====================");
				for (int i = 0; i < person.size(); i++) {
					System.out.println((i + 1) + ". 이름: " + person.get(i).getName() + "  전화번호: "
							+ person.get(i).getPhone() + "  등록일: " + person.get(i).getDate());
				}
				break;

			// 3입력
			case 3:
				// Person 클래스에 대해 person( ArrayList<Person> )을 순차적으로 찾는 Iterator 인스턴스 생성
				Iterator<Hcb0402_Person> searchIterator = person.iterator();
				// 컨트롤러
				Boolean found = false;

				System.out.println("===============전화번호를 알고 싶은 사람의 이름을 입력하세요===============");
				// 일단 찾는 이름을 입력받을 변수
				String searchInput = scanner.next();

				// 이 iterator 인스턴스가 다음 반복을 할 수 있다면 다음 반복의 요소 = Person 인스턴스 를 Person 클래스의
				// searchPerson 에 할당하여 사용한다.
				// 그냥 searchIterator.next()를 사용하면 오류가 나는데 왜인지 모르겠다
				while (searchIterator.hasNext()) {
					// 그렇게 할당받은 searchPerson의 getter로 받은 이름이 입력받은 값과 같다면 (string이므로 == 사용 불가, equals
					// 사용)
					Hcb0402_Person searchPerson = searchIterator.next();
					if (searchPerson.getName().equals(searchInput)) {
						System.out.println(searchInput + " 님의 전화번호는 " + searchPerson.getPhone() + " 입니다. "
								+ searchPerson.getDate() + " 에 등록되었습니다");
						found = true;
					}
					// 찾았다면 굳이 더 볼 필요없이 break할수도 있지만 동명이인의 가능성도 있으니
				}
				// 못찾았다면 여기서 알림
				if (!found)
					System.out.println(searchInput + " 님의 전화번호는 등록되지 않았습니다");
				break;

			// 4입력
			case 4:
				Iterator<Hcb0402_Person> deleteIterator = person.iterator();
				Boolean deleted = false;
				System.out.println("===============연락처를 지우고 싶은 사람의 이름을 입력하세요===============");
				String deleteInput = scanner.next();

				// 3번과 같다, 만약 iterator가 다음이 있다면 그것을 변수에 할당하고 그것을 입력한 값과 비교한다.
				while (deleteIterator.hasNext()) {
					Hcb0402_Person searchPerson = deleteIterator.next();
					if (searchPerson.getName().equals(deleteInput)) {
						// 주의 deleteIterator.next()를 사용하기에 deleteIterator이 하나 전이 아닌가 헷갈리지만 이것의 remove()는
						// 마지막으로 리턴한 즉 마지막으로 next()에 사용된 요소를 제거한다
						// deleteIterator은 사실 특정한 무언가를 가르키지 않는다 method들이 다음번의 무언가, 마지막의 무언가 이므로
						// deleteIterator이 현재 어디있는지는 중요하지 않다
						deleteIterator.remove();
						System.out.println(deleteInput + " 님의 연락처가 삭제되었습니다");
						System.out.println("출력에서 확인해 보세요");
						deleted = true;
					}
				}
				if (!deleted)
					System.out.println("찾으시는 연락처는 없습니다");
				break;

			case 5:
				Random random = new Random();
				// 등록된 전화번호가 없으면 person.size()가 0이므로 exception발생 try catch로 대응
				try {
					int rNum = random.nextInt(person.size());
					System.out.print(person.get(rNum).getName() + " 님의 전화번호는 ");
					System.out.print(person.get(rNum).getPhone() + " 번 이고 ");
					System.out.println(person.get(rNum).getDate() + " 에 등록되었습니다");
				} catch (Exception e) {
					System.out.println("등록된 전화번호가 없습니다");
				}
				break;
			}// 스위치문 끝
		}
		// 끝내기 전에 스캐너 클로즈 잊지말기
		scanner.close();
	}

}
