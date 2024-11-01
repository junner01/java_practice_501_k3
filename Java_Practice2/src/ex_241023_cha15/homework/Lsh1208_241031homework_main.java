package ex_241023_cha15.homework;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

public class Lsh1208_241031homework_main extends JFrame {

	private JTextField name;
	private JTextField email;
	private JTextField password;
	private JList<String> listbox;
	private DefaultListModel<String> listModel;

	Lsh1208_homework241031_dao dao = new Lsh1208_homework241031_dao();

	public Lsh1208_241031homework_main() {
		setTitle("회원 정보");
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(6, 1));

		name = new JTextField(10);
		inputPanel.add(name);

		email = new JTextField(10);
		inputPanel.add(email);

		password = new JTextField(10);
		inputPanel.add(password);

		JButton insertbtn = new JButton("저장");
		inputPanel.add(insertbtn);
		JButton selectbtn = new JButton("조회");
		inputPanel.add(selectbtn);
		JButton deletebtn = new JButton("삭제");
		inputPanel.add(deletebtn);

		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BorderLayout());

		listModel = new DefaultListModel<>();
		listbox = new JList<>(listModel);
		listPanel.add(new JLabel("회원 리스트"), BorderLayout.NORTH);
		listPanel.add(new JScrollPane(listbox), BorderLayout.CENTER);

		add(inputPanel, BorderLayout.WEST);
		add(listPanel, BorderLayout.CENTER);

		insertbtn.addActionListener(e -> insertUser());
		selectbtn.addActionListener(e -> selectUser());
		deletebtn.addActionListener(e -> deleteUser());

		setVisible(true);
	}

	public void selectUser() {
		ArrayList<Lsh1208_homework241031_dto> list = dao.select();
		listModel.clear();
		for (Lsh1208_homework241031_dto dto : list) {
			listModel.addElement(dto.getName());
		}
	}

	public void insertUser() {
		if (!name.getText().isEmpty() && !email.getText().isEmpty() && !password.getText().isEmpty()) {
			int result = dao.insert(name.getText(), email.getText(), password.getText());
			name.setText("");
			email.setText("");
			password.setText("");
			selectUser();
		} else if (name.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "이름을 입력하세요.");
		} else if (email.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "메일을 입력하세요.");
		} else if (password.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "암호를 입력하세요.");
		}

	}

	public void deleteUser() {
		// 선택된 항목 가져오기
		int selectedIndex = listbox.getSelectedIndex();
		if (selectedIndex == -1) {
			JOptionPane.showMessageDialog(null, "삭제할 유저를 선택하세요.");
			return;
		}

		// 선택된 유저의 ID를 가져오기
		String selectedName = listModel.getElementAt(selectedIndex);
		// DTO 리스트에서 선택된 유저의 ID 찾기
		ArrayList<Lsh1208_homework241031_dto> list = dao.select();
		int userId = -1; // 기본값으로 -1 설정

		for (Lsh1208_homework241031_dto dto : list) {
			if (dto.getName().equals(selectedName)) {
				userId = dto.getId();
				break;
			}
		}

		// 유저 삭제
		if (userId != -1) {
			int confirm = JOptionPane.showConfirmDialog(null, "정말로 " + selectedName + "을(를) 삭제하시겠습니까?", "삭제 확인",
					JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				dao.delete(userId); // 삭제 메서드 호출
				selectUser(); // 리스트 갱신
			}
		} else {
			JOptionPane.showMessageDialog(null, "유저 정보를 찾을 수 없습니다.");
		}
	}

	public static void main(String[] args) {
		new Lsh1208_241031homework_main();
	}
}
