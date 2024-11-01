package ex_241023_cha15.homework;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Kjy0227_DBTest extends JFrame {
	private JTextField nameField;
	private JTextField emailField;
	private JTextField pwField;
	private JPanel showPanel;
	
	private List<Kjy0227_MemberDTO> members;
	
	private Kjy0227_MemberDAO memberDAO = new Kjy0227_MemberDAO();
	
	public Kjy0227_DBTest() {
		setTitle("회원 관리 프로그램");
		setSize(800, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		members = new ArrayList<Kjy0227_MemberDTO>();
		
		JPanel inputPanel = new JPanel();
		setLayout(new BorderLayout());
		
		// 입력창
		inputPanel.add(new JLabel("이름: "));
		nameField = new JTextField(10);
		inputPanel.add(nameField);
		inputPanel.add(new JLabel("이메일: "));
		emailField = new JTextField(20);
		inputPanel.add(emailField);
		inputPanel.add(new JLabel("비밀번호: "));
		pwField = new JTextField(10);
		inputPanel.add(pwField);
		
		// 버튼
		JButton addButton = new JButton("가입");
		inputPanel.add(addButton);
		JButton selButton = new JButton("조회");
		inputPanel.add(selButton);
		
		showPanel = new JPanel();
		showPanel.setLayout(new BoxLayout(showPanel, BoxLayout.Y_AXIS));
		
		add(inputPanel,BorderLayout.NORTH);
		add(showPanel,BorderLayout.CENTER);
		
		addButton.addActionListener(e -> addMember());
		selButton.addActionListener(e -> selMember());
		
		setVisible(true);
	}
	
	// 가입
	public void addMember() {
		String name = nameField.getText();
		String email = emailField.getText();
		String password = pwField.getText();
		
		if(name.isEmpty() || email.isEmpty() || password.isEmpty()) {
			JOptionPane.showMessageDialog(null, "이름, 이메일, 비밀번호를 모두 입력해주세요.",
					"입력 오류", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(memberDAO.insertMember(name, email, password) > 0) {
			JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.",
					"가입 성공", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "회원가입에 실패했습니다.",
					"저장 오류", JOptionPane.ERROR_MESSAGE);
		}
		
		showPanel.removeAll(); // 회원목록 초기화
		showPanel.revalidate();
		showPanel.repaint();
	}
	
	// 조회
	public void selMember() {
		showPanel.removeAll(); // 회원목록 초기화
		members = memberDAO.selectMember();
		
		for (Kjy0227_MemberDTO member : members) {
			JLabel memberLabel = new JLabel("이름 : " + member.getName() + ", 이메일 : " + member.getEmail());
			showPanel.add(memberLabel);
		}
		
		showPanel.revalidate();
		showPanel.repaint();
	}

	
	
	public static void main(String[] args) {
		new Kjy0227_DBTest();
	}

}
