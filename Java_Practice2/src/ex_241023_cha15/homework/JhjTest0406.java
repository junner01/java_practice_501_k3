package ex_241023_cha15.homework;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class JhjTest0406 extends JFrame {

	private JTextField nameField;
	private JTextField emailField;
	private JTextField passwordField;
//	private JPanel wordPanel;
	private DefaultTableModel tableModel;
	private JhjDAO0406 dao;
//		private Random random;

	public JhjTest0406() {

		setTitle("회원관리프로그램");
		setSize(700, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		dao = new JhjDAO0406();

		setLayout(new BorderLayout());
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new FlowLayout());
//--------------------------------------------------------------------
		inputPanel.add(new JLabel("이름: "));
		nameField = new JTextField(10);
		inputPanel.add(nameField);

		inputPanel.add(new JLabel("이메일: "));
		emailField = new JTextField(10);
		inputPanel.add(emailField);

		inputPanel.add(new JLabel("패스워드: "));
		passwordField = new JTextField(10);
		inputPanel.add(passwordField);
//--------------------------------------------------------------------
		JButton saveButton = new JButton("추가");
		JButton roadButton = new JButton("조회");

		inputPanel.add(saveButton);
		inputPanel.add(roadButton);
//--------------------------------------------------------------------
		String[] columnNames = { "이름", "이메일", "패스워드" };
		tableModel = new DefaultTableModel(columnNames, 0);
		JTable table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
// --------------------------------------------------------------------
//
//		wordPanel = new JPanel();
//		wordPanel.setLayout(new FlowLayout());

		add(inputPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
// --------------------------------------------------------------------

		saveButton.addActionListener(e -> saveMember());
		roadButton.addActionListener(e -> loadMembers());

		setVisible(true);
	}

	private void saveMember() {
		String name = nameField.getText();
		String email = emailField.getText();
		String password = passwordField.getText();

		if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
			JHJDTO0406 dto = new JHJDTO0406();
			dto.setName(name);
			dto.setEmail(email);
			dto.setPassword(password);
			

			int result = dao.insertDB(dto); // 데이터베이스에 회원 정보 저장
			if (result > 0) {
				JOptionPane.showMessageDialog(this, "회원 정보가 성공적으로 저장되었습니다.");
				nameField.setText("");
				emailField.setText("");
				passwordField.setText("");
			} else {
				JOptionPane.showMessageDialog(this, "회원 정보 저장에 실패했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "모든 필드를 입력해주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void loadMembers() {
		ArrayList<JHJDTO0406> memberList = dao.select(); // 데이터베이스에서 회원 정보 조회
        tableModel.setRowCount(0); // 기존 테이블 데이터 초기화
        for (JHJDTO0406 member : memberList) {
            Object[] row = { member.getName(), member.getEmail(), member.getPassword() };
            tableModel.addRow(row); // 테이블에 행 추가
        }
    }

	public static void main(String[] args) {
		new JhjTest0406();
	}

//	
//	public static void main(String[] args) {
//
//		// 디비 서버에서 crud 관련 기능 모음집. 
//		// 현재, 1개 select , 
//		test_DAO dao = new test_DAO();
//		// JDBC_Ex_DAO 클래스를 사용하기 위해서 객체 생성.
//		
//		// DAO, 2) insert 기능 추가해서, 
//		// 여기에서 이용해보기. 
//		int result = dao.insertDB(99,"더미부서1","부산진구1");
//		System.out.println("DAO 에서 insert 기능 추가 후 : 값반환:" + result +"개 추가됨.");
//		
//
//		ArrayList<test_DTO> list = dao.select();
//		// JDBC_Ex_DAO 클래스의 select 메소드 호출.
//		// 결과값으로는 dept 테이블의 모든 레코드를 저장하고 있는
//		// ArrayList 객체를 리턴 받는다.
//
//	
//		for (test_DTO dto : list) {
//			int deptno = dto.getDeptno();
//			String dname = dto.getDname();
//			String loc = dto.getLoc();
//			System.out.println(deptno + "\t" + dname + "\t" + loc);
//		} // for ~ each 문을 이용해서 ArrayList 에 저장된 레코드의 값을 출력한다.
//
//	}
}
