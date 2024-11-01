package ex_241023_cha15.homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Lsh0708_DAO {

	// 1, 연결하기 위한 정보 4가지
		String driver = "oracle.jdbc.driver.OracleDriver"; // 10행 ~ 14행 데이터베이스
		// 접속을 위한 4가지 정보를 String 변수에 저장.
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		// 계정 기본은, 모두 접근 불가. 설정을 통해서, 계정에게 접근 권한, 테이블에 관련 여러가지
		// 권한 , 시스템이 할당해야 가능함.
		String userid = "scott";
		String passwd = "tiger";
		
		public Lsh0708_DAO() {
			try {
				Class.forName(driver); // 드라이버를 로딩하는 초기화 작업을 생성자에서 구현한다.
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		
		public ArrayList<Lsh0708_DTO> select() {
			ArrayList<Lsh0708_DTO> list = new ArrayList<Lsh0708_DTO>();

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = DriverManager.getConnection(url, userid, passwd);
				// DriverManager 클래스의 getConnection() 메소드를
				// 이용해서 Connection 객체를 얻는다.
				String query = "SELECT name,email,password FROM member501";
				// 요청할 SQL 문을 String 변수에 저장한다.
				pstmt = con.prepareStatement(query);
				// SQL 문 전송에 필요한 PreparedStatement 객체를
				// Connection 객체의 preparedStatement(sql)메소드를 이용해서 얻는다.
				rs = pstmt.executeQuery();
				// SELECT 문을 요청하기 때문에 executeQuery() 메소드를 사용하며
				// 결과는 ResultSet 객체로 받는다.
				while (rs.next()) {
					Lsh0708_DTO dto = new Lsh0708_DTO();
					// 각각의 레코드 정보를 JDBC_Ex_DTO 클래스의 객체에 저장한다.
					dto.setUserId(rs.getString("name"));
					dto.setUserMail(rs.getString("email"));
					dto.setUserPass(rs.getString("password"));					
					list.add(dto); // 저장된 JDBC_Ex_DTO 클래스의 객체를 누적시키기 위해서
					// ArrayList 객체 저장한다. while 문이 모두 실행된 후에는 dept 테이블의 모든 레코드가
					// ArrayList 의 객체에 모두 저장된다.
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return list;
		}// select
		
		public int insertDB(String userID,String userPass,String userMail) {
			Connection con = null;
			PreparedStatement pstmt = null;
			int result = 0;
			try {
				con = DriverManager.getConnection(url, userid, passwd);
				String sql = "INSERT INTO member501(id,name,email,password)" + "VALUES(member501_seq.NEXTVAL,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, userID);
				pstmt.setString(2, userMail);
				pstmt.setString(3, userPass);
				System.out.println(userID+userPass+userMail);
				result = pstmt.executeUpdate();
				System.out.println(result + "개의 레코드가 저장");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}// finally
			return result;
		} // insert 
		
}
