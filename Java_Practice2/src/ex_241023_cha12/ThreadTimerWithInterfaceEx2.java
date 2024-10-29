package ex_241023_cha12;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ThreadTimerWithInterfaceEx2 extends JFrame {
	public ThreadTimerWithInterfaceEx2() {
		setTitle("Thread를 상속받은 타이머 스레드 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		// 타이머 값을 출력할 레이블 생성
		JLabel timerLabel = new JLabel();
		timerLabel.setFont(new Font("Gothic", Font.ITALIC, 80));
		c.add(timerLabel);

		// 스레드 상속 받은 클래스의 인스턴스 생성.
		// 작업 순서1, 
		// B 클래스에 , 라벨 인스턴스를 전달하면, 
		// 전달된 라벨은 A, B 클래스에서 같이 바라보는 공통의 라벨. 
		TimerThread th = new TimerThread(timerLabel);
		setSize(250, 150);
		setVisible(true);
		th.start(); // 타이머 스레드의 실행을 시작하게 한다.
	}
//
	// ThreadTimerEx : A 라는 클래스 
	// TimerThread : B 라는 클래스 
	// 인스턴스 => 참조형 변수 => 0x100 , 원본 그대로 유지.
	// 결론, A 라는 클래스에서, 특정의 인스턴스를 B라는 클래스에 전달시. 
	// B라는 클래스에서는, 받을 때, 타입과 변수를 선언하고, 
	// 생성자를 초기화를 해서 사용하는 기법. 
	class TimerThread extends Thread {
		// 작업 순서2,
		// 받을 변수를 전역으로 선언하고, 
		private JLabel timerLabel; // 타이머 값이 출력되는 레이블

		// 작업 순서3,
		// 생성자 통해서, 넘어온 라벨 인스턴스를 받아서, 
		// B 라는 클래스에서 작업 할수 있게 해줌. 
		public TimerThread(JLabel timerLabel) {
			this.timerLabel = timerLabel;
		}

		// 스레드 코드. run()이 종료하면 스레드 종료
		@Override
		public void run() {
			int n = 0; // 타이머 카운트 값
			while (true) { // 무한 루프
				timerLabel.setText(Integer.toString(n));
				n++; // 카운트 증가
				try {
					Thread.sleep(1000); // 1초동안 잠을 잔다.
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}

	public static void main(String[] args) {
		new ThreadTimerWithInterfaceEx2();
	}
}
