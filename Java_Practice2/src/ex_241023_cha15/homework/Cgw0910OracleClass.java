package ex_241023_cha15.homework;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Cgw0910OracleClass {
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String userid = "scott";
    private String passwd = "tiger";
    private String sql;

    public Cgw0910OracleClass() {
        sql = "CREATE TABLE users (" +
                "user_id VARCHAR2(50) PRIMARY KEY, " +
                "userpassword VARCHAR2(50) NOT NULL, " +
                "email VARCHAR2(50)" +
                ")";
        
        try (Connection conn = DriverManager.getConnection(url, userid, passwd);
             Statement stmt = conn.createStatement()) {

            DatabaseMetaData dbm = conn.getMetaData();
            try (ResultSet tables = dbm.getTables(null, null, "USERS", null)) {
                if (!tables.next()) {
                    stmt.execute(sql);
                    System.out.println("USERS 테이블이 성공적으로 생성되었습니다.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertOracle(String id, Cgw0910UserData userData) {
        String password = userData.getUserPassword();
        String email = userData.getUserEmail();
        sql = "INSERT INTO users(user_id, userpassword, email) VALUES (?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, userid, passwd);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
             
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            pstmt.setString(3, email);
            pstmt.executeUpdate();
            System.out.println("데이터가 성공적으로 저장되었습니다.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cgw0910UserData searchOracle(String id) {
        Cgw0910UserData userData = null;
        sql = "SELECT * FROM users WHERE user_id = ?";

        try (Connection con = DriverManager.getConnection(url, userid, passwd);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
             
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String password = rs.getString("userpassword");
                    String email = rs.getString("email");
                    userData = new Cgw0910UserData(password, email);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userData;
    }

    public void clearTableData() {
        sql = "TRUNCATE TABLE users";

        try (Connection con = DriverManager.getConnection(url, userid, passwd);
             Statement stmt = con.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("테이블 데이터가 성공적으로 클리어되었습니다.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteData(String id) {
        sql = "DELETE FROM users WHERE user_id = ?";

        try (Connection con = DriverManager.getConnection(url, userid, passwd);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
             
            pstmt.setString(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("데이터가 성공적으로 삭제되었습니다.");
            } else {
                System.out.println("삭제할 데이터를 찾을 수 없습니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTable() {
        sql = "DROP TABLE users";

        try (Connection con = DriverManager.getConnection(url, userid, passwd);
             Statement stmt = con.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("테이블이 성공적으로 삭제되었습니다.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
