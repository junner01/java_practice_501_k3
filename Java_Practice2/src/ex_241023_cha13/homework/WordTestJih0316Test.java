package ex_241023_cha13.homework;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WordTestJih0316Test extends JFrame {

	private JTextField wordField;
	private JTextField meaningField;
	private JPanel wordPanel;

	private Map<String, JLabel> wordLabelMap;
	private Map<String, String> wordMeaningLabelMap;

	private Random random;

	public WordTestJih0316Test() {
		setTitle("단어 프로그램 v 1.0.0");
		setSize(720, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		wordLabelMap = new HashMap<>();
		wordMeaningLabelMap = new HashMap<>();
		random = new Random();

		setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new FlowLayout());

		inputPanel.add(new JLabel("단어 : "));
		wordField = new JTextField(10);
		inputPanel.add(wordField);

		inputPanel.add(new JLabel("뜻 : "));
		meaningField = new JTextField(10);
		inputPanel.add(meaningField);

		JButton addButton = new JButton("단어추가");
		inputPanel.add(addButton);
		JButton randomButton = new JButton("랜덤단어표시");
		inputPanel.add(randomButton);
		JButton saveButton = new JButton("저장");
		inputPanel.add(saveButton);
		JButton loadButton = new JButton("불러오기");
		inputPanel.add(loadButton);

		wordPanel = new JPanel();
		wordPanel.setLayout(new FlowLayout());

		add(inputPanel, BorderLayout.NORTH);
		add(wordPanel, BorderLayout.CENTER);

		addButton.addActionListener(e -> addWord());
		randomButton.addActionListener(e -> displayRandomWord());
		saveButton.addActionListener(e -> saveWord());
		loadButton.addActionListener(e -> loadWord());

		setVisible(true);
	}

	private void addWord() {
		String word = wordField.getText();
		String meaning = meaningField.getText();

		if (!word.isEmpty() && !meaning.isEmpty()) {
			JLabel wordLabel = new JLabel(word);
			wordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

			wordLabel.addMouseListener(new MouseAdapter() {
				private boolean showMeaning = false;

				@Override
				public void mouseClicked(MouseEvent e) {
					if (!showMeaning) {
						wordLabel.setText("뜻 : " + meaning);
						showMeaning = true;
					} else {
						wordLabel.setText(word);
						showMeaning = false;
					}
				}
			});

			wordLabelMap.put(word, wordLabel);
			wordMeaningLabelMap.put(word, meaning);

			wordPanel.add(wordLabel);
			wordPanel.revalidate();
			wordPanel.repaint();
		} else {
			JOptionPane.showMessageDialog(null, "단어와 뜻을 입력해주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void displayRandomWord() {
		if (wordLabelMap.isEmpty()) {
			JOptionPane.showMessageDialog(null, "단어가 입력 안됨.", "입력 오류", JOptionPane.ERROR_MESSAGE);
			return;
		}
		for (JLabel jLabel : wordLabelMap.values()) {
			jLabel.setVisible(false);
		}

		List<String> words = new ArrayList<>(wordLabelMap.keySet());
		String randomWords = words.get(random.nextInt(words.size()));
		wordLabelMap.get(randomWords).setVisible(true);
	}

	private void saveWord() {
		try (FileWriter writer = new FileWriter("c:\\Temp\\wordTest.txt")) {
			for (String word : wordMeaningLabelMap.keySet()) {
				writer.write(word + "," + wordMeaningLabelMap.get(word) + "\n");
			}
			JOptionPane.showMessageDialog(null, "단어와 뜻이 TXT 파일로 저장되었습니다.", "저장 성공", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "파일 저장 중 오류가 발생했습니다.", "저장 오류", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void loadWord() {
		try (FileReader reader = new FileReader("c:\\Temp\\wordTestCsv.csv")) {
			wordLabelMap.clear();
			wordMeaningLabelMap.clear();
			wordPanel.removeAll();

			int character;

			String currentLine = "";

			while ((character = reader.read()) != -1) {
				if (character == '\n') {
					String[] parts = currentLine.split(",");
					if (parts.length < 2) {
						currentLine = "";
						continue;
					}

					String word = parts[0];
					String meaning = parts[1];

					wordMeaningLabelMap.put(word, meaning);

					JLabel wordLabel = new JLabel(word);
					wordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

					wordLabel.addMouseListener(new MouseAdapter() {
						private boolean showMeaning = false;

						@Override
						public void mouseClicked(MouseEvent e) {
							if (!showMeaning) {
								wordLabel.setText("뜻 : " + meaning);
								showMeaning = true;
							} else {
								wordLabel.setText(word);
								showMeaning = false;
							}
						}
					});

					wordLabelMap.put(word, wordLabel);
					wordPanel.add(wordLabel);
					currentLine = "";
				} else {
					currentLine += (char) character;
				}
			}

			wordPanel.revalidate();
			wordPanel.repaint();
			JOptionPane.showMessageDialog(null, "단어와 뜻이 성공적으로 불러왔습니다.", "불러오기 성공", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "파일 불러오기 중 오류가 발생했습니다.", "불러오기 오류", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		new WordTestJih0316Test();
	}
}
