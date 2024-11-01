package ex_241023_cha15.homework;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Jih0316DB_DAO {
	private Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott"; 
		String password = "tiger";
		return DriverManager.getConnection(url, user, password);
	}

	public void addMember(Jih0316DB_DTO member) throws SQLException {
		try (Connection conn = getConnection()) {
			String sql = "INSERT INTO Membership (id, password, email) VALUES (?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getEmail());
			pstmt.executeUpdate();
		}
	}

	public List<Jih0316DB_DTO> loadMembers() throws SQLException {
		List<Jih0316DB_DTO> members = new ArrayList<>();
		try (Connection conn = getConnection()) {
			String sql = "SELECT id, password, email FROM Membership";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("id");
				String password = rs.getString("password");
				String email = rs.getString("email");
				members.add(new Jih0316DB_DTO(id, password, email));
			}
		}
		return members;
	}

	public void resetDatabase() throws SQLException {
		try (Connection conn = getConnection()) {
			String sql = "DELETE FROM Membership"; // 모든 데이터 삭제 쿼리
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		}
	}
}
