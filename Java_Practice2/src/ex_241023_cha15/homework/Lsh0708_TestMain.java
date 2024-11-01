package ex_241023_cha15.homework;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Lsh0708_TestMain extends JFrame {

	private JTextField idField;
	private JTextField passField;
	private JTextField emailField;
	private JPanel userPanel; // 단어 표시할 필드
	Lsh0708_DAO dao = new Lsh0708_DAO();

	public Lsh0708_TestMain() {

		setTitle("회원가입 프로그램 V 1.0.0");
		// 창 사이즈. 기본 크기.
		setSize(800, 300);
		// 창의 닫기를 클릭시, 정상 종료.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new BorderLayout());
		
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new FlowLayout());
		
		userPanel = new JPanel();
		userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));

		inputPanel.add(new JLabel("아이디 : "));
		idField = new JTextField(10);
		inputPanel.add(idField);

		inputPanel.add(new JLabel("비밀번호: "));
		passField = new JTextField(10);
		inputPanel.add(passField);

		inputPanel.add(new JLabel("이메일: "));
		emailField = new JTextField(20);
		inputPanel.add(emailField);

		JButton addButton = new JButton("가입하기");
		inputPanel.add(addButton);

		JButton serchButton = new JButton("조회");
		inputPanel.add(serchButton);

		add(inputPanel, BorderLayout.NORTH);
		add(userPanel, BorderLayout.CENTER);

		addButton.addActionListener(e -> addUser());

		serchButton.addActionListener(e -> searchUser());

		setVisible(true);

	}

	private void addUser() {
		String userID = idField.getText();
		String userPass = passField.getText();
		String userEmail = emailField.getText();

		if (userID.isEmpty() || userPass.isEmpty() || userEmail.isEmpty()) {
			System.out.println("모든 필드를 입력하세요.");
			JOptionPane.showMessageDialog(null, "모든 필드를 입력하세요.", "모든 필드를 입력하세요.", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int result = dao.insertDB(userID, userEmail, userPass);
		System.out.println(userID + userPass + userEmail);
		System.out.println("DAO 에서 insert 기능 추가 후 : 값반환:" + result + "개 추가됨.");
		idField.setText("");
		passField.setText("");
		emailField.setText("");

	}

	private void searchUser() {
		ArrayList<Lsh0708_DTO> list = dao.select();
		  userPanel.removeAll();
		  int count = 1;
		for (Lsh0708_DTO dto : list) {
			JLabel userLabel = new JLabel(count + ". 아이디: "+ dto.getUserId()+", 이메일: "+ dto.getUserMail()+", 패스워드 :"+dto.getUserPass());
			userPanel.add(userLabel);
			String name = dto.getUserId();
			String email = dto.getUserMail();
			String pass = dto.getUserPass();
			System.out.println(name + "\t" + email + "\t" + pass);
			count++;
			
		} // for ~ each 문을 이용해서 ArrayList 에 저장된 레코드의 값을 출력한다.
		userPanel.revalidate(); // Refresh the userPanel
		userPanel.repaint(); // Update the GUI
		

	}

	public static void main(String[] args) {

		new Lsh0708_TestMain();
	}

}
