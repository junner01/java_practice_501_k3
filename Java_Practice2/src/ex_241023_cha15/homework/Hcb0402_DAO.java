package ex_241023_cha15.homework;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Hcb0402_DAO {
	// property
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "scott";
	String passwd = "tiger";
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// constructor
	public Hcb0402_DAO() {
		try {
			Class.forName(driver); // 드라이버를 로딩하는 초기화 작업을 생성자에서 구현한다.
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// DTO list를 반환하는 select method 정의
	public ArrayList<Hcb0402_DTO> select() {
		ArrayList<Hcb0402_DTO> list = new ArrayList<Hcb0402_DTO>();

		try {
			// 연결
			con = DriverManager.getConnection(url, userid, passwd);
			// 명령할 쿼리를 할당 후 넘겨줘서 결과를 리턴받음
			String query = "SELECT id,name,email,password FROM member501";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Hcb0402_DTO dto = new Hcb0402_DTO();
				dto.setId(rs.getInt("id"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setPassword(rs.getString("password"));
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

	// DTO member을 받아 insert method 정의
	public void insert(Hcb0402_DTO member) {

		try {
			// 연결
			con = DriverManager.getConnection(url, userid, passwd);
			String query = "INSERT INTO member501(id,name,email,password)" + "VALUES(member501_seq.NEXTVAL,?,?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member.name);
			pstmt.setString(2, member.email);
			pstmt.setString(3, member.password);
			// executeupdate로 insert문을 넘겨주고 변환된 행의 수를 int n 으로 받음
			int n = pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, n + "개의 행이 추가되었습니다", "추가 완료", JOptionPane.PLAIN_MESSAGE);
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
		}
	}

	// 이름을 받아 delete method 정의
	public void delete(String name) {
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			String query = "DELETE FROM member501 WHERE name = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
			int n = pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, n + "개의 행이 삭제되었습니다", "삭제 완료", JOptionPane.INFORMATION_MESSAGE);
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
		}
	}
	public void delete(int id) {
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			String query = "DELETE FROM member501 WHERE id = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			int n = pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, n + "개의 행이 삭제되었습니다", "삭제 완료", JOptionPane.INFORMATION_MESSAGE);
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
		}
	}
	
	// 선택하여 update method 정의
	public void update(int id, String name, String email, String pass) {
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			String query = "UPDATE member501 SET name = ? , email = ? , password = ? WHERE id = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, pass);
			pstmt.setInt(4, id);
			int n = pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, n + "개의 행이 수정되었습니다", "수정 완료", JOptionPane.PLAIN_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
				// 사용한 자원을 finally 문을 이용해서 반납한다.
				// 자원 반납은 사용했던 객체의 역순으로 하며 모두 공통적으로
				// close() 메소드를 사용한다.
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}