package ex_241023_ch7.homework2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class SoojongHashMapMain {

    static HashMap<String, SoojongHashMap> SoojongMembers = new HashMap<String, SoojongHashMap>();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            int menuNumber;
            System.out.println("===================================================\r\n"
                    + "연락처 프로그램 만들기.\r\n"
                    + "1. 입력, 2. 출력 , 3. 검색 , 4. 삭제, 5. 랜덤 , 6. 종료\r\n"
                    + "===================================================");
            System.out.print("원하시는 메뉴를 입력해주세요: ");
            menuNumber = scan.nextInt();
            if (menuNumber == 6) {
                System.out.println("종료되었습니다.");
                break;
            }

            menu(menuNumber);
        }
    }

    public static void menu(int menuNumber) {
        if (menuNumber == 1) {
            System.out.println("입력을 선택했습니다.");
            // 입력 부분
            System.out.print("이름을 입력하세요: ");
            String name = scan.next();

            System.out.print("전화번호를 입력하세요: ");
            String phone = scan.next();

            // 현재 날짜와 시간 추가
            Date date = new Date();

            // SoojongHashMap 객체 생성
            SoojongHashMap contact = new SoojongHashMap();
            contact.setName(name);
            contact.setNumber(phone);
            contact.setDate(date);

            SoojongMembers.put(name, contact); // 이름을 키로 저장

            System.out.println("연락처가 저장되었습니다.");

        } else if (menuNumber == 2) {
            System.out.println("출력을 선택했습니다.");
            // 등록된 연락처 출력
            if (SoojongMembers.isEmpty()) {
                System.out.println("등록된 연락처가 없습니다.");
            } else {
                Set<String> keys = SoojongMembers.keySet();
                Iterator<String> it = keys.iterator();
                while (it.hasNext()) {
                    String key = it.next();
                    SoojongHashMap contact = SoojongMembers.get(key);
                    // 출력 형식
                    String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(contact.getDate());
                    System.out.println("이름: " + contact.getName() + ", 전화번호: " + contact.getNumber() + ", 등록 일자: " + formattedDate);
                }
            }

        } else if (menuNumber == 3) {
            System.out.println("검색을 선택했습니다.");
            // 검색 기능
            System.out.print("검색할 이름을 입력하세요: ");
            String searchName = scan.next();
            if (SoojongMembers.containsKey(searchName)) {
                SoojongHashMap contact = SoojongMembers.get(searchName);
                String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(contact.getDate());
                System.out.println("이름: " + contact.getName() + ", 전화번호: " + contact.getNumber() + ", 등록 일자: " + formattedDate);
            } else {
                System.out.println("해당 이름의 연락처가 존재하지 않습니다.");
            }

        } else if (menuNumber == 4) {
            System.out.println("삭제를 선택했습니다.");
            // 삭제 기능
            System.out.print("삭제할 이름을 입력하세요: ");
            String deleteName = scan.next();
            if (SoojongMembers.containsKey(deleteName)) {
                SoojongMembers.remove(deleteName);
                System.out.println(deleteName + "의 연락처가 삭제되었습니다.");
            } else {
                System.out.println("해당 이름의 연락처가 존재하지 않습니다.");
            }

        } else if (menuNumber == 5) {
            System.out.println("랜덤을 선택했습니다.");
            // 랜덤 연락처 출력 기능
            if (SoojongMembers.isEmpty()) {
                System.out.println("등록된 연락처가 없습니다.");
            } else {
                Object[] keys = SoojongMembers.keySet().toArray();
                String randomKey = (String) keys[new Random().nextInt(keys.length)];
                SoojongHashMap randomContact = SoojongMembers.get(randomKey);
                String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(randomContact.getDate());
                System.out.println("랜덤 선택된 연락처 -> 이름: " + randomContact.getName() + ", 전화번호: " + randomContact.getNumber() + ", 등록 일자: " + formattedDate);
            }

        } else {
            System.out.println("잘못된 메뉴입니다. 다시 입력해주세요.");
        }
    }
}
