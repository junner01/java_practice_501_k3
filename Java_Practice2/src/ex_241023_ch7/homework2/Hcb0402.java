package ex_241023_ch7.homework2;

import java.time.LocalDate;
import java.util.*;

public class Hcb0402 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// Person클래스로 ArrayList
		HashMap<String, Hcb0402_Person> person = new HashMap<String, Hcb0402_Person>();
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
				String nickName;
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
				System.out.println("애칭을 입력하세요");
				nickName = scanner.next();
				LocalDate date = LocalDate.now();
				// 입력받은 정보를 인스턴스로 ArrayList에 추가
				person.put(nickName, new Hcb0402_Person(nameInput, phoneInput, date));
				// 마지막으로 추가된 ArrayList 확인
				System.out.println("등록한 이름은 " + person.get(nickName).getName());
				System.out.println("등록한 전화번호는 " + person.get(nickName).getPhone());
				System.out.println("등록한 날짜는 " + person.get(nickName).getDate());
				System.out.println("맞으시면 10을 입력해주세요");
				try {
					selec = scanner.nextInt();
				} catch (Exception e) {
					person.remove(nickName);
					System.out.println("전화번호는 숫자로 입력해주세요");
					scanner.nextLine();
					continue;
				}
				// 맞으면 그대로 진행, 틀리면 마지막으로 입력받았던 인스턴스는 지움
				if (selec != 10) {
					person.remove(nickName);
					System.out.println("처음으로 돌아갑니다.");
				} else {
					System.out.println("등록되었습니다");
				}
				break;

			// 2입력
			case 2:
				// 간단한 ArrayList 전체 불러내기
				// Set엔 순서가 보장되지 않는다 => 인덱스로 불러낼 수 없다
				// Hashmap은 iterator을 사용할 수 없다 => foreach문도 내부는 iterator을 사용하므로 불가 => set나 list로 변환 후 사용해야한다
				// 그럼 굳이 list로 변환하여 iterator을 사용할 필요 없이 list로 변환 후 인덱스로 불러내면된다.
				// 1) ket를 String형식의 List로 변환
				List<String> listPerson = new ArrayList<>(person.keySet()); 
				System.out.println("====================현재 등록된 전화번호부====================");
				// 2) foreach문을 이용해 List<String(key)>를 i에 할당
				for (String i : listPerson) {
					// 3) i에 key가 할당되므로 hashMap의 get로 value를 가져오고, value는 Person클래스이므로 getter를 이용해 정보를 가져온다
					System.out.print(i + " 님의 이름은:" + person.get(i).getName());
					System.out.print(" 전화번호는: " + person.get(i).getPhone());
					System.out.println(" 등록일은: " + person.get(i).getDate());
				}
				break;

			// 3입력
			case 3:
				// 컨트롤러
				Boolean found = false;

				System.out.println("===============전화번호를 알고 싶은 사람의 애칭을 입력하세요===============");
				// 일단 찾는 이름을 입력받을 변수
				String searchInput = scanner.next();

				if (person.containsKey(searchInput)) {
					System.out.println(searchInput + " 님의 전화번호는 " + person.get(searchInput).getPhone() + " 입니다. "
							+ person.get(searchInput).getDate() + " 에 등록되었습니다");
					found = true;
				}

				// 못찾았다면 여기서 알림
				if (!found)
					System.out.println(searchInput + " 님의 전화번호는 등록되지 않았습니다");
				break;

			// 4입력
			case 4:
				Boolean deleted = false;

				System.out.println("===============연락처를 지우고 싶은 사람의 애칭을 입력하세요===============");
				String deleteInput = scanner.next();

				if (person.containsKey(deleteInput)) {
					// 주의 deleteIterator.next()를 사용하기에 deleteIterator이 하나 전이 아닌가 헷갈리지만 이것의 remove()는
					// 마지막으로 리턴한 즉 마지막으로 next()에 사용된 요소를 제거한다
					// deleteIterator은 사실 특정한 무언가를 가르키지 않는다 method들이 다음번의 무언가, 마지막의 무언가 이므로
					// deleteIterator이 현재 어디있는지는 중요하지 않다
					person.remove(deleteInput);
					System.out.println(deleteInput + " 님의 연락처가 삭제되었습니다");
					System.out.println("출력에서 확인해 보세요");
					deleted = true;
				}

				if (!deleted)
					System.out.println("찾으시는 연락처는 없습니다");
				break;

			case 5:
				Random random = new Random();
				// 등록된 전화번호가 없으면 person.size()가 0이므로 exception발생 try catch로 대응
				// 1. key를 index 로 하고 random으로 나온 수를 get()에 넣어 가져오기
				// 2. key를 List로 만들고 random 으로 나온 수를 List에서 get() <<<< 2번 전체출력에서 사용
				// 3. stream API 사용, <<<< 1)먼저 hashMap의 key와 value를 entrySet()으로 key와 value를 가진 set로 만든 다음   
				//    2)stream()으로 stream을 생성하고 3)random으로 나온 수만큼 skip(), 4)findFrist()로 값 리턴, 5)orElse()로 값이 없을때를 대비 <<선택
				
				//1) hashMap의 key 와 value를 Set형태로 만듬, 이상태에서 닫아버리면 제일 앞이 Set<Map.Entry<K,V>>의 형태로 받아야 함
				Map.Entry<String, Hcb0402_Person> entryPerson = person.entrySet()
				//2) 1)에서 만든 Set형태를 stream으로 변환
				.stream()
				//3) 2)에서 변환된 stream중 앞에서 랜덤한 수만큼 skip (random.nextInt로 랜덤한 수 생성, person.size로 0~hashMap의 크기 중에서 랜덤한 수 나오게 지정) 
				.skip(random.nextInt(person.size()))
				//4) 3)의 결과로 위치지정이 랜덤한 곳에 도달한 그곳의 값을 반환받음 이것은 Set의 형태가 아니라 Set 안에 있는 Map.Entry<K,V>의 형태를 받음
				.findFirst()
				//5) 반환받을 값이 없다면 Null을 반환함
				.orElse(null);
				
				//Map.Entry안의 Key와 Value를 받아 출력하면 랜덤출력 완료
				System.out.println("애칭: " + entryPerson.getKey());
				//단 Map.Entry의 Value는 Hcb0402_Person형태로 나오므로 한번 더 getter를 쓸 필요가 있음
				System.out.println("이름: " + entryPerson.getValue().getName());
				System.out.println("전화번호: " + entryPerson.getValue().getPhone());
				System.out.println("등록일: " + entryPerson.getValue().getDate());			
				
				break;
			}// 스위치문 끝
		}
		// 끝내기 전에 스캐너 클로즈 잊지말기
		scanner.close();
	}

}
