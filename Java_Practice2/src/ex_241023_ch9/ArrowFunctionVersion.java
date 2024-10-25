package ex_241023_ch9;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ArrowFunctionVersion extends JFrame {
	public ArrowFunctionVersion() {
		setTitle("Action 이벤트 리스너 작성");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		JButton btn = new JButton("Action");
		c.add(btn);
		// 익명클래스 버전.
		// 람다식으로 , 화살표 함수 : () => {}
		// 조건, 함수형 인터페이스(추상 메서드가 하나일 때만 ) 이용가능.
		btn.addActionListener(e -> {
			JButton b = (JButton) e.getSource();
			if (b.getText().equals("Action")) {
				b.setText("액션");
			} else {
				b.setText("Action");
				setTitle(b.getText());
			}
		});

		setSize(350, 150);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ArrowFunctionVersion();
	}
}
