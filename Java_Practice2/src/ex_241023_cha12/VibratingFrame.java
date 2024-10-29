package ex_241023_cha12;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;

public class VibratingFrame extends JFrame implements Runnable {
	private Thread th; // 진동하는 스레드

	public VibratingFrame() {
		setTitle("진동하는 프레임 만들기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(200, 200);
		setLocation(300, 300);
		setVisible(true);
		
		// 프레임 내부에 기본 패널에, 마우스 클릭 리스너 설정. 
		getContentPane().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (th != null && th.isAlive()) {
					// 해당 스레드 종료
				th.interrupt();
				} else {
					// 다시 클릭 하면, 새롭게 스레드 인스턴스 생성.
					th = new Thread(VibratingFrame.this);
					th.start(); // 진동 시작
				}
			}
		});
		// 방법2, Thread 클래스 생성자에, 인터페이스 구현한 클래스의 인스턴스 주입. 
		th = new Thread(this); // 진동하는 스레드 객체 생성
		// 스레드 동작 시작.
		// 최초 스레드 동작. 
		th.start(); // 진동 시작
	}

	@Override
	public void run() { // 프레임의 진동을 일으키기 위해
// 20ms마다 프레임의 위치를 랜덤하게 이동 
		Random r = new Random();
		while (true) {
			try {
				// 20ms 대기
				Thread.sleep(20); // 20ms 잠자기
			} catch (InterruptedException e) {
				return; // 리턴하면 스레드 종료
			}
			// r.nextInt() % 5 : -4 이상 , 4 이하, 랜덤한 정수를 뽑기
			int x = getX() + r.nextInt() % 5; // 새 위치 x
			int y = getY() + r.nextInt() % 5; // 새 위치 y

			setLocation(x, y); // 프레임의 위치 이동
		}
	}

	public static void main(String[] args) {
		new VibratingFrame();
	}
}
