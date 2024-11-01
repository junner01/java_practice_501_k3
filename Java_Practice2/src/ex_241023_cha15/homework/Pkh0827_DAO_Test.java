package ex_241023_cha15.homework;

import java.sql.*;
import java.util.ArrayList;

public class Pkh0827_DAO_Test {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "scott";
	String passwd = "tiger";

	public Pkh0827_DAO_Test() {
		try {
			Class.forName(driver); // 드라이버 로드
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public int insertMember(Pkh0827_DTO_test member) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			String sql = "INSERT INTO members(id, password, email) VALUES(?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getEmail());
			result = pstmt.executeUpdate(); // 쿼리 실행
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, null); // 자원 해제
		}
		return result; // 결과 반환
	}

	public ArrayList<Pkh0827_DTO_test> selectAllMembers() {
		ArrayList<Pkh0827_DTO_test> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			String query = "SELECT id, password, email FROM members";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery(); // 쿼리 실행

			while (rs.next()) {
				Pkh0827_DTO_test member = new Pkh0827_DTO_test();
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString("password"));
				member.setEmail(rs.getString("email"));
				list.add(member); // 리스트에 회원 추가
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, rs); // 자원 해제
		}
		return list; // 회원 리스트 반환
	}

	private void closeResources(Connection con, PreparedStatement pstmt, ResultSet rs) {
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
}
