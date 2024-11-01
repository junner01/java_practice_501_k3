package ex_241023_cha15.homework;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Kjy1122Member extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JTextField passwordField;
    private DefaultTableModel tableModel;
    private Kjy1122DAO memberDAO;

    public Kjy1122Member() {
        memberDAO = new Kjy1122DAO();  // DAO 초기화
        
        setTitle("회원 관리 프로그램");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 상단 입력 패널
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("이름: "));
        nameField = new JTextField(10);
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("이메일: "));
        emailField = new JTextField(10);
        inputPanel.add(emailField);

        inputPanel.add(new JLabel("비밀번호: "));
        passwordField = new JTextField(10);
        inputPanel.add(passwordField);

        JButton addButton = new JButton("추가");
        inputPanel.add(addButton);

        JButton viewButton = new JButton("조회");
        inputPanel.add(viewButton);
        
        JButton deleteButton = new JButton("삭제");
        inputPanel.add(deleteButton);

        add(inputPanel, BorderLayout.NORTH);

        // 중앙 테이블 패널
        tableModel = new DefaultTableModel(new String[]{"이름", "이메일", "비밀번호"}, 0);
        JTable memberTable = new JTable(tableModel);
        add(new JScrollPane(memberTable), BorderLayout.CENTER);

        // 버튼 이벤트 처리
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMember();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewMembers();
            }
        });
        
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMember();
            }
        });

        setVisible(true);
    }

    // 회원 추가 메서드
    private void addMember() {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            int result = memberDAO.insertMember(name, email, password);

            if (result > 0) {
                JOptionPane.showMessageDialog(this, "회원이 성공적으로 추가되었습니다.");
                nameField.setText("");
                emailField.setText("");
                passwordField.setText("");
                // 여기서 viewMembers 호출 제거
            } else {
                JOptionPane.showMessageDialog(this, "회원 추가에 실패했습니다.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "모든 필드를 입력해 주세요.");
        }
    }

    // 회원 조회 메서드
    private void viewMembers() {
        tableModel.setRowCount(0);  // 기존 테이블 데이터를 지움
        ArrayList<Kjy1122DTO> memberList = memberDAO.select();
        for (Kjy1122DTO member : memberList) {
            tableModel.addRow(new Object[]{member.getName(), member.getEmail(), member.getPassword()});
        }
    }
    
    private void deleteMember() {
        String email = emailField.getText();

        if (!email.isEmpty()) {
            int result = memberDAO.deleteMember(email);

            if (result > 0) {
                JOptionPane.showMessageDialog(this, "회원이 성공적으로 삭제되었습니다.");
                emailField.setText("");  // 입력 필드 초기화
                viewMembers();  // 회원 목록 갱신
            } else {
                JOptionPane.showMessageDialog(this, "회원 삭제에 실패했습니다.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "삭제할 회원의 이메일을 입력해 주세요.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Kjy1122Member::new);
    }
}
