package ex_241023_cha15.homework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class SoojongJDBC_insert extends JFrame {
    String driver = "oracle.jdbc.driver.OracleDriver";
    String url = "jdbc:oracle:thin:@localhost:1521:xe";
    String userid = "scott";
    String passwd = "tiger";

    private JTextField wordField;
    private JTextField emailField;
    private JTextField passField;
    private JTextArea wordArea;

    public SoojongJDBC_insert() {
        setTitle("회원 관리 프로그램");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        inputPanel.add(new JLabel("아이디"));
        wordField = new JTextField(10);
        inputPanel.add(wordField);

        inputPanel.add(new JLabel("이메일"));
        emailField = new JTextField(10);
        inputPanel.add(emailField);
        
        inputPanel.add(new JLabel("비밀번호"));
        passField = new JTextField(10);
        inputPanel.add(passField);

        JButton addbtn = new JButton("추가");
        inputPanel.add(addbtn);

        JButton loadbtn = new JButton("로드");
        inputPanel.add(loadbtn);

        wordArea = new JTextArea(20, 50);
        wordArea.setBorder(new LineBorder(Color.black, 1));
        wordArea.setEditable(false); // 사용자가 직접 수정하지 못하도록 설정

        add(inputPanel, BorderLayout.NORTH);
        add(wordArea, BorderLayout.CENTER);

        addbtn.addActionListener(e -> addword());
        loadbtn.addActionListener(e -> loadword());

        setVisible(true);
    }

    public void addword() {
        String word = wordField.getText();
        String email = emailField.getText();
        String password = passField.getText();

        if (!word.isEmpty() && !email.isEmpty()) {
            String insertQuery = "INSERT INTO member501 (id, email, password) VALUES (?, ?, ?)";

            try {
                Class.forName(driver);
                try (Connection con = DriverManager.getConnection(url, userid, passwd);
                     PreparedStatement pstmt = con.prepareStatement(insertQuery)) {

                    pstmt.setString(1, word);
                    pstmt.setString(2, email);
                    pstmt.setString(3, password);

                    int result = pstmt.executeUpdate();
                    if (result > 0) {
                        // JOptionPane.showMessageDialog(null, "추가되었습니다.");
                        loadword(); // 추가 후 바로 로드하여 최신 상태로 표시
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "데이터베이스에 저장 실패.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "아이디와 이메일을 모두 입력해주세요.");
        }
    }

    public void loadword() {
        wordArea.setText(""); // 초기화하여 이전 데이터 삭제
        String selectQuery = "SELECT id, email, password FROM member501";

        try {
            Class.forName(driver);
            try (Connection con = DriverManager.getConnection(url, userid, passwd);
                 PreparedStatement pstmt = con.prepareStatement(selectQuery);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    String id = rs.getString("id");
                    String email = rs.getString("email");
                    String password = rs.getString("password");

                    wordArea.append("아이디 : " + id + ", 이메일 : " + email + ", 비밀번호 : " + password + "\n");
                }
                // JOptionPane.showMessageDialog(null, "데이터 로드 완료.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "데이터 로드 실패.");
        }
    }

    public static void main(String[] args) {
        new SoojongJDBC_insert();
    }
}
