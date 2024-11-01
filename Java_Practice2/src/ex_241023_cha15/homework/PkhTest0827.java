package ex_241023_cha15.homework;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PkhTest0827 {
	private static Pkh0827_DAO_Test dao = new Pkh0827_DAO_Test(); // DAO 객체 생성
	private static JTable memberTable; // 테이블
	private static DefaultTableModel tableModel; // 테이블 모델

	public static void main(String[] args) {
		// 프레임 설정
		JFrame frame = new JFrame("회원 관리");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 400);
		frame.setLayout(new BorderLayout());

		// 입력 패널
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new FlowLayout());

		// ID, 비밀번호, 이메일 입력 필드
		JLabel idLabel = new JLabel("ID:");
		JTextField idField = new JTextField(10);

		JLabel passwordLabel = new JLabel("비밀번호:");
		JPasswordField passwordField = new JPasswordField(10);

		JLabel emailLabel = new JLabel("이메일:");
		JTextField emailField = new JTextField(10);

		JButton addButton = new JButton("추가");
		JButton viewButton = new JButton("조회");

		// 입력 패널에 컴포넌트 추가
		inputPanel.add(idLabel);
		inputPanel.add(idField);
		inputPanel.add(passwordLabel);
		inputPanel.add(passwordField);
		inputPanel.add(emailLabel);
		inputPanel.add(emailField);
		inputPanel.add(addButton);
		inputPanel.add(viewButton);

		// 테이블 모델 및 테이블 생성
		String[] columnNames = { "ID", "비밀번호", "이메일" };
		tableModel = new DefaultTableModel(columnNames, 0);
		memberTable = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(memberTable);

		// 버튼 클릭 이벤트 처리
		// 회원 추가
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = idField.getText();
				String password = new String(passwordField.getPassword());
				String email = emailField.getText();

				Pkh0827_DTO_test newMember = new Pkh0827_DTO_test(id, password, email);
				int insertResult = dao.insertMember(newMember);
				if (insertResult > 0) {
					JOptionPane.showMessageDialog(frame, "회원이 성공적으로 추가되었습니다!");
				} else {
					JOptionPane.showMessageDialog(frame, "회원 추가에 실패했습니다.");
				}
				// 입력 필드 초기화
				idField.setText("");
				passwordField.setText("");
				emailField.setText("");
			}
		});

		// 회원 목록 조회
		viewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tableModel.setRowCount(0); // 기존 데이터 초기화
				ArrayList<Pkh0827_DTO_test> members = dao.selectAllMembers();
				for (Pkh0827_DTO_test member : members) {
					tableModel.addRow(new Object[] { member.getId(), member.getPassword(), member.getEmail() });
				}
			}
		});

		// 프레임에 컴포넌트 추가
		frame.add(inputPanel, BorderLayout.NORTH);
		frame.add(scrollPane, BorderLayout.CENTER);

		// 프레임 표시
		frame.setVisible(true);
	}
}
