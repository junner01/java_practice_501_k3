package ex_241023_ch8;

import javax.swing.JFrame;

public class MyFrame1 extends JFrame {
	public MyFrame1() {
		setTitle("300x300 스윙 프레임 만들기");
		setSize(300, 300);
		setVisible(true);
	}

	public static void main(String[] args) {
		MyFrame1 mf = new MyFrame1();
		MyFrame1 mf2 = new MyFrame1();
	}
}




