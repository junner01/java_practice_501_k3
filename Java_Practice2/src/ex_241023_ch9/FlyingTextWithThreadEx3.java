package ex_241023_ch9;

import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FlyingTextWithThreadEx3 extends JFrame {
	private JPanel contentPane = new JPanel();
	private JLabel la = new JLabel("HELLO");

	public FlyingTextWithThreadEx3() {
		super("상,하,좌,우 키를 이용하여 텍스트 움직이기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();
		// 배치 관리자 설정 안함, 사용자가 직접 위치를 지정 가능.
		c.setLayout(null);
		// Container 패널에 키 이벤트 리스너 설정.
		c.addKeyListener(new MyKeyListener(la));

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
		// 이동할 라벨 추가. 
		private JLabel la; 
		
		// 움직임의 상태 변수,
		private volatile boolean upPressed = false;
		private volatile boolean downPressed = false;
		private volatile boolean leftPressed = false;
		private volatile boolean rightPressed = false;

		// 생성자 추가. 1)라벨 변수 받고, 2) 스레드 실행 시키기 
		public MyKeyListener (JLabel la) {
			this.la = la;
			//스레드 시작. 
			startMovementThread();
		}
		

		public void keyPressed(KeyEvent e) {

			// 키의 누른 상태 변수를 먼저 확인해서, 키가 누른 상태면 빠져 나오기.
			// 스레드가 동작이면, 무시.

			int keyCode = e.getKeyCode(); // 입력된 키코드

			switch (keyCode) {
			case KeyEvent.VK_UP:
				upPressed = true;
				break;
			case KeyEvent.VK_DOWN:
				downPressed = true;
				break;
			case KeyEvent.VK_LEFT:
				leftPressed = true;
				break;
			case KeyEvent.VK_RIGHT:
				rightPressed = true;
				break;
			}

		}

		// 키를 누르고, 뗀 순간의 메서드에서, 상태에 false
		@Override
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode(); // 입력된 키코드

			switch (keyCode) {
			case KeyEvent.VK_UP:
				upPressed = false;
				break;
			case KeyEvent.VK_DOWN:
				downPressed = false;
				break;
			case KeyEvent.VK_LEFT:
				leftPressed = false;
				break;
			case KeyEvent.VK_RIGHT:
				rightPressed = false;
				break;
			}
		} // keyReleased
		
		// 스레드를 이용해서, 각각의 동작을 동시에 수행하는 것처럼 보임. 
		
		private void startMovementThread() {
			// 익명 클래스 형식 구현 -> 람다식으로 구현하는 방법. 
			Thread movementThread = new Thread(() -> {
				while (true) {
					try {
						// 50 ms 지연 주기.
						Thread.sleep(50); //50 ms 간격
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// 키 상태에 따른 JLabel 이동. 
					if (upPressed) {
						la.setLocation(la.getX(),la.getY()-10);
					}
					if (downPressed) {
						la.setLocation(la.getX(),la.getY()+10);
					}
					if (leftPressed) {
						la.setLocation(la.getX()-10,la.getY());
					}
					if (rightPressed) {
						la.setLocation(la.getX()+10,la.getY());
					}
				}
			});
			// 스레드 설정 후, 여기에서 실행. 
			movementThread.start();
		}
		
	} // MyKeyListener

	public static void main(String[] args) {
		new FlyingTextWithThreadEx3();
	}
}
