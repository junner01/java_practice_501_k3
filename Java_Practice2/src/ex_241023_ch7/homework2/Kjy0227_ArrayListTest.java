package ex_241023_ch7.homework2;

import java.time.LocalDateTime;
import java.util.*;

public class Kjy0227_ArrayListTest {

   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      HashMap<String, Kjy0227_Person> map = new HashMap<String, Kjy0227_Person>();
      
      while(true) {
    	 System.out.println("1.입력, 2.출력, 3.검색, 4.삭제, 5.랜덤, 6.종료");
         String input = sc.next();
         // 입력
         if("1".equals(input)) {
        	 Kjy0227_Person person = new Kjy0227_Person();
        	 
        	 System.out.println("이름>>");
        	 person.setName(sc.next());
        	 System.out.println("연락처>>");
        	 person.setPhoneNum(sc.next());
        	 person.setCdt(LocalDateTime.now());
        	 
        	 map.put(person.getName(), person);
        	 System.out.println("등록 완료");
        	 continue;
         }
         // 출력
         if("2".equals(input)) {
            if(!map.isEmpty()) {
               Set<String> keySet = map.keySet();
               for (String key : keySet) {
            	   System.out.println(map.get(key).toString());
               }
            } else {
               System.out.println("연락처 정보가 없습니다.");
            }
            continue;
         }
         // 검색
         if("3".equals(input)) {
            System.out.println("검색어를 입력하세요 : ");
            String srh = sc.next();
            
            if(map.containsKey(srh)) {
            	System.out.println(map.get(srh).toString());
            } else {
               System.out.println("검색 결과가 존재하지 않습니다.");
            }
            continue;
         }
         // 삭제
         if("4".equals(input)) {
            System.out.println("삭제할 이름을 입력하세요 : ");
            String srh = sc.next();

            if(map.containsKey(srh)) {
            	map.remove(srh);
            	Set<String> keySet = map.keySet();
                for (String key : keySet) {
             	   System.out.println(map.get(key).toString());
                }
            } else {
               System.out.println("일치하는 이름이 존재하지 않습니다.");
            }
            continue;
         }
         // 랜덤
         if("5".equals(input)) {
            if(!map.isEmpty()) {
               Random random = new Random();
               Object[] keys = map.keySet().toArray();
               Object randomKey = keys[random.nextInt(keys.length)];
               System.out.println(map.get(randomKey).toString());
            } else {
               System.out.println("연락처 정보가 없습니다.");
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