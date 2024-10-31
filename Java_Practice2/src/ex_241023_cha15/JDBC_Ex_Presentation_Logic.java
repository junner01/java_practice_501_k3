package ex_241023_cha15;

import java.util.ArrayList;

public class JDBC_Ex_Presentation_Logic {
	public static void main(String[] args) {

		// 디비 서버에서 crud 관련 기능 모음집. 
		// 현재, 1개 select , 
		JDBC_Ex_DAO dao = new JDBC_Ex_DAO();
		// JDBC_Ex_DAO 클래스를 사용하기 위해서 객체 생성.
		
		// DAO, 2) insert 기능 추가해서, 
		// 여기에서 이용해보기. 
		int result = dao.insertDB(99,"더미부서1","부산진구1");
		System.out.println("DAO 에서 insert 기능 추가 후 : 값반환:" + result +"개 추가됨.");
		

		ArrayList<JDBC_Ex_DTO> list = dao.select();
		// JDBC_Ex_DAO 클래스의 select 메소드 호출.
		// 결과값으로는 dept 테이블의 모든 레코드를 저장하고 있는
		// ArrayList 객체를 리턴 받는다.

		for (JDBC_Ex_DTO dto : list) {
			int deptno = dto.getDeptno();
			String dname = dto.getDname();
			String loc = dto.getLoc();
			System.out.println(deptno + "\t" + dname + "\t" + loc);
		} // for ~ each 문을 이용해서 ArrayList 에 저장된 레코드의 값을 출력한다.

	}
}
