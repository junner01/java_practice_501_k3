package ex_241023_cha15.homework;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Jh1_MemberDAO {
  public static final String driver = "oracle.jdbc.driver.OracleDriver";
  public static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
  public static final String userid = "scott";
  public static final String passwd = "tiger";

  private static Jh1_MemberDAO instance = null;

  public Jh1_MemberDAO() {
    try {
      Class.forName(driver);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static Jh1_MemberDAO getInstance() {
    if (instance == null) instance = new Jh1_MemberDAO();
    return instance;
  }


  public List<Jh1_MemberDTO> selectAll() throws SQLException {
    String query = "SELECT id, name, email FROM member501";
    ArrayList<Jh1_MemberDTO> list = new ArrayList<>();
    try (
      Connection connection = DriverManager.getConnection(url, userid, passwd);
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet resultSet = statement.executeQuery()
      ) {
      while (resultSet.next()) {
        list.add(new Jh1_MemberDTO(
          resultSet.getInt("id"),
          resultSet.getString("name"),
          resultSet.getString("email")
        ));
      }
    }
    return list;
  }

  public Jh1_MemberDTO selectByEmailAndPassword(String email, String passwdRaw) throws SQLException {
    String query = "SELECT id, name, email FROM member501 WHERE email = ? AND password = ?";
    ResultSet resultSet = null;
    try (
      Connection connection = DriverManager.getConnection(url, userid, passwd);
      PreparedStatement statement = connection.prepareStatement(query);
    ) {
      statement.setString(1, email);
      statement.setString(2, passwdRaw);
      resultSet = statement.executeQuery();
      if (resultSet.next()) {
        return (new Jh1_MemberDTO(
          resultSet.getInt("id"),
          resultSet.getString("name"),
          resultSet.getString("email")
        ));
      }
    } finally {
      if (resultSet != null) resultSet.close();
    }
    return null;
  }

  public int insert(String name, String email, String passwdRaw) throws SQLException {
    String query = "INSERT INTO member501(id, name, email, password) VALUES (member501_seq.NEXTVAL, ?, ?, ?)";
    Connection connection = DriverManager.getConnection(url, userid, passwd);
    PreparedStatement statement = connection.prepareStatement(query);

    statement.setString(1, name);
    statement.setString(2, email);
    statement.setString(3, passwdRaw);

    return statement.executeUpdate();
  }

}
