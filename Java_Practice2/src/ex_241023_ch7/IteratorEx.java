package ex_241023_ch7;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Vector;

import util.PersonLsy1205Test;

public class IteratorEx {
	public static void main(String[] args) {
		// 정수 값만 다루는 제네릭 벡터 생성
		Vector<Integer> v = new Vector<Integer>();
		Vector<PersonLsy1205Test> persons = new Vector<PersonLsy1205Test>();
		PersonLsy1205Test lsy = new PersonLsy1205Test("lsy","0101",LocalDateTime.now().toString());
		PersonLsy1205Test lsy2 = new PersonLsy1205Test("lsy2","01012",LocalDateTime.now().toString());
		PersonLsy1205Test lsy3 = new PersonLsy1205Test("lsy3","01013",LocalDateTime.now().toString());
		persons.add(lsy);
		persons.add(lsy2);
		persons.add(lsy3);
		// 
		Iterator<PersonLsy1205Test> personsIt = persons.iterator(); // Iterator 객체 얻기
		// 비유, 하나의 가상 테이블 만든다. 
		// 0행에서 대기중. 
		// it.hasNext() -> 0행 -> 1행으로 가서, 확인.
		while (personsIt.hasNext()) {
			// it.next(); 1행의 요소를 가져오기.
			// Integer 참조형 -> int , 자동 언방식. 
			// 자동 형변환
			PersonLsy1205Test person = personsIt.next();
			System.out.println(person.toString());
		}
		
		// iterator 없이, 
		for (PersonLsy1205Test person : persons) {
			System.out.println("iterator 없이,");
			System.out.println(person.toString());
		}
		
		
		// 컬렉션의 요소는 모두 객체(인스턴스)참조형. 
		// 5, 기본형이지만, 자동으로 오토박싱, 형변환, Integer 변환해줌. 
		v.add(5); // 5 삽입
		v.add(4); // 4 삽입
		v.add(-1); // -1 삽입
		v.add(2, 100); // 4와 -1 사이에 정수 100 삽입

		// Iterator를 이용한 모든 정수 출력하기
		Iterator<Integer> it = v.iterator(); // Iterator 객체 얻기
		// 비유, 하나의 가상 테이블 만든다. 
		// 0행에서 대기중. 
		// it.hasNext() -> 0행 -> 1행으로 가서, 확인.
		while (it.hasNext()) {
			// it.next(); 1행의 요소를 가져오기.
			// Integer 참조형 -> int , 자동 언방식. 
			// 자동 형변환
			int n = it.next();
			System.out.println(n);
		}

		// Iterator를 이용하여 모든 정수 더하기
		int sum = 0;
		it = v.iterator(); // Iterator 객체 얻기
		while (it.hasNext()) {
			int n = it.next();
			sum += n;
		}
		System.out.println("벡터에 있는 정수 합 : " + sum);
	}
}
