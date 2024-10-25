package ex_241023_ch9;

import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FlyingTextEx extends JFrame {
	private JPanel contentPane = new JPanel();
	private JLabel la = new JLabel("HELLO");

	public FlyingTextEx() {
		super("상,하,좌,우 키를 이용하여 텍스트 움직이기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();
		// 배치 관리자 설정 안함, 사용자가 직접 위치를 지정 가능. 
		c.setLayout(null);
		// Container 패널에 키 이벤트 리스너 설정.
		c.addKeyListener(new MyKeyListener());

		// 라벨의 초기 위칫값
		la.setLocation(50, 50);
		// 라벨의 초기 크기
		la.setSize(100, 20);
		c.add(la);

		setSize(200, 200);
		setVisible(true);

		c.setFocusable(true); // 컨텐트팬이 포커스를 받을 수 있도록 설정
		c.requestFocus(); // 포커스 지정
	}

	class MyKeyListener extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode(); // 입력된 키코드
			switch (keyCode) {
			case KeyEvent.VK_UP:
				la.setLocation(la.getX(), la.getY() - 10);
				break;
			case KeyEvent.VK_DOWN:
				la.setLocation(la.getX(), la.getY() + 10);
				break;
			case KeyEvent.VK_LEFT:
				la.setLocation(la.getX() - 10, la.getY());
				break;
			case KeyEvent.VK_RIGHT:
				la.setLocation(la.getX() + 10, la.getY());
				break;
			}
		}
	}

	public static void main(String[] args) {
		new FlyingTextEx();
	}
}
