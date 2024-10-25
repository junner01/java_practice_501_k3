package ex_241023_ch7.homework;

import java.time.LocalDateTime;
import java.util.*;

public class Kjy0227_ArrayListTest {

   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      ArrayList<Kjy0227_Person> list = new ArrayList<Kjy0227_Person>();
      int idx = -1;
      
      System.out.println("1.입력, 2.출력, 3.검색, 4.삭제, 5.랜덤, 6.종료");
      while(true) {
         String input = sc.next();
         // 입력
         if("1".equals(input)) {
            for(int i=0; i<3; i++) {
               Kjy0227_Person person = new Kjy0227_Person();

               System.out.println("이름>>");
               person.setName(sc.next());
               System.out.println("연락처>>");
               person.setPhoneNum(sc.next());
               person.setCdt(LocalDateTime.now());

               list.add(person);
            }
            System.out.println("등록 완료");
            continue;
         }
         // 출력
         if("2".equals(input)) {
            if(!list.isEmpty()) {
               for (Kjy0227_Person person : list) {
                  System.out.println(person.toString());
               }
            } else {
               System.out.println("리스트가 비어 있습니다.");
            }
            continue;
         }
         // 검색
         if("3".equals(input)) {
            System.out.println("검색어를 입력하세요 : ");
            String srh = sc.next();
            idx = -1;
            
            for (Kjy0227_Person person : list) {
               if(srh.equals(person.getName())) {
                  idx = list.indexOf(person);
               }
            }

            if(idx != -1) {
               System.out.println(list.get(idx).toString());
            } else {
               System.out.println("검색 결과가 존재하지 않습니다.");
            }
            continue;
         }
         // 삭제
         if("4".equals(input)) {
            System.out.println("삭제할 이름을 입력하세요 : ");
            String srh = sc.next();
            idx = -1;

            for (Kjy0227_Person person : list) {
               if(srh.equals(person.getName())) {
                  idx = list.indexOf(person);
               }
            }

            if(idx != -1) {
               list.remove(idx);
               System.out.println("삭제가 완료되었습니다.");
               for (Kjy0227_Person person : list) {
                  System.out.println(person.toString());
               }
            } else {
               System.out.println("일치하는 이름이 존재하지 않습니다.");
            }
            continue;
         }
         // 랜덤
         if("5".equals(input)) {
            if(!list.isEmpty()) {
               Random random = new Random();
               System.out.println(list.get(random.nextInt(list.size())).toString());
            } else {
               System.out.println("리스트가 비어 있습니다.");
            }
            continue;
         }
         // 종료
         if("6".equals(input)) {
            System.out.println("프로그램을 종료합니다.");
            break;
         }
      }
      sc.close();
   }

}