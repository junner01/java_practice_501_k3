package ex_241023_ch8;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GridLayoutEx extends JFrame {
	 GridLayoutEx() {
		super("GridLayout 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();

		// 1x10의 GridLayout 배치관리자
		contentPane.setLayout(new GridLayout(2, 5,10,10));

		for (int i = 0; i < 10; i++) { // 10개의 버튼 부착
			String text = Integer.toString(i); // i를 문자열로 변환
			JButton button = new JButton(text);
			contentPane.add(button); // 컨텐트팬에 버튼 부착
		}
		setSize(500, 200);
		setVisible(true);
	}

	public static void main(String[] args) {
		new GridLayoutEx();
	}
}
