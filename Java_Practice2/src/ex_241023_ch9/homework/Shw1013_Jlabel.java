package ex_241023_ch9.homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class Shw1013_Jlabel extends JFrame {

	private HashMap<String, String> vocabulary = new HashMap<>();

	public Shw1013_Jlabel() {
		vocabulary.put("apple", "둥글고 빨간 과일");
		vocabulary.put("banana", "길고 노란 과일");
		vocabulary.put("house", "사람이 살 수 있는 장소");
		vocabulary.put("car", "사람이 이동할 수 있는 수단");

		setTitle("단어장");
		setSize(800, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(2, 2));

		for (String key : vocabulary.keySet()) {
			String value = vocabulary.get(key);

			JLabel label = new JLabel(key, SwingConstants.CENTER);
			label.setFont(new Font("Malgun Gothic", Font.PLAIN, 24));
			label.setOpaque(true);
			label.setBackground(Color.LIGHT_GRAY);

			label.addMouseListener(new MouseAdapter() {
				private boolean labelChange = false;

				public void mouseClicked(MouseEvent e) {
					if (labelChange) {
						label.setText(key);
					} else {
						label.setText(value);
					}
					labelChange = !labelChange;
				}
			});

			add(label);
		}

		setVisible(true);
	}

	public static void main(String[] args) {
		new Shw1013_Jlabel();
	}
}
