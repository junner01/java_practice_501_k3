package ex_241023_cha15.homework;

import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Jih0316DB extends JFrame {
	private JTextField IDField;
	private JTextField pwField;
	private JTextField EmailField;
	private JTable memberTable;
	private DefaultTableModel tableModel;
	private Jih0316DB_DAO dao;

	public Jih0316DB() {
		dao = new Jih0316DB_DAO();
		setTitle("회원 관리 프로그램 v 1.0.0");
		setSize(850, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new FlowLayout());

		inputPanel.add(new JLabel("ID : "));
		IDField = new JTextField(8);
		inputPanel.add(IDField);

		inputPanel.add(new JLabel("Password : "));
		pwField = new JTextField(10);
		inputPanel.add(pwField);

		inputPanel.add(new JLabel("E-mail : "));
		EmailField = new JTextField(10);
		inputPanel.add(EmailField);

		JButton addButton = new JButton("추가");
		inputPanel.add(addButton);
		JButton loadButton = new JButton("조회");
		inputPanel.add(loadButton);
		JButton resetButton = new JButton("초기화");
		inputPanel.add(resetButton);

		// 테이블 모델 및 JTable 생성
		String[] columnNames = { "ID", "Password", "E-mail" };
		tableModel = new DefaultTableModel(columnNames, 0);
		memberTable = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(memberTable);

		// MembershipPanel에 테이블 추가
		JPanel MembershipPanel = new JPanel(new BorderLayout());
		MembershipPanel.add(scrollPane, BorderLayout.CENTER);

		add(inputPanel, BorderLayout.NORTH);
		add(MembershipPanel, BorderLayout.CENTER);

		addButton.addActionListener(e -> addMember());
		loadButton.addActionListener(e -> loadMembers());
		resetButton.addActionListener(e -> resetDatabase());

		setVisible(true);
		
	}

	private void addMember() {
		String id = IDField.getText();
		String pw = pwField.getText();
		String email = EmailField.getText();

		if (!id.isEmpty() && !pw.isEmpty() && !email.isEmpty()) {
			try {
				Jih0316DB_DTO member = new Jih0316DB_DTO(id, pw, email);
				dao.addMember(member);

				// 테이블 초기화 후 추가된 데이터만 표시
				tableModel.setRowCount(0);
				tableModel.addRow(new Object[] { id, pw, email });

				// 입력 필드 초기화
				IDField.setText("");
				pwField.setText("");
				EmailField.setText("");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "데이터베이스 오류: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "모든 필드를 입력해주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void loadMembers() {
		// 기존 데이터 지우고 모든 데이터 로드
		tableModel.setRowCount(0);
		try {
			List<Jih0316DB_DTO> members = dao.loadMembers();
			for (Jih0316DB_DTO member : members) {
				tableModel.addRow(new Object[] { member.getId(), member.getPassword(), member.getEmail() });
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "데이터베이스 오류: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void resetDatabase() {
		int confirm = JOptionPane.showConfirmDialog(null, "모든 데이터를 삭제하시겠습니까?", "초기화 확인", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			try {
				dao.resetDatabase();
				tableModel.setRowCount(0);
				JOptionPane.showMessageDialog(null, "모든 데이터가 삭제되었습니다.", "초기화 완료", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "데이터베이스 오류: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public static void main(String[] args) {
		new Jih0316DB();
	}
}
