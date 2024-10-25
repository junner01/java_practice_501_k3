package ex_241023_ch7.homework;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Iterator;

public class Cgw0910Communication {

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        ArrayList<Cgw0910Person> personData = new ArrayList<Cgw0910Person>();
        
        outerLoop:
        while (true) {
            System.out.println("입력값 대기중 1.입력, 2.출력, 3.검색, 4.삭제, 5.랜덤, 6.종료임");
            
            if (!scanner.hasNextInt()) {
                System.out.println("유효한 숫자를 입력해주세요.");
                scanner.next();
                continue;
            }
            
            int input = scanner.nextInt();
            scanner.nextLine();
            
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
                        LocalDateTime userDate = LocalDateTime.now();
                        Cgw0910Person person = new Cgw0910Person();
                        person.setPerson(userName, userPhoneNum, userDate);
                        personData.add(person);
                        System.out.println("연락처가 저장되었습니다.");
                        break;
                    }
                    case 2: {
                        System.out.println("연락처 데이터 호출");
                        System.out.println("유저 이름 입력 시 데이터 내 정보를 호출합니다");
                        System.out.print("입력: ");
                        String userName = scanner.nextLine();
                        boolean inData = false;
                        for (Cgw0910Person person : personData) {
                            if (person.getName().equals(userName)) {
                                person.showInfo();
                                inData = true;
                                break;
                            }
                        }
                        if (!inData) {
                            System.out.println("데이터 내 " + userName + "(이)가 없습니다.");
                        }
                        break;
                    }
                    case 3: {
                        System.out.println("연락처 데이터 검색");
                        System.out.println("유저 이름 입력 시 데이터 내 정보를 검색합니다");
                        System.out.print("입력: ");
                        String userName = scanner.nextLine();
                        boolean inData = false;
                        for (Cgw0910Person person : personData) {
                            if (person.getName().equals(userName)) {
                                System.out.println(userName + "은 데이터 내에 있습니다.");
                                inData = true;
                                break;
                            }
                        }
                        if (!inData) {
                            System.out.println("데이터 내 " + userName + "(이)가 없습니다.");
                        }
                        break;
                    }
                    case 4: {
                        System.out.println("연락처 데이터 삭제");
                        System.out.println("유저 이름 입력 시 데이터 내 정보를 삭제합니다");
                        System.out.print("입력: ");
                        String userName = scanner.nextLine();
                        boolean inData = false;
                        Iterator<Cgw0910Person> iterator = personData.iterator();
                        while (iterator.hasNext()) {
                            Cgw0910Person person = iterator.next();
                            if (person.getName().equals(userName)) {
                                iterator.remove();
                                inData = true;
                                System.out.println(userName + "의 연락처가 삭제되었습니다.");
                                break;
                            }
                        }
                        if (!inData) {
                            System.out.println("데이터 내 " + userName + "(이)가 없습니다.");
                        }
                        break;
                    }
                    case 5: {
                        System.out.println("연락처 데이터를 랜덤하게 호출합니다.");
                        if (!personData.isEmpty()) {
                            Cgw0910Person randomPerson = personData.get(random.nextInt(personData.size()));
                            randomPerson.showInfo();
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
                }
            } else {
                System.out.println("1부터 6까지의 숫자만 입력해주세요.");
            }
        }
        scanner.close();
    }
}
