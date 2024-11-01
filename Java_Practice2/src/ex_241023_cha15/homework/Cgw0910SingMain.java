package ex_241023_cha15.homework;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Cgw0910SingMain extends JFrame {
	private JPanel flowPanel;
	private JPanel inputIdPanel;
	private JPanel inputPasswordPanel;
	private JPanel inputEmailPanel;
	private JPanel boxPanel;
	private JTextField idField;
	private JTextField passwordField;
	private JTextField emailField;
	private JButton inputButton;
	private JButton getButton;
	private JButton tableDelete;
	private Cgw0910OracleClass oracleClass;
	private JPanel buttonPanel;

	public Cgw0910SingMain() {
		oracleClass = new Cgw0910OracleClass();

		setTitle("SignUp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 150);
		setLayout(new BorderLayout());

		flowPanel = new JPanel(new FlowLayout());
		boxPanel = new JPanel();
		boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

		inputIdPanel = new JPanel(new FlowLayout());
		inputPasswordPanel = new JPanel(new FlowLayout());
		inputEmailPanel = new JPanel(new FlowLayout());

		flowPanel.add(boxPanel);
		buttonPanel.add(inputButton = new JButton("입력:"));
		buttonPanel.add(getButton = new JButton("출력:"));
		buttonPanel.add(tableDelete = new JButton("데이터 테이블 삭제"));
		flowPanel.add(buttonPanel);

		inputEmailPanel.add(new JLabel("EMAIL          "));
		emailField = new JTextField(15);
		inputEmailPanel.add(emailField);

		inputIdPanel.add(new JLabel("ID                 "));
		idField = new JTextField(15);
		inputIdPanel.add(idField);

		inputPasswordPanel.add(new JLabel("PASSWORD"));
		passwordField = new JTextField(15);
		inputPasswordPanel.add(passwordField);

		boxPanel.add(inputIdPanel);
		boxPanel.add(inputPasswordPanel);
		boxPanel.add(inputEmailPanel);

		add(flowPanel, BorderLayout.CENTER);

		// 입력 버튼 리스너
		inputButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = idField.getText().trim();
				String password = passwordField.getText().trim();
				String email = emailField.getText().trim();

				if (id.isEmpty() || password.isEmpty() || email.isEmpty()) {
					JOptionPane.showMessageDialog(null, "빈칸을 모두 입력해주세요.");
					return;
				}

				Cgw0910UserData userData = oracleClass.searchOracle(id);
				if (userData == null) {
					userData = new Cgw0910UserData(password, email);
					oracleClass.insertOracle(id, userData);
					JOptionPane.showMessageDialog(null, "등록이 완료되었습니다!");
				} else {
					JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.");
					return;
				}

				idField.setText("");
				passwordField.setText("");
				emailField.setText("");
			}
		});

		// 출력 버튼 리스너
		getButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new OutPutFrame();
			}
		});
		tableDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				oracleClass.deleteTable();
				System.exit(0);
			}
		});

		setVisible(true);
	}

	// OutPutFrame 클래스 정의
	public class OutPutFrame extends JFrame {
		private JPanel flowJPanel;
		private JPanel searchJPanel;
		private JTextField searchField;
		private JButton searchButton;
		private JButton clearButton;
		private JButton deleteButton;
		private JLabel outPutLabel;
		private boolean isData = false;
		private String searchId;

		public OutPutFrame() {
			setTitle("search");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 창 닫기 설정 변경
			setSize(500, 150);
			setLayout(new BorderLayout());

			searchJPanel = new JPanel(new FlowLayout());
			searchField = new JTextField(15);
			searchButton = new JButton("검색");

			searchJPanel.add(new JLabel("검색 ID:"));
			searchJPanel.add(searchField);
			searchJPanel.add(searchButton);
			searchJPanel.add(clearButton = new JButton("데이터 클리어"));
			searchJPanel.add(deleteButton = new JButton("삭제"));

			add(searchJPanel, BorderLayout.NORTH);
			outPutLabel = new JLabel();
			add(outPutLabel, BorderLayout.CENTER);

			// 검색 버튼에 ActionListener 추가
			searchButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					searchId = searchField.getText().trim();
					if (searchId.isEmpty()) {
						JOptionPane.showMessageDialog(null, "ID를 입력하세요.");
						return;
					}

					Cgw0910UserData userData = oracleClass.searchOracle(searchId);
					if (userData != null) {
						String userInfo = "ID: " + searchId + "      " + "Password: " + userData.getUserPassword()
								+ "      " + "Email: " + userData.getUserEmail();
						outPutLabel.setText(userInfo);
						revalidate();
						repaint();
						isData = true;
					} else {
						JOptionPane.showMessageDialog(null, "해당 ID의 데이터가 없습니다.");
					}
					searchField.setText("");
				}
			});
			clearButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					oracleClass.clearTableData();
				}
			});
			deleteButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (isData && searchId != null) {
						oracleClass.deleteData(searchId);
						outPutLabel.setText("");
						isData = false;
					}
					else {
						JOptionPane.showMessageDialog(null, "데이터가 선택되지 않았습니다.");
					}
				}
			});

			setVisible(true);
		}
	}

	public static void main(String[] args) {
		new Cgw0910SingMain();
	}
}