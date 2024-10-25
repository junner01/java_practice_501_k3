package ex_241023_ch7.homework;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

class PersonKdh1018 {
    private String name;
    private String contact;
    private String registrationDate;

    public PersonKdh1018(String name, String contact) {
        this.name = name;
        this.contact = contact;
        this.registrationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public String toString() {
        return "이름: " + name + ", 연락처: " + contact + ", 등록 날짜: " + registrationDate;
    }
}

public class Kdh1018Homework {
    private ArrayList<PersonKdh1018> contacts = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            System.out.println("1. 입력, 2. 출력, 3. 삭제, 4. 랜덤, 5. 검색, 6. 종료");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    displayContacts();
                    break;
                case 3:
                    deleteContact();
                    break;
                case 4:
                    randomContact();
                    break;
                case 5:
                    searchContact();
                    break;
                case 6:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도하세요.");
            }
        }
    }

    private void addContact() {
        System.out.print("이름을 입력하세요: ");
        String name = scanner.nextLine();
        System.out.print("연락처를 입력하세요: ");
        String contact = scanner.nextLine();
        contacts.add(new PersonKdh1018(name, contact));
        System.out.println("연락처가 등록되었습니다.");
    }

    private void displayContacts() {
        if (contacts.isEmpty()) {
            System.out.println("등록된 연락처가 없습니다.");
            return;
        }
        for (PersonKdh1018 person : contacts) {
            System.out.println(person);
        }
    }

    private void deleteContact() {
        System.out.print("삭제할 이름을 입력하세요: ");
        String name = scanner.nextLine();
        boolean removed = contacts.removeIf(person -> person.getName().equalsIgnoreCase(name));
        if (removed) {
            System.out.println(name + "의 연락처가 삭제되었습니다.");
        } else {
            System.out.println("해당 이름의 연락처가 없습니다.");
        }
    }

    private void randomContact() {
        if (contacts.isEmpty()) {
            System.out.println("등록된 연락처가 없습니다.");
            return;
        }
        Random random = new Random();
        PersonKdh1018 randomPerson = contacts.get(random.nextInt(contacts.size()));
        System.out.println("랜덤 선택된 연락처: " + randomPerson);
    }

    private void searchContact() {
        System.out.print("검색할 이름을 입력하세요: ");
        String name = scanner.nextLine();
        boolean found = false;
        for (PersonKdh1018 person : contacts) {
            if (person.getName().equalsIgnoreCase(name)) {
                System.out.println(person);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("해당 이름의 연락처가 없습니다.");
        }
    }

    public static void main(String[] args) {
        new Kdh1018Homework().run();
    }
}

