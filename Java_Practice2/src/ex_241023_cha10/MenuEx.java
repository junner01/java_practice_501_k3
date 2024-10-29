package ex_241023_cha10;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuEx extends JFrame {

	public MenuEx() {
		setTitle("Menu 만들기 예제");
		createMenu(); // 메뉴 생성, 프레임에 삽입
		setSize(250, 200);
		setVisible(true);
	}

	public void createMenu() {
		// 1 메뉴바
		JMenuBar mb = new JMenuBar();
		// 2 메인 메뉴
		JMenu screenMenu = new JMenu("Screen");
		// 메인 메뉴 -> 3 하위 메뉴
		screenMenu.add(new JMenuItem("Load"));
		screenMenu.add(new JMenuItem("Hide"));
		screenMenu.add(new JMenuItem("ReShow"));
		// 4 구분선 addSeparator
		screenMenu.addSeparator();
		// 메인 메뉴 -> 하위 메뉴
		screenMenu.add(new JMenuItem("Exit"));
		// 메뉴바에, 메인 메뉴 붙이기.
		mb.add(screenMenu);
		// 또 다른 메인 메뉴.
		// 메인 메뉴2
		JMenu screenMenu2 = new JMenu("Edit");
		screenMenu2.add(new JMenuItem("중국집"));
		screenMenu2.add(new JMenuItem("고깃집"));
		mb.add(screenMenu2);
		mb.add(new JMenu("Source"));
		mb.add(new JMenu("Project"));
		mb.add(new JMenu("Run"));
		// 프레임(윈도우창, 메뉴바 붙이기)
		setJMenuBar(mb);
	}

	public static void main(String[] args) {
		new MenuEx();
	}
}
