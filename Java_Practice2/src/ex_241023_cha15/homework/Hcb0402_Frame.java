package ex_241023_cha15.homework;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Hcb0402_Frame {
	// component 선언
	private static JPanel centerPanel = new JPanel();
	private static JPanel westPanelInside = new JPanel();
	private static JScrollPane westPanel = new JScrollPane(westPanelInside);
	private static JPanel northPanel = new JPanel();
	private static JButton cButton = new JButton("create");
	private static JButton rButton = new JButton("read");
	private static JButton dButton = new JButton("delete");
	private static JButton uButton = new JButton("update");
	private static TextField name = new TextField(10);
	private static TextField email = new TextField(10);
	private static TextField pass = new TextField(10);
	private static int selectedId;
	private static String selectedName;
	private static String selectedEmail;
	private static String selectedPassword;

	// constructor
	public Hcb0402_Frame() {
		JFrame exFrame = new JFrame();

		// frame property
		exFrame.setTitle("GridLayout 예제");
		exFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		exFrame.setSize(800, 300);
		exFrame.setLayout(new BorderLayout());

		// panel을 frame에 추가
		exFrame.add(northPanel, BorderLayout.NORTH);
		exFrame.add(centerPanel, BorderLayout.CENTER);
		exFrame.add(westPanel, BorderLayout.WEST);

		// west panel의 property
		westPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		westPanel.setPreferredSize(new Dimension(200, 300));

		// west panel inside의 property
		westPanelInside.setLayout(new BoxLayout(westPanelInside, BoxLayout.Y_AXIS));

		// northpanel에 들어갈 button의 이벤트 리스너
		cButton.addActionListener(e -> create());
		rButton.addActionListener(e -> read());
		dButton.addActionListener(e -> delete());
		uButton.addActionListener(e -> update());

		// 추가 panel 에 add
		northPanel.add(new JLabel("이름: "));
		northPanel.add(name);
		northPanel.add(new JLabel("이메일: "));
		northPanel.add(email);
		northPanel.add(new JLabel("비밀번호: "));
		northPanel.add(pass);
		northPanel.add(cButton);
		northPanel.add(rButton);
		northPanel.add(dButton);
		northPanel.add(uButton);

		// 끝
		exFrame.setVisible(true);
	}

	// method
	// read method
	private void read() {
		westPanelInside.removeAll();
		centerPanel.removeAll();
		Hcb0402_DAO dao = new Hcb0402_DAO();
		// JDBC_Ex_DAO 클래스를 사용하기 위해서 객체 생성.

		ArrayList<Hcb0402_DTO> list = dao.select();
		// JDBC_Ex_DAO 클래스의 select 메소드 호출.
		// 결과값으로는 dept 테이블의 모든 레코드를 저장하고 있는
		// ArrayList 객체를 리턴 받는다.

		for (Hcb0402_DTO dto : list) {
			int id = dto.getId();
			String name = dto.getName();
			String email = dto.getEmail();
			String password = dto.getPassword();
			JLabel idName = new JLabel(String.valueOf(id) + "." + name);
			idName.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					centerPanel.removeAll();
					JLabel centerLabel = new JLabel("이메일: " + email + " 비밀번호: " + password, SwingConstants.CENTER);
					centerLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
					centerPanel.add(centerLabel);
					selectedId = id;
					selectedName = name;
					selectedEmail = email;
					selectedPassword = password;
					centerPanel.revalidate();
					centerPanel.repaint();
				}
			});
			westPanelInside.add(idName);
		}
		westPanelInside.revalidate();
		westPanelInside.repaint();
		centerPanel.revalidate();
		centerPanel.repaint();
		selectedId = 0;
		selectedName = null;
		selectedEmail = null;
		selectedPassword = null;
	}

	// create method
	private void create() {
		if (!name.getText().isEmpty() && !email.getText().isEmpty() && !pass.getText().isEmpty()) {
			Hcb0402_DTO member = new Hcb0402_DTO(name.getText(), email.getText(), pass.getText());
			new Hcb0402_DAO().insert(member);
			read();
		} else {
			JOptionPane.showMessageDialog(null, "이름과 이메일 비밀번호를 모두 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
		}
		name.setText("");
		email.setText("");
		pass.setText("");
	}

	// delete method
private void delete() {
		if (!name.getText().isEmpty()) {
			new Hcb0402_DAO().delete(name.getText());
			read();
		} else if (selectedId != 0) {
			new Hcb0402_DAO().delete(selectedId);
			read();
		}
		else {
			JOptionPane.showMessageDialog(null, "삭제하고 싶은 이름을 입력해 주세요", "입력 오류", JOptionPane.ERROR_MESSAGE);
		}
		name.setText("");
		email.setText("");
		pass.setText("");
	}

	private void update() {
		if (!name.getText().isEmpty() && !email.getText().isEmpty() && !pass.getText().isEmpty() && selectedId != 0) {
			new Hcb0402_DAO().update(selectedId, name.getText(), email.getText(), pass.getText());
			read();
		} else if (!name.getText().isEmpty() && !email.getText().isEmpty()) {
			new Hcb0402_DAO().update(selectedId, name.getText(), email.getText(), selectedPassword);
		} else if (!name.getText().isEmpty() && !pass.getText().isEmpty()) {
			new Hcb0402_DAO().update(selectedId, name.getText(), selectedEmail, pass.getText());
		} else if (!email.getText().isEmpty() && !pass.getText().isEmpty()) {
			new Hcb0402_DAO().update(selectedId, selectedName, email.getText(), pass.getText());
		} else if (!name.getText().isEmpty()) {
			new Hcb0402_DAO().update(selectedId, name.getText(), selectedEmail, selectedPassword);
		} else if (!email.getText().isEmpty()) {
			new Hcb0402_DAO().update(selectedId, selectedName, email.getText(), selectedPassword);
		} else if (!pass.getText().isEmpty()) {
			new Hcb0402_DAO().update(selectedId, selectedName, selectedEmail, pass.getText());
		} else {
			JOptionPane.showMessageDialog(null, "수정하려는 인물을 선택하고 이믈이나 이메일, 비밀번호를 입력해 주세요", "수정 오류", JOptionPane.ERROR_MESSAGE);
		}
		read();
		name.setText("");
		email.setText("");
		pass.setText("");
	}

	public static void main(String[] args) {
		new Hcb0402_Frame();
	}
}
