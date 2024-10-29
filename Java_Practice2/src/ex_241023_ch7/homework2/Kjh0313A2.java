package ex_241023_ch7.homework2;

import java.time.LocalDate;
import java.util.*;

import ex_241023_ch7.homework2.Kjh0313A;

public class Kjh0313A2 {

	public static void main(String[] args) { // 이 파일을 메인으로
		Scanner scanner = new Scanner(System.in); // 스캐너 생성
		HashMap<String, Kjh0313A> personMap = new HashMap<>(); // ArrayList 대신 HashMap 사용
		int selec = 0; // while문을 시작하기 위한 selec 0 선언(while문이 시작할려면 0으로 시작해야 하기 때문)

		while (selec != 6) { // 반복 시작
			System.out.println("==================================================");
			System.out.println("1. 입력, 2. 출력 , 3. 검색 , 4. 삭제, 5. 랜덤 , 6. 종료");
			System.out.println("==================================================");// 메세지
			try {
				selec = scanner.nextInt();
			} catch (Exception e) {
				System.out.println("숫자만 입력해주세요");
				scanner.nextLine();
				continue; // 숫자 입력 받기
			}

			switch (selec) {
			case 1:// 1을 입력 받을시 아래 코드 실행
				String nameInput;
				int phoneInput;
				System.out.println("====================전화번호 등록====================");
				System.out.println("이름을 입력하세요");
				nameInput = scanner.next(); // 네임인풋에 받을 문자 스캔
				System.out.println("전화번호를 입력하세요");
				try {
					phoneInput = scanner.nextInt(); // 폰인풋에 받을 문자 스캔
				} catch (Exception e) {
					scanner.nextLine();
					continue;
				}
				LocalDate date = LocalDate.now();

				personMap.put(nameInput, new Kjh0313A(nameInput, phoneInput, date)); // HashMap에 값 추가
				System.out.println("등록한 이름 : " + nameInput);
				System.out.println("등록한 전화번호 : " + phoneInput);
				System.out.println("등록한 날짜 : " + date);
				break; // while문 최초로 귀환

			case 2:
				System.out.println("====================현재 등록된 전화번호====================");
				for (Map.Entry<String, Kjh0313A> entry : personMap.entrySet()) {
					Kjh0313A person = entry.getValue(); // HasMap에 저장되어 있는 값들 호출
					System.out.println(
							"이름: " + person.getName() + "  전화번호: " + person.getPhone() + "  등록일: " + person.getDate());// 콘솔창에
																														// 출력
				}
				break;

			case 3:
				System.out.println("===============검색할 이름 입력===============");
				String searchInput = scanner.next();// HasMap에 입력된 정보 검색
				Kjh0313A searchPerson = personMap.get(searchInput);// 입력된 이름과 일치하는 정보가 있다면 그 정보를 searchPerson변수에 저장

				if (searchPerson != null) {// 성공적으로 저장되면 아래코드 실행
					System.out.println(searchInput + " 님의 전화번호는 " + searchPerson.getPhone() + " 입니다. "
							+ searchPerson.getDate() + " 에 등록되었습니다.");
				} else {// 일치 하지 않는다면 아래코드 실행
					System.out.println(searchInput + " 님의 전화번호는 등록되지 않았습니다.");
				}
				break;// while문 최초로 귀환

			case 4:
				System.out.println("===============삭제할 이름 입력===============");
				String deleteInput = scanner.next();// HasMap에 입력된 정보 검색

				if (personMap.remove(deleteInput) != null) {// 입력된 정보와 일치하는 정보를 삭제
					System.out.println(deleteInput + " 님의 연락처가 삭제되었습니다.");
				} else {// 입력된 정보와 일치하는 정보가 없을시 아래코드 실행
					System.out.println("찾으시는 연락처는 없습니다.");
				}
				break;

			case 5:
				if (!personMap.isEmpty()) {
					Random random = new Random();
					// HashMap의 크기 내에서 랜덤한 인덱스 생성
					int randomIndex = random.nextInt(personMap.size());
					// HashMap의 키셋을 순회하며 랜덤 인덱스에 해당하는 키 선택
					String randomKey = null;
					int currentIndex = 0;
					for (String key : personMap.keySet()) {
						if (currentIndex == randomIndex) {
							randomKey = key;
							break;
						}
						currentIndex++;
					}
					// 선택된 랜덤 키를 이용해 값 가져오기
					if (randomKey != null) {
						Kjh0313A randomPerson = personMap.get(randomKey);
						System.out.print(randomPerson.getName() + " 님의 전화번호는 ");
						System.out.print(randomPerson.getPhone() + " 번 이고 ");
						System.out.println(randomPerson.getDate() + " 에 등록되었습니다.");
					}
				} else {
					System.out.println("등록된 전화번호가 없습니다.");
				}
				break;
			}
		}
		scanner.close();
	}
}
