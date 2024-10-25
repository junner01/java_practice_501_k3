package ex_241023_ch7.homework;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class JHJ_Arr {

	public static void main(String[] args) {
        ArrayList<JHJ_Person> ArrList = new ArrayList<>();
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
                    
                    ArrList.add(new JHJ_Person(name, number));
                    System.out.println("연락처가 등록되었습니다.");
                    break;
                    
                case 2: 
                    if (ArrList.isEmpty()) { //isEmpty 비어있으
                        System.out.println("등록된 연락처가 없습니다.");
                    } else {
                        System.out.println("등록된 연락처:");
                        for (int i = 0; i < ArrList.size(); i++) {
                            JHJ_Person person = ArrList.get(i);
                            System.out.println("이름: " + person.getName());
                            System.out.println("연락처: " + person.getNumber());
                            System.out.println("등록 날짜: " + person.getAddDay());
                            System.out.println("---------------------------");
                        }
                    }
                    break;

                case 3:
                    System.out.print("검색할 이름 입력: ");
                    String search = scanner.nextLine();
                    
                    boolean found = false;
                    for (JHJ_Person person : ArrList) {
                        if (person.getName().contains(search)) {
                        	System.out.println("이름: " + person.getName());
                            System.out.println("연락처: " + person.getNumber());
                            System.out.println("등록 날짜: " + person.getAddDay());
                            System.out.println("---------------------------");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("해당 이름의 연락처가 없습니다.");
                    }
                    break;

                case 4: 
                    System.out.print("삭제할 이름 입력: ");
                    String deleteName = scanner.nextLine();
                    boolean removed = ArrList.removeIf(person -> person.getName().equalsIgnoreCase(deleteName));
                    if (removed) {
                        System.out.println("연락처가 삭제되었습니다.");
                    } else {
                        System.out.println("해당 이름의 연락처가 없습니다.");
                    }
                    break;

                case 5: // 랜덤
                    if (!ArrList.isEmpty()) {
                        int randomIndex = random.nextInt(ArrList.size());
                        System.out.println("랜덤 선택된 연락처:");
                        ArrList.get(randomIndex).displayPersonInfo();
                    } else {
                        System.out.println("등록된 연락처가 없습니다.");
                    }
                    break;

                case 6: // 종료
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    return;

                default:
                    System.out.println("올바른 번호를 입력하세요.");
            }
        }
    }

}
