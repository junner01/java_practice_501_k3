package ex_241023_ch7.homework2;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Lsh0708Main {

    public static void main(String[] args) {
        HashMap<String, Lsh0708Person> contactMap = new HashMap<String, Lsh0708Person>();

        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("===============================================");
            System.out.println("연락처 프로그램 만들기, HashMap 버전");
            System.out.println("1.입력, 2.출력, 3.검색, 4.삭제, 5.랜덤, 6.종료");
            System.out.println("===============================================");
            String num = scanner.next();
            switch (num) {
                case "1":
                    System.out.print("이름을 입력하세요 : ");
                    String name = scanner.next();
                    System.out.print("번호를 입력하세요 : ");
                    String number = scanner.next();
                    Lsh0708Person person = new Lsh0708Person(name, number);
                    contactMap.put(name, person);
                    break;
                case "2":
                    for (Lsh0708Person p : contactMap.values()) {
                        p.showInfo();
                    }
                    break;
                case "3":
                    System.out.print("검색할 사용자를 입력하세요 : ");
                    String searchUser = scanner.next();
                    Lsh0708Person foundPerson = contactMap.get(searchUser);
                    if (foundPerson != null) {
                        foundPerson.showInfo();
                    } else {
                        System.out.println("사용자를 찾을 수 없습니다.");
                    }
                    break;
                case "4":
                    System.out.print("삭제할 사용자를 입력하세요 : ");
                    String removeUser = scanner.next();
                    if (contactMap.remove(removeUser) != null) {
                        System.out.println(removeUser + " 사용자를 삭제했습니다.");
                    } else {
                        System.out.println("존재하지 않는 사용자입니다. 사용사 삭제에 실패했습니다!");
                    }
                    break;
                case "5":
                    Random random = new Random();
                    if (!contactMap.isEmpty()) {
                        int randomIndex = random.nextInt(contactMap.size());
                        String randomKey = (String) contactMap.keySet().toArray()[randomIndex];
                        System.out.println("추천 사용자!");
                        contactMap.get(randomKey).showInfo();
                    } else {
                        System.out.println("등록된 사용자가 없습니다.");
                    }
                    break;
                case "6":
                    System.out.println("프로그램을 종료합니다!!!!!!");
                    run = false;
                    break;
                default:
                    System.out.println("메뉴를 다시 선택하세요!");
            }
        }
        scanner.close();
    }
}
