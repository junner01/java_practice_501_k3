package ex_241023_cha15.homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Kjy0227_MemberDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "scott";
	String passwd = "tiger";

	public Kjy0227_MemberDAO() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// 조회
	public List<Kjy0227_MemberDTO> selectMember() {
		List<Kjy0227_MemberDTO> list = new ArrayList<Kjy0227_MemberDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			String query = "SELECT name,email,password FROM MEMBER501 ORDER BY id ASC";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Kjy0227_MemberDTO dto = new Kjy0227_MemberDTO();
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setPassword(rs.getString("password"));
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 저장
	public int insertMember(String name, String email, String password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			String sql = "INSERT INTO MEMBER501(id,name,email,password) VALUES(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			System.out.println(name);
			
			pstmt.setInt(1, getMaxId());
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, password);
			result = pstmt.executeUpdate();
			
			System.out.println(result + "개의 레코드가 저장");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// id 생성
	private int getMaxId() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int maxId = 1;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			String query = "SELECT * FROM (SELECT id FROM MEMBER501 ORDER BY id DESC) WHERE ROWNUM = 1";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				maxId = rs.getInt("id") + 1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return maxId;
	}
}
