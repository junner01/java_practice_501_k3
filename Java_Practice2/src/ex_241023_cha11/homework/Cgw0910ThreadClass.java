package ex_241023_cha11.homework;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Cgw0910ThreadClass extends JFrame {
	private Cgw0910Thread bar = new Cgw0910Thread(100);

	public Cgw0910ThreadClass(String title) {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel viewGauge = new JLabel();
		viewGauge.setBackground(Color.white);
		bar.setLocation(50, 50);
		viewGauge.setSize(50, 50);
		Container c = getContentPane();
		c.setLayout(null);
		bar.setBackground(Color.ORANGE);
		bar.setOpaque(true);
		bar.setLocation(20, 50);
		bar.setSize(300, 20);
		c.add(bar);
		c.add(viewGauge);
		c.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				bar.fill();
				viewGauge.setText(String.valueOf(bar.getbarSize()));
			}
		});
		setSize(350, 200);
		setVisible(true);
		c.setFocusable(true);
		c.requestFocus();
		Cgw0910ConsumerThread th = new Cgw0910ConsumerThread(bar);
		th.start(); // 스레드 시작
	}

	public static void main(String[] args) {
		new Cgw0910ThreadClass("아무키나 빨리 눌러 바 채우기");
	}
}