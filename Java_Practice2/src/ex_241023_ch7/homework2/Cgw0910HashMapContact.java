package ex_241023_ch7.homework2;
import java.util.Scanner;
import java.util.Random;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Cgw0910HashMapContact {

	public static void main(String[] args) {
		Random random = new Random();
		Scanner scanner = new Scanner(System.in);
		HashMap<String, Cgw0910ContactClass>userData = new HashMap<String, Cgw0910ContactClass>();
		outerLoop: while (true) {
			System.out.println("입력값 대기중 1.입력, 2.출력, 3.검색, 4.삭제, 5.랜덤, 6.종료임");

			if (!scanner.hasNextInt()) {
				System.out.println("유효한 숫자를 입력해주세요.");
				scanner.next(); // 잘못된 입력 건너뛰기
				continue;
			}

			int input = scanner.nextInt();
			scanner.nextLine(); // 개행 문자 소모

			if (0 < input && input < 7) {
				switch (input) {
				case 1: {
					System.out.println("연락처 데이터 저장");
					System.out.println("유저 이름을 입력해주세요.");
					System.out.print("입력: ");
					String userName = scanner.nextLine();
					System.out.println("유저 연락처를 입력해주세요 예(01012341234)");
					System.out.print("입력: ");
					String userPhoneNum = scanner.nextLine();
					Cgw0910ContactClass person = new Cgw0910ContactClass();
					person.setPerson(userName, userPhoneNum);
					userData.put(userName, person);
					System.out.println("연락처가 저장되었습니다.");
					break;
				}
				case 2: {
					System.out.println("연락처 데이터 호출");
					if (!userData.isEmpty()) {
						userData.forEach((key, value) -> {
							System.out.println("유저 이름: " + key);
							value.showInfo();
						});
					}
					else {
						System.out.println("연락처 내 데이터가 비어있습니다");
					}
					break;
				}
				case 3: {
					System.out.println("연락처 데이터 검색");
					System.out.println("유저 이름 입력 시 데이터 내 정보를 호출합니다");
					System.out.print("입력: ");
					String userName = scanner.nextLine();
					if (userData.containsKey(userName)) {
						System.out.println("유저 이름: " + userName);
						userData.get(userName).showInfo();
					}
					else {
						System.out.println("데이터 내 " + userName + "(이)가 없습니다.");
					}
					break;
				}
				case 4: {
					System.out.println("연락처 데이터 삭제");
					System.out.println("유저 이름 입력 시 데이터 내 정보를 삭제합니다");
					System.out.print("입력: ");
					String userName = scanner.nextLine();
					if (userData.containsKey(userName)) {
						userData.remove(userName);
						System.out.println("데이터 내 " + userName + "(을)를 삭제하였습니다.");
					}
					else {
						System.out.println("데이터 내 " + userName + "(이)가 없습니다.");
					}
					break;
				}
				case 5: {
					System.out.println("연락처 데이터를 랜덤하게 호출합니다.");
					if (!userData.isEmpty()) {
						List<String> keyList = userData.keySet().stream().collect(Collectors.toList());
						String randomPerson = keyList.get(random.nextInt(keyList.size()));
						System.out.println(randomPerson);
						userData.get(randomPerson).showInfo();
					} else {
						System.out.println("데이터가 비어있습니다.");
					}
					break;
				}
				case 6: {
					System.out.println("프로그램을 종료합니다.");
					break outerLoop;
				}
				default: {
					System.out.println("유효하지 않은 입력입니다.");
					break;
				}
				} // switch 끝
			} else {
				System.out.println("1부터 6까지의 숫자만 입력해주세요.");
			}
		} // while 끝
		scanner.close();
	} // main 끝
} // class 끝


