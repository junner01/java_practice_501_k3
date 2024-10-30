package ex_241023_cha13.homework;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class WordTestJHJ1205Test extends JFrame {

	private JTextField wordField;
	private JTextField meaningField;
	private JPanel wordPanel;

	private Map<String, JLabel> wordLabelMap;
	private Map<String, String> wordMeaningLabelMap;
//		private Random random;

	public WordTestJHJ1205Test() {

		setTitle("단어 프로그램 V 1.0.0");
		setSize(600, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		wordLabelMap = new HashMap<>();
		wordMeaningLabelMap = new HashMap<String, String>();
		InputStreamReader in = new InputStreamReader(System.in);

		setLayout(new BorderLayout());
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new FlowLayout());

		inputPanel.add(new JLabel("단어: "));
		wordField = new JTextField(10);
		inputPanel.add(wordField);

		inputPanel.add(new JLabel("뜻: "));
		meaningField = new JTextField(10);
		inputPanel.add(meaningField);

		JButton saveButton = new JButton("save");
		inputPanel.add(saveButton);

		JButton roadButton = new JButton("road");
		inputPanel.add(roadButton);

		wordPanel = new JPanel();
		wordPanel.setLayout(new FlowLayout());

		add(inputPanel, BorderLayout.NORTH);
		add(wordPanel, BorderLayout.CENTER);

		saveButton.addActionListener(e -> saveWord());
		roadButton.addActionListener(e -> showRoadWord());

		setVisible(true);
	}

	private void saveWord() {
		String word = wordField.getText();
		String meaning = meaningField.getText();

		if (!word.isEmpty() && !meaning.isEmpty()) {
			try (FileWriter fout = new FileWriter("c:\\Temp\\test.txt", true)) {
				fout.write("단어: " + word + "  뜻: " + meaning + "\n");
				JOptionPane.showMessageDialog(this, "단어와 뜻이 성공적으로 저장되었습니다.");

				wordField.setText("");
				meaningField.setText("");

				fout.close();
			} catch (IOException e) {
				System.out.println("입출력 오류");
				JOptionPane.showMessageDialog(this, "파일 저장 중 오류가 발생했습니다.", "입출력 오류", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "단어와 뜻을 모두 입력해주세요.", "입력오류", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void showRoadWord() {
		wordPanel.removeAll();

		try (FileReader fr = new FileReader("c:\\Temp\\test.txt")) {
			int c;
			String line = "";
			boolean hasWords = false;

			while ((c = fr.read()) != -1) {
				if ((char) c == '\n') {
					hasWords = true;
					JLabel text = new JLabel(line);
					text.setBorder(new EmptyBorder(5, 0, 0, 10));
					wordPanel.add(text);
					line = "";
				} else {
					line += (char) c;
				}
			}
			if (!hasWords) {
				JLabel emptyLabel = new JLabel("저장된 단어가 없습니다.");
				wordPanel.add(emptyLabel);
			}

			fr.close();
		} catch (IOException e) {
			System.out.println("입출력 오류");
			JOptionPane.showMessageDialog(this, "파일 읽기 중 오류가 발생했습니다.", "입출력 오류", JOptionPane.ERROR_MESSAGE);
			return;
		}
		wordPanel.revalidate();
		wordPanel.repaint();
	}

	public static void main(String[] args) {
		new WordTestJHJ1205Test();
	} // main

}// class