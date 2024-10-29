package ex_241023_ch9.homework;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Pkh0827_voca3 extends JFrame {
	private JLabel meaningLabel; // 뜻을 표시할 레이블

	public Pkh0827_voca3() {
		setTitle("단어장");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout()); 
		JPanel buttonPanel = new JPanel(); 
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 40)); 

		String[] words = { "Apple", "Grape", "WaterMelon", "StrawBerry","lemon" };
		for (String word : words) {
			JButton button = new JButton(word);
			buttonPanel.add(button);
			button.addActionListener(new ActionListener() {
				private boolean isClicked = false; // 클릭 상태 추적

				@Override
				public void actionPerformed(ActionEvent e) {
					if (!isClicked) {
						showMeaning(word);
						isClicked = true; 
					} else {
						meaningLabel.setText("");
						isClicked = false; 
					}
				}
			});
		}

		// 버튼 패널과 뜻 레이블 추가
		meaningLabel = new JLabel(""); // 초기에는 아무 것도 표시하지 않음
		c.add(buttonPanel, BorderLayout.CENTER); // 버튼 패널을 중앙에 배치
		c.add(meaningLabel, BorderLayout.SOUTH); // 뜻 레이블을 하단에 배치

		setSize(600, 300);
		setVisible(true);
	}

	private void showMeaning(String word) {
		switch (word) {
		case "Apple":
			meaningLabel.setText("뜻: 사과");
			break;
		case "Grape":
			meaningLabel.setText("뜻: 포도");
			break;
		case "WaterMelon":
			meaningLabel.setText("뜻: 수박");
			break;
		case "StrawBerry":
			meaningLabel.setText("뜻: 딸기");
			break;
		case "lemon":
			meaningLabel.setText("뜻: 레몬");
			break;
		}
	}

	public static void main(String[] args) {
		new Pkh0827_voca3();
	}
}
