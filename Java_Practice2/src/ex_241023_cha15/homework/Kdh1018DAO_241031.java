package ex_241023_cha15.homework;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Kdh1018DAO_241031 {
    private String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe"; 
    private String jdbcUsername = "scott";  
    private String jdbcPassword = "tiger"; 

    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void addUser(Kdh1018DTO_241031 user) {
    	String sql = "insert into NewMembers(" + "id,name,email,password" + ") "
				+ "values(member501_seq.NEXTVAL,?,?,?)";
//        String sql = "INSERT INTO NewMembers (name, email, password) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            System.out.println("회원이 추가되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Kdh1018DTO_241031> getUsers() {
        List<Kdh1018DTO_241031> userList = new ArrayList<>();
        String sql = "SELECT name, email, password FROM NewMembers";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                userList.add(new Kdh1018DTO_241031(name, email, password));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }
}
