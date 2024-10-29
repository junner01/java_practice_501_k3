package ex_241023_cha10;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MenuActionEventEx extends JFrame {
	private JLabel imgLabel = new JLabel(); // 빈 레이블
	private JLabel nameJLabel;
	

	public MenuActionEventEx() {
		setTitle("Menu에 Action 리스너 만들기 예제");
		createMenu();
		// 배치 관리자가 없으면, 기본이 BorderLayout, 동,서,남,북,가운데 중 하나 선택.
		getContentPane().add(imgLabel, BorderLayout.CENTER);
		// 입력 다이얼로그 ,
		// String name = JOptionPane.showInputDialog("이름을 입력하세요.");
		
		int result = JOptionPane.showConfirmDialog(null, "계속할 것입니까?",
				"Confirm", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.CLOSED_OPTION) {
				// 사용자가 "예", "아니오"의 선택 없이 다이얼로그 창을 닫은 경우
					nameJLabel = new JLabel("JOptionPane.CLOSED_OPTION : 선택" );
				}
				else if(result == JOptionPane.YES_OPTION) {
				// 사용자가 "예"를 선택한 경우
					nameJLabel = new JLabel("OptionPane.YES_OPTION : 선택" );
				}
				else if(result == JOptionPane.NO_OPTION) {
					// 사용자가 "예"를 선택한 경우
						nameJLabel = new JLabel("OptionPane.NO_OPTION : 선택" );
					}
				else {
				}
				
				JOptionPane.showMessageDialog(null,
						"조심하세요", "Message",
						JOptionPane.ERROR_MESSAGE);
		
		
		getContentPane().add(nameJLabel, BorderLayout.SOUTH);
		setSize(250, 200);
		setVisible(true);
	}

	public void createMenu() {
		JMenuBar mb = new JMenuBar(); // 메뉴바 생성

		// 하위 메뉴
		JMenuItem[] menuItem = new JMenuItem[4];
		String[] itemTitle = { "Load", "Hide", "ReShow", "Exit" };
		// 메인 메뉴 
		JMenu screenMenu = new JMenu("Screen");
		
		// 이벤트 처리기. 내부에 클래스 만듦.  
		MenuActionListener listener = new MenuActionListener();
		
		for (int i = 0; i < menuItem.length; i++) {
			menuItem[i] = new JMenuItem(itemTitle[i]);
			menuItem[i].addActionListener(listener);
			screenMenu.add(menuItem[i]);
		}
		mb.add(screenMenu);
		setJMenuBar(mb); // 메뉴바를 프레임에 부착
	}

	class MenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// 메인 메뉴에 있는 이름들. 
			String cmd = e.getActionCommand();
			switch (cmd) { // 메뉴 아이템의 종류 구분
			case "Load":
				// 이미지 라벨일 처음에는 널임. 
				// 널 : false , 반대 : true.
				if (imgLabel.getIcon() != null)
					return;
// 이미 로딩되었으면 리턴
				imgLabel.setIcon(new ImageIcon("images/test11.png"));
				break;
			case "Hide":
				imgLabel.setVisible(false);
				break;
			case "ReShow":
				imgLabel.setVisible(true);
				break;
			case "Exit":
				System.exit(0);
				break;
			}
		}
	}

	public static void main(String[] args) {
		new MenuActionEventEx();
	}
}
