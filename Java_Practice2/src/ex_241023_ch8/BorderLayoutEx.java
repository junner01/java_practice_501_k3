package ex_241023_ch8;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;

public class BorderLayoutEx extends JFrame {
	BorderLayoutEx() {
		setTitle("BorderLayout 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane(); // 컨텐트팬 알아내기

		// 컨텐트팬에 BorderLayout 배치관리자 설정
		contentPane.setLayout(new BorderLayout(30, 20));

		contentPane.add(new JButton("Calculate"), BorderLayout.CENTER);
		contentPane.add(new JButton("add"), BorderLayout.NORTH);
		contentPane.add(new JButton("sub"), BorderLayout.SOUTH);
		contentPane.add(new JButton("mul"), BorderLayout.EAST);
		contentPane.add(new JButton("div"), BorderLayout.WEST);

		setSize(300, 200); // 프레임 크기 300x200 설정
		setVisible(true); // 프레임을 화면에 출력
	}

	public static void main(String[] args) {
		new BorderLayoutEx();
	}
}
