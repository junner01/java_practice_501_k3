package ex_241023_cha15.homework;

import java.lang.classfile.instruction.ReturnInstruction;
import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Csb806DAO {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "scott";
	String passwd = "tiger";

	public Csb806DAO() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Csb806DTO> select() {
		ArrayList<Csb806DTO> list = new ArrayList<Csb806DTO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			String query = "SELECT * FROM MEMBER501";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String email = null;
				String name = null;
				String password = null;
				Csb806DTO dto = new Csb806DTO(name, email, password);
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
	}//select
	
	//insert 기능
	public int insertDB(String name, String email, String password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			String sql = "INSERT INTO member501(ID, name, email, password) VALUES(MEMBER501_SEQ.NEXTVAL,?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, password);
			
			result = pstmt.executeUpdate();
			System.out.println(result + "개의 레코드가 저장");
		} catch(Exception e) {
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
	}//insert


		

}

