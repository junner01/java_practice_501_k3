package ex_241023_cha11.homework;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Csb806Bar extends JFrame {
	private Label bar = new Label(100);
	private JLabel textLabel;

	public Csb806Bar(String title) {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		bar.setBackground(Color.ORANGE);
		bar.setOpaque(true);
		bar.setLocation(20, 50);
		bar.setSize(300, 20);
		c.add(bar);
		
		textLabel = new JLabel("바 크기 표시");
		textLabel.setLocation(20,80);
		textLabel.setSize(300,20);
		c.add(textLabel);

		c.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				bar.fill();
			}
		});
		
		setSize(350, 200);
		setVisible(true);

		c.setFocusable(true);
		c.requestFocus();
		ConsumerThread th = new ConsumerThread(bar);
		th.start(); // 스레드 시작
	}

	class Label extends JLabel {
		private int barSize = 0; // 바의 크기
		private int maxBarSize;

		public Label(int maxBarSize) {
			this.maxBarSize = maxBarSize;
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.MAGENTA);
			int width = (int) (((double) (this.getWidth())) / maxBarSize * barSize);
			if (width == 0)
				return;
			g.fillRect(0, 0, width, this.getHeight());
		}

		synchronized void fill() {
			if (barSize == maxBarSize) {
				try {
					wait();
				} catch (InterruptedException e) {
					return;
				}
			}
			barSize++;
			repaint(); // 바 다시 그리기
			textLabel.setText("현재 바 크기: " + barSize);
			notify();
		}

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
			textLabel.setText("현재 바 크기: " + barSize);
			notify();
		}
	}

	class ConsumerThread extends Thread {
		private Label bar;

		public ConsumerThread(Label bar) {
			this.bar = bar;
		}

		@Override
		public void run() {
			while (true) {
				try {
					sleep(200);
					bar.consume();
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}

	public static void main(String[] args) {
		new Csb806Bar("아무키나 빨리 눌러 바 채우기");
	} // main

} // class
