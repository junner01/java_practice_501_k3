package ex_241023_ch9.homework;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Cyu0923 {

	private static final String[] words = { "apple", "banana", "house", "car" };
	private static final String[] meanings = { "사과", "바나나", "집", "차" };

	public static void main(String[] args) {
		JFrame frame = new JFrame("메모장");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());

		for (int i = 0; i < words.length; i++) {
			final int index = i;

			JLabel wordLabel = new JLabel(words[i]);
			wordLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					wordLabel.setText(meanings[index]);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					wordLabel.setText(words[index]);
				}
			});

			frame.add(wordLabel);
		}

		frame.setSize(400, 200);
		frame.setVisible(true);
	}
}