package ex_241023_cha15.homework;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class Csb806Personal extends JFrame {
    
    private JTextField nameField;
    private JTextField mailField;
    private JTextField passwordField;
    private JPanel memberPanel;
    
    Csb806DAO dao = new Csb806DAO();
    
    public Csb806Personal() {
        setTitle("Membership");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLayout(new BorderLayout());
        
        
        JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new FlowLayout());
        
        // 이름 입력
        inputPanel.add(new JLabel("이름:  "));
        nameField = new JTextField(10);
        inputPanel.add(nameField);
        
        // 이메일 입력
        inputPanel.add(new JLabel("이메일:  "));
        mailField = new JTextField(10);
        inputPanel.add(mailField);
        
        // 비밀번호 입력칸
        inputPanel.add(new JLabel("비밀번호:  "));
        passwordField = new JTextField(10);
        inputPanel.add(passwordField);
        
        // 추가 버튼
        JButton addButton = new JButton("추가");
        inputPanel.add(addButton);
        
        // 조회 버튼
        JButton checkButton = new JButton("조회");
        inputPanel.add(checkButton);
        
        // 회원 정보 표시 패널
        memberPanel = new JPanel();
        memberPanel.setLayout(new BoxLayout(memberPanel, BoxLayout.Y_AXIS));
        
        add(inputPanel, BorderLayout.NORTH);
        add(memberPanel, BorderLayout.CENTER);
        
        // 이벤트 리스너 추가
        addButton.addActionListener(e -> addMemberInfo());
        checkButton.addActionListener(e -> displayMemberInfo());
        
        setVisible(true);
    }
    
    // 회원 정보 추가 메서드
    public void addMemberInfo() {
        String name = nameField.getText();
        String email = mailField.getText();
        String password = passwordField.getText();
        
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
        	System.out.println("모든 필드를 입력하세요.");
            return;
        }
        
       int result = dao.insertDB(name,email,password);
    }
    
    // 회원 정보 조회 메서드
    private void displayMemberInfo() {
        memberPanel.removeAll(); // 기존 패널 내용 제거
        
        ArrayList<Csb806DTO> members = dao.select(); // 모든 회원 정보를 가져옴
        if (members != null) {
            for (Csb806DTO member : members) {
            	memberPanel.add(new JLabel("이름: " + member.getName()));
                memberPanel.add(new JLabel("이메일: " + member.getEmail()));
                memberPanel.add(new JLabel("비밀번호: " + member.getPassword()));
                
                // 빈 줄을 추가하여 회원 간 구분
                memberPanel.add(new JLabel("---------------------"));
            }
        } else {
            memberPanel.add(new JLabel("회원 정보가 없습니다."));
        }

        memberPanel.revalidate(); // 패널 갱신
        memberPanel.repaint();
    }

    public static void main(String[] args) {
        new Csb806Personal();
    }
}
