package ex_241023_cha15.homework.lsy1205Test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class LsyTest1205Main extends JFrame {

	private JTextField nameField;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JPasswordField passwordConfirmField;

	private DefaultListModel<String> memberListModel = new DefaultListModel<>();
	// JList UI 표시.
	private final JList<String> memberList = new JList<>(memberListModel);

	private JButton joinBtn;
	private JButton updateBtn;
	// 선언만 했음.
	private JButton deleteBtn;
	private JButton clearBtn;

	LsyTest1205DAO dao = null;

	public LsyTest1205Main() {

		setTitle("회원관리 프로그램 V 1.0.0");
		setSize(1000, 300);
		setLocationRelativeTo(null); // 화면 가운데 정렬
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		dao = new LsyTest1205DAO();
		setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new FlowLayout());

		inputPanel.add(new JLabel("name: "));
		// 2. 입력 공간 붙이기.
		// 이름
		nameField = new JTextField(10);
		inputPanel.add(nameField);
		// 이메일
		inputPanel.add(new JLabel("email: "));
		emailField = new JTextField(10);
		inputPanel.add(emailField);

		// password
		inputPanel.add(new JLabel("password: "));
		passwordField = new JPasswordField(10);
		inputPanel.add(passwordField);

		// password confirm
		inputPanel.add(new JLabel("password confirm: "));
		passwordConfirmField = new JPasswordField(10);
		inputPanel.add(passwordConfirmField);

		// 회원 추가하는 버튼
		joinBtn = new JButton("회원추가");
		inputPanel.add(joinBtn);

		// ==================================================
		add(inputPanel, BorderLayout.NORTH);

		memberList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		memberList.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) { // 선택이 완전히 끝났을 때만 실행
				String selectedWord = memberList.getSelectedValue();
				if (selectedWord != null) {
//					System.out.println("selectedWord : " + selectedWord);
					String[] parts = selectedWord.split(","); // 쉼표를 기준으로 분리
					String idPart = parts[0].trim(); // "id=26" 부분 선택
					String idValue = idPart.split("=")[1].trim(); // "="을 기준으로 분리하여 숫자만 추출
					int id = Integer.parseInt(idValue); // 숫자로 변환
//					System.out.println("ID 값: " + id);

					LsyTest1205DTO member = dao.selectMemberById(id); // ID가 1인 회원 정보 조회
//					System.out.println("member 확인 : " + member.toString());
					// JFrame 설정, 상세보기 창 전환
					newFrame(member);
				}

			}

		});
		JScrollPane scrollPane = new JScrollPane(memberList);
		add(scrollPane, BorderLayout.CENTER);

		joinBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String email = emailField.getText();
				String password = passwordField.getText();
				String passwordConfirm = passwordConfirmField.getText();

				LsyTest1205DTO memberLsyTest1205DTO = dao.selectMemberByEmail(email);
				// 이메일 중복 여부
				if (memberLsyTest1205DTO == null) {

					// 패스워드 일치 여부 확인
					if (password.equals(passwordConfirm)) {
						LsyTest1205DTO dto = new LsyTest1205DTO(name, email, password);
						dao.insertMember(dto);
						refreshTable();
//					initField();

					} else {
						// 경고 메시지 출력
						JOptionPane.showMessageDialog(null, "패스워드가 일치하지 않습니다. 다시 확인해주세요.", "경고",
								JOptionPane.WARNING_MESSAGE);
					}
				} // 이메일 중복 여부
				else {
					// 경고 메시지 출력
					JOptionPane.showMessageDialog(null, "이메일 중복입니다. 다시 확인해주세요.", "경고",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		}); // joinBtn

		refreshTable();

		setVisible(true);

	}
	// UI 기능들

	public void refreshTable() {
		memberListModel.clear();
		ArrayList<LsyTest1205DTO> resultList = dao.selectAllMember();
		for (LsyTest1205DTO lsyTest1205DTO : resultList) {
			memberListModel.addElement(lsyTest1205DTO.toString());
		}

	}

	public void initField() {
		nameField.setText("");
		emailField.setText("");
		passwordField.setText("");
		passwordConfirmField.setText("");

	}

	// 상세보기 창
	public void newFrame(LsyTest1205DTO member) {
		// JFrame 설정
		JFrame frame = new JFrame("회원 정보");
		frame.setSize(400, 300);
		frame.dispose();
		frame.setLayout(new GridLayout(6, 2, 10, 10)); // 6행 2열의 레이아웃

		// JLabel과 JTextField 설정
		JLabel idLabel = new JLabel("아이디:");
		JTextField idField = new JTextField();
		idField.setEditable(false); // 수정 불가능하게 설정

		JLabel nameLabel = new JLabel("이름:");
		JTextField nameField = new JTextField();

		JLabel emailLabel = new JLabel("이메일:");
		JTextField emailField = new JTextField();

		JLabel passwordLabel = new JLabel("비밀번호:");
		JPasswordField passwordField = new JPasswordField();

		JLabel passwordConfirmLabel = new JLabel("비밀번호 확인:");
		JPasswordField passwordConfirmField = new JPasswordField();

		JButton updateBtn = new JButton("수정하기");
		JButton deleteBtn = new JButton("삭제하기");

		// 데이터가 있는 경우 각 JTextField에 정보 설정
		if (member != null) {
			idField.setText(String.valueOf(member.getId()));
			nameField.setText(member.getName());
			emailField.setText(member.getEmail());
			passwordField.setText(member.getPassword());

			// 삭제
			deleteBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int response = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?", "삭제 확인",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

					// 사용자가 "예"를 선택한 경우에만 삭제 메서드 실행
					if (response == JOptionPane.YES_OPTION) {
						if (passwordField.getText().equals(passwordConfirmField.getText())) {
							dao.deleteMember(member.getId()); // 삭제 메서드 호출
							frame.dispose();
							// JList 데이터 리프레시
							refreshTable();
						} else {
							JOptionPane.showMessageDialog(null, "패스워드가 일치하지 않습니다. 다시 확인해주세요.", "경고",
									JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			});
			// 삭제
			// 수정
			updateBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int response = JOptionPane.showConfirmDialog(null, "정말 수정하시겠습니까?", "수정 확인",
							JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

					// 사용자가 "예"를 선택한 경우에만 삭제 메서드 실행
					if (response == JOptionPane.YES_OPTION) {
						if (passwordField.getText().equals(passwordConfirmField.getText())) {
							dao.updateMember(member.getId(), nameField.getText(), emailField.getText(),
									passwordField.getText()); // 수정 메서드 호출
							frame.dispose();
							// JList 데이터 리프레시
							refreshTable();
						} else {
							JOptionPane.showMessageDialog(null, "패스워드가 일치하지 않습니다. 다시 확인해주세요.", "경고",
									JOptionPane.WARNING_MESSAGE);
						}

					}
				}
			});
			// 수정
		} else {
			// 회원 정보가 없는 경우
			idField.setText("회원 정보를 찾을 수 없습니다.");
		}

		// 프레임에 구성 요소 추가
		frame.add(idLabel);
		frame.add(idField);
		frame.add(nameLabel);
		frame.add(nameField);
		frame.add(emailLabel);
		frame.add(emailField);
		frame.add(passwordLabel);
		frame.add(passwordField);
		frame.add(passwordConfirmLabel);
		frame.add(passwordConfirmField);
		frame.add(updateBtn);
		frame.add(deleteBtn);

		// 프레임 보이기
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		LsyTest1205Splash splashScreen = new LsyTest1205Splash();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				splashScreen.closeSplash(); // 스플래시 화면 닫기
				new LsyTest1205Main(); // 메인 화면 실행
			}
		}, 2000); // 2초 (2000 밀리초) 후 실행
	} // main
}
