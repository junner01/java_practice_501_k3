package ex_241023_cha15.homework.lsy1205Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class LsyTest1205DAO {
	// DAO(Data Access Object), 비지니스 로직 처리하는 기능 모음집
	// crud, -> insert, select(전체조회), update, delete, select(한명 조회,email, id)
	public boolean insertMember(LsyTest1205DTO lsyTest1205DTO) {
		boolean ok = false;
		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 명령
		
		// MemberDTO memberDTO, 객체, 인스턴스, 게터, 세터.
		// 게터로 각 요소를 하나씩 꺼내기.
		int id = lsyTest1205DTO.getId();
		String name = lsyTest1205DTO.getName();
		String email = lsyTest1205DTO.getEmail();
		String password = lsyTest1205DTO.getPassword();
		try {
			
			con = ConnectionDB.getConn();
			String sql = "insert into member501(" + "id,name,email,password" + ") "
					+ "values(member501_seq.NEXTVAL,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, password);
			
			int r = pstmt.executeUpdate(); // 실행 -> 저장
			if (r > 0) {
				System.out.println("insert 성공");
				ok = true;
			} else {
				System.out.println("insert 실패");
			}
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

		return ok;
	} // insert

	// 조회
	public ArrayList<LsyTest1205DTO> selectAllMember() {
		
		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 명령
		ResultSet rs = null; // 결과
		
		ArrayList<LsyTest1205DTO> resultList = null;
		try {
			con = ConnectionDB.getConn();
			String sql = "select * from member501 order by id desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			resultList = new ArrayList<LsyTest1205DTO>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				
				LsyTest1205DTO dto = new LsyTest1205DTO(id, name, email, password);
				
				resultList.add(dto);

			} // while
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
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
		return resultList;

	}// selectMember

	// 한명만 조회
	public LsyTest1205DTO selectMemberById(int memberId) {

		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 명령
		ResultSet rs = null; // 결과
		LsyTest1205DTO dto = null; // 반환할 DTO 객체

		try {
			con = ConnectionDB.getConn();
			String sql = "select * from member501 where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memberId); // 첫 번째 매개변수로 id 설정
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				
				dto = new LsyTest1205DTO(id, name, email, password);
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
		return dto;
	}

	// 한명만 조회
	
	// 한명만 조회, email
		public LsyTest1205DTO selectMemberByEmail(String inputEmail) {

			Connection con = null; // 연결
			PreparedStatement pstmt = null; // 명령
			ResultSet rs = null; // 결과
			LsyTest1205DTO dto = null; // 반환할 DTO 객체

			try {
				con = ConnectionDB.getConn();
				String sql = "select * from member501 where email = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, inputEmail); // 첫 번째 매개변수로 id 설정
				rs = pstmt.executeQuery();

				if (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String email = rs.getString("email");
					String password = rs.getString("password");
					dto = new LsyTest1205DTO(id, name, email, password);
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
			return dto;
		}

		// 한명만 조회
	
	// 삭제
	public void deleteMember(int user_id) {

		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 명령

		try {

			con = ConnectionDB.getConn();

			String sql = "DELETE  FROM member501 WHERE id = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, user_id);

			int r = pstmt.executeUpdate(); // 실행 -> 저장

			if (r > 0) {
				System.out.println("삭제 성공");
				JOptionPane.showMessageDialog(null, user_id + "번 삭제 성공", "Message", JOptionPane.ERROR_MESSAGE);

			} else {
				System.out.println("삭제 실패");
			}

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
	// 삭제

	// 수정, 13번 라인에서, ( 모델 DTO에 전부 담아서 전달함), 밑에 방식 보다는 모델에 담아서 전달함.
	public void updateMember(int user_id, String userName, String userEmail, String password) {

		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 명령

		try {

			con = ConnectionDB.getConn();
			// UPDATE member501 SET name = 'ddd2' , email = 'ddd2' , password = 'ddd2' WHERE
			// id = 5;
			String sql = "UPDATE member501 SET name = ? , email = ? , password = ? WHERE id = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, userName);
			pstmt.setString(2, userEmail);
			pstmt.setString(3, password);
			pstmt.setInt(4, user_id);

//					System.out.println("user_id : " + user_id);
//					System.out.println("user_name : " + userName);
//					System.out.println("user_email : " + userEmail);
//					System.out.println("user_password : " + password);

			int r = pstmt.executeUpdate(); // 실행 -> 저장

			if (r > 0) {
				System.out.println("수정 성공");
				JOptionPane.showMessageDialog(null, user_id + "번 수정 성공", "Message", JOptionPane.ERROR_MESSAGE);

			} else {
				System.out.println("수정 실패");
			}

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
	// 수정

}// class
