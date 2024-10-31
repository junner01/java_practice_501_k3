package ex_241023_cha15;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC_select_ex_1 {
	public static void main(String[] args) {
		// 자바 -> 오라클 서버 접근 하기위한 준비물 4가지. 
		// ojdbc8_g.jar 파일을 , 프로젝트 연결 후, 라이브러리이고, 
		// 여기 안에 패키지명으로 구성 되었고, 해당 경로 하위에 OracleDriver 클래스 가 존재함. 
		 String driver = "oracle.jdbc.driver.OracleDriver";   // 12행 ~ 15행 데이터베이스 접속을 위한 4가지 정보를 String  변수에  저장.
		 // 오라클 서버의 아이피 주소, 현재는 각자 디비 서버를 이용해서, 본인 아이피 : localhost 사용함. 
		 // 1521 오라클 웰노운 포트 , xe : 스키마 , 데이터베이스 라고 부르기도 함. 
		 String url = "jdbc:oracle:thin:@localhost:1521:xe";
		 // 계정 기본은, 모두 접근 불가. 설정을 통해서, 계정에게 접근 권한, 테이블에 관련 여러가지 
		 // 권한 , 시스템이 할당해야 가능함. 
		 String userid = "scott";
		 String passwd = "tiger";
		 
		 // 디비 서버에 연결하기 위한, 1)url : 서버 아이피 주소, 2)계정, 3)비번 등을 가지고 있음. 도구라고 생각하기.  
		 Connection con = null;
		 // 동적 sql 처리하기 위한 도구
		 PreparedStatement pstmt = null;
		 // select 할 때만 필요한 도구이고, 가상의 테이블 처럼 생각하면 됨. 
		 ResultSet rs = null;
		 try {
			 // 필요한 , 디비에 접근, sql 작성, 등 도구를 이용 가능. 
			 Class.forName(driver);
			 //명시된 드라이버 클래스를 메모리에 로딩한다. 
			 con = DriverManager.getConnection(url, userid, passwd); 
			 // DriverManager 클래스의 getConnection() 메소드를 이용해서
//			 Connection 객체를 얻는다. 
			 // 디비 서버에게 일을 시킬려면, sql 라는 문법을 전달해야함. 
			 // SELECT 조회할 열1, 조회할 열2,... FROM 조회할 테이블명; 
			 String query = "SELECT deptno,dname,loc FROM dept";
			 //요청할 SQL  문을 String 변수에 저장한다.
			 // 서버에 일을 시킬 준비가 완료됨. 
			 pstmt =con.prepareStatement(query); 
			 //SQL 문 전송에 필요한 PreparedStatement 객체를 
			 //Connection 객체의 preparedStatement(sql)메소드를 이용해서 얻는다. 
			 
			 // 일을 시키면, 수행후, 결과 테이블 정보를 받아옴. 
			 // rs -> 가상의 테이블 , 0행부터 시작합니다. 
			 rs = pstmt.executeQuery(); 
			 //SELECT 문을 요청하기 때문에 executeQuery()
//			 메소드를 사용하며 결과는 ResultSet 객체로 받는다.
			 //rs.next() , 0행 -> 1행으로
			 // deptno 열, dname 열, loc 열 , 각 열의 값을 하나씩 가지고 와서 
			 // 결국에 1행이됨. 
			 // 조회된 테이블 결과 행 만큼 진행. 4번 진행됨. 
			 while(rs.next()) {
				 int deptno = rs.getInt("deptno");
				 String dname = rs.getString("dname");
				 String loc = rs.getString("loc");
				 System.out.println(deptno + " " + dname + " " + loc);
			 }
		 }catch(Exception e) {
			 e.printStackTrace();
		 }finally {
			 try {
				 // 반납시 , 순서가 , 역순으로 진행함. 
				 if (rs != null) rs.close();
				 if (pstmt != null) pstmt.close();
				 if ( con != null) con.close();
			 }catch(SQLException e){
				 e.printStackTrace();
			 }
		 }
			 }
		}
