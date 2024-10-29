package ex_241023_cha11.upgradeImageBoard;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class ch13_GraphicsDrawLineMouseEx3 extends JFrame {
	ch13_ToolBar toolbar = new ch13_ToolBar();
	public ch13_GraphicsDrawLineMouseEx3() {
		setTitle("버튼 클릭, 선,타원, 사각형,그리기 응용 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1440,800);
		add(toolbar, BorderLayout.NORTH);
		add(toolbar.buttons.canvas);
		//setContentPane(new MyPanel());
		setVisible(true);
	}

	public static void main(String [] args) {
		new ch13_GraphicsDrawLineMouseEx3();
	} 
}
