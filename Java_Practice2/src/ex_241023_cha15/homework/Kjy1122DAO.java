package ex_241023_cha15.homework;

import java.sql.*;
import java.util.ArrayList;

public class Kjy1122DAO {
    // DB 연결 정보
    String driver = "oracle.jdbc.driver.OracleDriver"; 
    String url = "jdbc:oracle:thin:@localhost:1521:xe";
    String userid = "scott";
    String passwd = "tiger";

    // 생성자에서 드라이버 로드
    public Kjy1122DAO() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 회원 목록 조회 메서드
    public ArrayList<Kjy1122DTO> select() {
        ArrayList<Kjy1122DTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(url, userid, passwd);
            String query = "SELECT name, email, password FROM members";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Kjy1122DTO member = new Kjy1122DTO(rs.getString("name"), rs.getString("email"), rs.getString("password"));
                list.add(member);
            }
        } catch (SQLException e) {
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

    // 회원 추가 메서드
    public int insertMember(String name, String email, String password) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            con = DriverManager.getConnection(url, userid, passwd);
            String sql = "insert into members(" + "id,name,email,password" + ") "
					+ "values(member501_seq.NEXTVAL,?,?,?)";
//            String sql = "INSERT INTO members (name, email, password) VALUES (?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);

            result = pstmt.executeUpdate();
            System.out.println(result + "개의 레코드가 저장되었습니다.");
        } catch (SQLException e) {
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
    public int deleteMember(String email) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            con = DriverManager.getConnection(url, userid, passwd);
            String sql = "DELETE FROM members WHERE email = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);

            result = pstmt.executeUpdate();
            System.out.println(result + "개의 레코드가 삭제되었습니다.");
        } catch (SQLException e) {
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

    
}


