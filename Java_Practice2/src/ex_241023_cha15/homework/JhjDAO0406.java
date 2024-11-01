package ex_241023_cha15.homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JhjDAO0406 {

	String driver = "oracle.jdbc.driver.OracleDriver"; 
	String url = "jdbc:oracle:thin:@localhost:1521:xe";

	String userid = "scott";
	String passwd = "tiger";

	public JhjDAO0406() {
		try {
			Class.forName(driver); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	public ArrayList<JHJDTO0406> select() {
		ArrayList<JHJDTO0406> list = new ArrayList<JHJDTO0406>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			String query = "SELECT name,email,password FROM members";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				JHJDTO0406 dto = new JHJDTO0406(rs.getString("name"), rs.getString("email"), rs.getString("password"));
//				
//				dto.setName(rs.getString("name"));
//				dto.setEmaile(rs.getString("email"));
//				dto.setPassword(rs.getString("password"));
				
				list.add(dto); 	
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
	}
	
	public int insertDB(JHJDTO0406 dto) {
		Connection con = null; // 서버에 접근가능한 ip username passwrod 세가지가 담겨있음
		PreparedStatement pstmt = null; //sql구문을 전달함
		int result = 0;
	
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			String sql = "INSERT INTO members (id, name, email, password) VALUES (members_seq.NEXTVAL, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getPassword());
			result = pstmt.executeUpdate();
			System.out.println(result + "개의 레코드가 저장");
			
			con.commit();
		} catch (Exception e) {
			 System.out.println("데이터 저장 실패: " + e.getMessage()); // 오류 메시지 출력
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