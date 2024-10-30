package ex_241023_cha11.homework;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Lsh1208_241029homework_thread extends JFrame {

	private MyLabel bar = new MyLabel(100);

	public Lsh1208_241029homework_thread(String title) {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		// 배치 관리자 없고, 사용자가 알아서 위치, 크기등을 설정함.
		c.setLayout(null);

		bar.setBackground(Color.ORANGE);
		bar.setOpaque(true);
		bar.setLocation(20, 50);
		bar.setSize(300, 20);
		c.add(bar);
		// UI, 프레임 창에, 막대 바 붙이기

		// 패널에 키 이벤트 리스너 추가.
		c.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				// 막대에, 분홍색으로 눈금이 특정 값만큼씩 증가하는 기능.
				bar.fill();
			}
		});

		setSize(350, 200);
		setVisible(true);

		c.setFocusable(true);
		c.requestFocus();

		// 스레드 이용해서, 특정 시간동안, 눈금이 감소하는 기능을 구현.
		// A 클래스 -> B 클래스, , 주입, 오렌지 막대를, 참조형 전달.
		// 보내는 부분,
		ConsumerThread th = new ConsumerThread(bar);
		th.start(); // 스레드 시작
	}

	class MyLabel extends JLabel {
		// 오렌지 막대,
		// barSize , 눈금 만큼씩 증가 또는 감소하는 화면,
		private int barSize = 0; // 바의 크기
		// 전체 최대 크기, 기본 100으로 가정.
		private int maxBarSize;
		private JLabel percentLabel;

		// A -> B , 특정 값을 전달하는 방법, 주입, 기본형.
		// 받는 부분
		public MyLabel(int maxBarSize) {
			this.maxBarSize = maxBarSize;
			percentLabel = new JLabel("0%");
			percentLabel.setForeground(Color.black);
			percentLabel.setSize(50, 20);
			add(percentLabel);
		}

		// 컴포넌트를 원래 스스로 그려주는 게 기본.
		// paintComponent , 재정의 하면, 그려주는 시점을 우리가 정할수 있음.
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			// 오렌지 막대를 분홍색으로 변경해서
			g.setColor(Color.MAGENTA);
			// width , barSize 눈금 만큼 씩 채워주는 역할. 1씩 증가하거나, 감소합니다.
			int width = (int) (((double) (this.getWidth())) / maxBarSize * barSize);
			if (width == 0)
				return;
			// 색을 채운 사각형 그리기
			// (0,0) 좌표에서, width(1만큼씩 채워준다)
			g.fillRect(0, 0, width, this.getHeight());

			// 퍼센트 텍스트 추가
			percentLabel.setText(barSize * 100 / maxBarSize + "%");
			percentLabel.setLocation(this.getWidth() / 2 - percentLabel.getWidth() / 2,
					this.getHeight() / 2 - percentLabel.getHeight() / 2);
		}

		// synchronized, fill() 기능이 다 동작하면, 그 다음에, 감소하는 기능이 동작함.
		// 동기화 기능.
		synchronized void fill() {
			// 계속 분홍 막대 그래프가, 최대로 차게 되면, 똑같아지면,
			// 잠시 대기하는 로직.
			// 1
			if (barSize == maxBarSize) {
				try {
					wait();
				} catch (InterruptedException e) {
					return;
				}
			}
			// 2
			barSize++;
			// 3
			repaint(); // 바 다시 그리기
			// 4
			notify();
		}

		// 감소하는 기능.
		synchronized void consume() {
			if (barSize == 0) {
				try {
					wait();
				} catch (InterruptedException e) {
					return;
				}
			}
			barSize--;
			repaint(); // 바 다시 그리기
			notify();
		}

		// 현재 barSize 반환 메서드 추가
		public int getBarSize() {
			return barSize;
		}
	}

	class ConsumerThread extends Thread {
		// 전역으로 참조형 변수 선언, 받는 변수(=매개변수)
		private MyLabel bar;

		public ConsumerThread(MyLabel bar) {
			this.bar = bar;
		}

		@Override
		public void run() {
			while (true) {
				try {
					// 0.2 초마다, 분홍색 막대를 감소 시키는 기능.
					sleep(200);
					bar.consume();
					// UI 업데이트 (현재 barSize 값을 JLabel에 표시)
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Lsh1208_241029homework_thread("키를 눌러 바를 채우기");
	}

}

