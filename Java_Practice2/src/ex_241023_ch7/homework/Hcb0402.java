package ex_241023_ch7.homework;

import java.time.LocalDate;
import java.util.*;

public class Hcb0402 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// Person클래스로 ArrayList
		ArrayList<Hcb0402_Person> person = new ArrayList<Hcb0402_Person>();
		// while문에서 숫자를 입력받아 네비게이터로 사용할것
		// 이 변수로 입력받은 수에 맞춰 진행
		int selec = 0;
		// 입력받는 경우 매번 scanner.input()으로 들어온걸 할당하기 때문에 돌려써도 괜찮을것이다
		String nameInput;
		int phoneInput;
		
		// 3,4번에서 Iterator.next() 리턴받을 Hcb0402_Person 클래스의 인스턴스를 할당할 변수 선언
		// 어차피 while내에 쓰기에 지역변수이므로 똑같이 선언해서 재사용해도 되지만..
		Hcb0402_Person searchPerson;
		// 컨트롤러
		Boolean found = false;

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
				//이름, 전화번호를 입력받고 이때 일시를 기록
				System.out.println("====================연락처 등록====================");
				System.out.println("이름을 입력하세요");
				nameInput = scanner.next();
				System.out.println("전화번호를 입력하세요");
				try {
					phoneInput = scanner.nextInt();
				} catch (Exception e) {
					System.out.println("전화번호는 숫자로 입력해주세요");
					// 위의 try에서 엔터를 누르는게 scanner버퍼에 남아서 while문의 초반 scanner에 줄바꿈이 들어가는것으로 보임
					scanner.nextLine();
					continue;
				}
				LocalDate date = LocalDate.now();
				
				/* 
				   여기서 LocalDate가 아니라 LocalDateTime으로 받아도 되고 
				   DateTimeFormatter formatted = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss"); 포맷을 정의하고
				   String formattedDate = date.format(formatted); 포맷을 사용하는것을 변수에 할당하여
				   formattedDate를 사용하여 구현해도 된다
				   이때 import java.time.format.DateTimeFormatter;를 잊지말것
				*/
				
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
				System.out.println("====================현재 등록된 연락처====================");
				for (int i = 0; i < person.size(); i++) {
					System.out.println((i + 1) + ". 이름: " + person.get(i).getName() + "  전화번호: "
							+ person.get(i).getPhone() + "  등록일: " + person.get(i).getDate());
				}
				break;

			// 3입력
			case 3:
				//컨트롤러 리셋
				found = false;
				System.out.println("===============연락처를 알고 싶은 사람의 이름을 입력하세요===============");
				// 일단 찾는 이름을 입력받을 변수 굳이 새로 할당할 필요 없이 1에서 썼던 nameInput을 그대로 써도 됨
				// 왜냐하면 매번 쓸때마다 scanner.next()로 새로 할당하니까
				nameInput = scanner.next();

				// 이 iterator 인스턴스가 다음 반복을 할 수 있다면 다음 반복의 요소 = Person 인스턴스 를 Person 클래스의
				// searchPerson 에 할당하여 사용한다.
				// 그냥 searchIterator.next()를 사용하면 오류가 나는데 왜인지 모르겠다
				// ====> .next()는 method라는걸 잊지말자 case3에선 if 조건문에 한번, if 내에서 2번 총 3번을 사용한다.
				// ====> .next()는 다음 요소를 리턴이라는 method이므로 한번의 반복에 3번 다음 요소를 리턴하게 된다
				// ====> 원하는대로 작동하지 않을뿐더러 마지막 직전의 경우 마지막, 그다음, 그다다음을 리턴하라는 요구인데
				// ====> 그 다음, 다다음은 존재하지 않기에 오류가 발생한다.
				
				// person 인스턴스에 대해 Person( ArrayList<Person> )을 순차적으로 찾는 Iterator 인스턴스 생성
				// searchIterator 은 3,4에 쓰이기에 전역으로 사용하고싶어서 밖으로 빼니 오류가 난다
				// 아마 여기에 있을 땐 case1과 블럭으로 분리되어있지만 밖으로 나가면 person이 변경될 가능성이 있기에 concurrent exception이 일어나는 것으로 보임
				Iterator<Hcb0402_Person> searchIterator = person.iterator();
				while (searchIterator.hasNext()) {
					searchPerson = searchIterator.next();
					// 그렇게 할당받은 searchPerson의 getter로 받은 이름이 입력받은 값과 같다면 (string이므로 == 사용 불가, equals 사용)
					if (searchPerson.getName().equals(nameInput)) {
						System.out.println(nameInput + " 님의 전화번호는 " + searchPerson.getPhone() + " 입니다. "
								+ searchPerson.getDate() + " 에 등록되었습니다");
						found = true;
					}
					// 찾았다면 굳이 더 볼 필요없이 break할수도 있지만 동명이인의 가능성도 있으니
				}
				// 못찾았다면 여기서 알림
				if (!found)
					System.out.println(nameInput + " 님의 연락처는 등록되지 않았습니다");
				break;

			// 4입력
			case 4:
				//컨트롤러 리셋
				found = false;
				System.out.println("===============연락처를 지우고 싶은 사람의 이름을 입력하세요===============");
				nameInput = scanner.next();

				// 3번과 같다, 만약 iterator가 다음이 있다면 그것을 변수에 할당하고 그것을 입력한 값과 비교한다.
				Iterator<Hcb0402_Person> deleteIterator = person.iterator();
				while (deleteIterator.hasNext()) {
					searchPerson = deleteIterator.next();
					if (searchPerson.getName().equals(nameInput)) {
						// 주의 Iterator.next()를 사용하기에 Iterator이 하나 전이 아닌가 헷갈리지만
						// 이것의 remove()는 마지막으로 리턴한 즉 마지막으로 next()로 호출된 요소를 제거한다
						// Iterator은 사실 특정한 무언가를 가르키지 않는다 컬렉션을 순차적으로 접근할 수 있는 하나의 방식으로 간주하면 쉽다
						// 따라서 Iterator이 현재 어디있는지는 중요하지 않다 hasNext(), next(), remove()의 method를 이해하는것이 중요하다
						// 쉽게 이해하자면 hasNext()는 다음이 있으면 true, 즉 마지막까지란 뜻으로 while과 같이 써서 전체를 뒤진다
						// next()는 다음 요소를 리턴인데 마지막요소까지 뒤졌을 경우 hasNext()가 false기에 리턴하지 않는다.
						// 따라서 while문 안에 써서 전체를 처음부터 하나하나 받아온다고 이해할 수 있다
						// remove()는 마지막으로 next()로 가져온것을 지운다 단순화시키면 while문 내에서 현재 작업하고있는 요소를 제거한다고 이해할 수 있다
						deleteIterator.remove();
						System.out.println(nameInput + " 님의 연락처가 삭제되었습니다");
						System.out.println("출력에서 확인해 보세요");
						found = true;
					}
				}
				if (!found)
					System.out.println(nameInput + " 님의 연락처는 등록되지 않았습니다");
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
			}// switch
		}// while
		// 끝내기 전에 스캐너 클로즈 잊지말기
		scanner.close();
	}// main

}// class
