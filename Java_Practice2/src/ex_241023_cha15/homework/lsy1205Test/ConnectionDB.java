package ex_241023_cha15.homework.lsy1205Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {

	// 연결을 위한 준비물.
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver"; // 12행 ~ 15행 데이터베이스 접속을 위한 4가지 정보를 String 변수에 저장.
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERID = "scott";
	private static final String PASSWORD = "tiger";
	
	public static Connection getConn() {
		Connection con = null;
	       
        try {
            Class.forName(DRIVER); //1. 드라이버 로딩
            con = DriverManager.getConnection(URL,USERID,PASSWORD); //2. 드라이버 연결
           
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        return con;
		
	}
	
}