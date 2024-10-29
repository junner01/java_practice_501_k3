package ex_241023_ch7.homework2;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Hsw1009Main {
    private static HashMap<String, Contact> contacts = new HashMap<String, Contact>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("==============================================");
            System.out.println("1. 입력, 2. 출력, 3. 검색, 4. 삭제, 5. 랜덤, 6. 종료");
            System.out.println("==============================================");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    inputContact();
                    break;
                case 2:
                    outputContacts();
                    break;
                case 3:
                    searchContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    randomContact();
                    break;
                case 6:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("올바른 선택을 해주세요.");
            }
        }
    }

    // 연락처 입력
    private static void inputContact() {
        System.out.print("이름: ");
        String name = scanner.nextLine();
        if (contacts.containsKey(name)) {
        	System.out.println("이미 등록된 이름입니다. 다시 한 번 확인 바랍니다.");
        	return;
        }
        System.out.print("연락처: ");
        String contactNumber = scanner.nextLine();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);

        double secondsWithFraction = now.getSecond() + now.getNano() / 1_000_000_000.0;
        String registrationDate1 = formattedDate + String.format(".%.1f", secondsWithFraction % 1);
        

        // HashMap에 이름을 키로 하고 Contact 객체를 값으로 저장
        Contact contact = new Contact(name, contactNumber, registrationDate1);
        contacts.put(name, contact);

        System.out.println("연락처가 등록되었습니다.");
    }

    // 전체 연락처 출력
    private static void outputContacts() {
        if (contacts.isEmpty()) {
            System.out.println("등록된 연락처가 없습니다.");
            return;
        }
        // HashMap의 값들 출력
        for (Contact contact : contacts.values()) {
            System.out.println(contact);
        }
    }

    // 이름으로 연락처 검색
    private static void searchContact() {
        System.out.print("검색할 이름: ");
        String name = scanner.nextLine();
        Contact contact = contacts.get(name);
        
        if (contact != null) {
            System.out.println(contact);
        } else {
            System.out.println("해당 이름의 연락처를 찾을 수 없습니다.");
        }
    }

    // 이름으로 연락처 삭제
    private static void deleteContact() {
        System.out.print("삭제할 이름: ");
        String name = scanner.nextLine();

        if (contacts.remove(name) != null) {
            System.out.println(name + "의 연락처가 삭제되었습니다.");
        } else {
            System.out.println("해당 이름의 연락처를 찾을 수 없습니다.");
        }
    }

    // 랜덤으로 연락처 선택
    private static void randomContact() {
        if (contacts.isEmpty()) {
            System.out.println("등록된 연락처가 없습니다.");
            return;
        }
        Random rand = new Random();
        // keySet으로 키 목록을 가져온 후 랜덤 선택
        Object[] keys = contacts.keySet().toArray();
        String randomKey = (String) keys[rand.nextInt(keys.length)];
        System.out.println("랜덤으로 선택된 연락처: " + contacts.get(randomKey));
    }
}

class Contact {
    private String name;
    private String contactNumber;
    private String registrationDate;

    public Contact(String name, String contactNumber, String registrationDate) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.registrationDate = registrationDate;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "이름: " + name + ", 연락처: " + contactNumber + ", 등록일: " + registrationDate;
    }
}

