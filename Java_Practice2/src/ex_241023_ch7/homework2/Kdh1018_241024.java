package ex_241023_ch7.homework2;

import java.text.SimpleDateFormat; // 날짜 형식 지정
import java.util.Date; // 날짜 객체
import java.util.HashMap; // 해시맵 사용
import java.util.Random; // 랜덤 객체
import java.util.Scanner; // 사용자 입력을 위한 스캐너

//Java에서 import는 다른 패키지에 정의된 클래스를 사용할 수 있도록 가져오는 구문. 기본적으로, Java는 클래스의 이름이 고유해야 하므로, 
//서로 다른 패키지에 같은 이름의 클래스가 있을 수 있습니다. import를 사용하면 이러한 클래스들을 구분하고 쉽게 사용할 수 있습니다. 코드 가독성 향상: import를 사용하면 코드가 더 간결해지고 가독성이 높아집니다.
//네임스페이스 관리: 서로 다른 패키지에 같은 이름의 클래스가 있을 경우, import를 통해 필요한 클래스를 명확하게 구분할 수 있습니다.

//  클래스 정의한다. 여기서 사람이름 PersonKdh961018로 정의
class PersonKdh961018 {
	private String name; // private로 받고, 문자열 이름
	private String contact; // private로 받고, 문자열 연락처 -> 번호는 정수인데 왜 문자열로 받을까?
	private String registrationDate; // private로 받고, 문자열 등록날짜.
	// 등록하고, 출력되어야 하는 세가지 조건 -> 이렇게 클래스 정의하고 시작해야한다.
	// class xxx (정의 되어야 하는 이름)
	// 그 다음 밑에 public private protected 등등 필요한거 써서 문자열로 써야하는거 쓴 다음 세미콜론으로

	// 생성자: 이름과 연락처를 입력받아 객체를 생성 // 문자열 이름과 문자열 연락처 받는 객체 생성
	public PersonKdh961018(String name, String contact) {
		this.name = name; // 입력받은 이름 설정 this 는 나 자신을 객체 그자체를 의미한다고 배운 것 같다.
		this.contact = contact; // 입력받은 연락처 설정
		// 현재 날짜와 시간을 yyyy-MM-dd HH:mm:ss 형식으로 설정
		this.registrationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	// getter setter 이거인가? 아마도 그건거 같다- > 우클릭 -> source -> getter setter로 해서 이름부여 후
	// 리턴(문자열로 받음)
	public String getName() {
		return name;
	}

	// 연락처 반환
	public String getContact() {
		return contact;
	}

	// 등록 날짜 반환
	public String getRegistrationDate() {
		return registrationDate;
	}

	// Person 객체의 정보를 문자열로 반환
	@Override // 상속 관계에 있는 클래스에서 부모 클래스의 메서드를 자식 클래스에서 재정의하는 과정을 의미합니다.
	public String toString() {
		return "이름: " + name + ", 연락처: " + contact + ", 등록 날짜: " + registrationDate;
	} // 퍼블릭= 모든접근 가능하고 문자열로 받는다 그리고 toString =>문자열 표현을 반환
		// 그러니까 리턴값을 내면 이름: ㅇㅇㅇ 연락처 ㅇㅇㅇ 등록날짜 ㅇㅇㅇ 가 나오는것
}

// 연락처 프로그램 클래스 정의
	public class Kdh1018_241024 {	
	// HashMap을 사용하여 이름을 키로 하고 위에 클래스를 객체를 값으로 저장한 다음 해쉬맵 이용
	private HashMap<String, PersonKdh961018> contacts = new HashMap<>();
	private Scanner scanner = new Scanner(System.in); // 사용자 입력을 위한 스캐너

	// 프로그램 실행 메서드
	public void run() //run 실행
		{
		while (true) { // while은 무한으로 진행되게 하는거임. 무겐츠쿠요미 느낌 뭔지알지
			
			
			// 사용자 메뉴 출력
			System.out.println("1. 입력, 2. 출력, 3. 검색, 4. 삭제, 5. 랜덤, 6. 종료");
			int choice = scanner.nextInt(); // 사용자 입력 받기 // int라는 정수형 변수에 choice 를 지정하고 = 스캐너엔 다음정수형으로 나오게끔 만드는거
			scanner.nextLine(); // 다음 줄 소모

			// 사용자 선택에 따라 적절한것 호출. 그러니까 선택지가 6번까지 있으니 case1 ~ case6까지 만든 다음에 실행 
			switch (choice) {
			case 1: // 연락처 추가
				addContact();
				break;
			case 2: // 연락처 출력
				displayContacts();
				break;
			case 3: // 연락처 검색
				searchContact();
				break;
			case 4: // 연락처 삭제
				deleteContact();
				break;
			case 5: // 랜덤으로 연락처 선택
				randomContact();
				break;
			case 6: // 프로그램 종료
				System.out.println("프로그램을 종료합니다."); //종료하는거니까 system.out.println 으로 출력하는 말을 입력 해줘야함.
				return; // run 메서드 종료
			default: // 잘못된 선택 처리
				System.out.println("잘못된 선택입니다. 다시 시도하세요.");
			}
		}
	}

	// 연락처 추가 메서드
	private void addContact() {
		System.out.print("이름을 입력하세요: "); // 이름 입력 요청
		String name = scanner.nextLine(); // 이름 입력 받기
		System.out.print("연락처를 입력하세요: "); // 연락처 입력 요청
		String contact = scanner.nextLine(); // 연락처 입력 받기
		// HashMap에 이름을 키로, Person 객체를 값으로 추가
		contacts.put(name, new PersonKdh961018(name, contact));
		System.out.println("연락처가 등록되었습니다."); // 등록 완료 메시지
	}

	// 모든 연락처 출력 메서드
	private void displayContacts() {
		if (contacts.isEmpty()) { // 연락처가 없으면
			System.out.println("등록된 연락처가 없습니다."); // 메시지 출력
			return; // 메서드 종료
		}
		// 모든 Person 객체를 출력
		for (PersonKdh961018 person : contacts.values()) {
			System.out.println(person);
		}
	}

	// 연락처 검색 메서드
	private void searchContact() {
		System.out.print("검색할 이름을 입력하세요: "); // 이름 입력 요청
		String name = scanner.nextLine(); // 이름 입력 받기
		// HashMap에서 이름을 키로 Person 객체 가져오기
		PersonKdh961018 person = contacts.get(name);
		if (person != null) { // 해당 이름의 연락처가 존재하면
			System.out.println(person); // 연락처 출력
		} else { // 존재하지 않으면
			System.out.println("해당 이름의 연락처가 없습니다."); // 메시지 출력
		}
	}

	// 연락처 삭제 메서드
	private void deleteContact() {
		System.out.print("삭제할 이름을 입력하세요: "); // 이름 입력 요청
		String name = scanner.nextLine(); // 이름 입력 받기
		// 해당 이름의 연락처 삭제
		PersonKdh961018 removedPerson = contacts.remove(name);
		if (removedPerson != null) { // 삭제가 성공하면
			System.out.println(name + "의 연락처가 삭제되었습니다."); // 삭제 메시지
		} else { // 삭제가 실패하면
			System.out.println("해당 이름의 연락처가 없습니다."); // 메시지 출력
		}
	}

	// 랜덤 연락처 선택 메서드
	private void randomContact() {
		if (contacts.isEmpty()) { // 연락처가 없으면
			System.out.println("등록된 연락처가 없습니다."); // 메시지 출력
			return; // 메서드 종료
		}
		Random random = new Random(); // 랜덤 객체 생성
		Object[] values = contacts.values().toArray(); // HashMap의 값들을 배열로 변환
		// 랜덤 인덱스를 선택하여 연락처 출력
		PersonKdh961018 randomPerson = (PersonKdh961018) values[random.nextInt(values.length)];
		System.out.println("랜덤 선택된 연락처: " + randomPerson);
	}

	// 프로그램의 진입점
	public static void main(String[] args) {
		new Kdh1018_241024().run(); // ContactProgram 객체 생성 및 실행
	}
}
	
	// 일단 파일을 나눠서 override로 인해 불러오는게 코딩의 가독성이 높지만 지금은 어떻게 할 지 잘 모르니까 한번에 하는걸로.
