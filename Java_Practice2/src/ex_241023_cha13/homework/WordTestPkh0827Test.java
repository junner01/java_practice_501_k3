package ex_241023_cha13.homework;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
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

public class WordTestPkh0827Test extends JFrame {
	private JTextField wordField; // 영어단어 입력 필드
	private JTextField meaningField; // 영어 뜻 입력 필드
	private JPanel wordPanel; // 단어 표시할 패널

	private Map<String, JLabel> wordLabelMap; // 단어와 JLabel을 매핑하는 맵
	private Map<String, String> wordMeaningLabelMap; // 단어와 뜻을 매핑하는 맵
	private Random random; // 랜덤 단어 선택을 위한 객체

	public WordTestPkh0827Test() {
		setTitle("단어 프로그램 V 1.0.0");
		setSize(1000, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		wordLabelMap = new HashMap<>();
		wordMeaningLabelMap = new HashMap<>();
		random = new Random();

		setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new FlowLayout());

		inputPanel.add(new JLabel("단어: "));
		wordField = new JTextField(10);
		inputPanel.add(wordField);

		inputPanel.add(new JLabel("뜻: "));
		meaningField = new JTextField(10);
		inputPanel.add(meaningField);

		JButton addButton = new JButton("단어추가");
		inputPanel.add(addButton);

		JButton randomButton = new JButton("랜덤 단어 뽑기");
		inputPanel.add(randomButton);

		JButton saveButton = new JButton("저장");
		inputPanel.add(saveButton);

		JButton roadButton = new JButton("불러오기");
		inputPanel.add(roadButton);

		wordPanel = new JPanel();
		wordPanel.setLayout(new FlowLayout());

		add(inputPanel, BorderLayout.NORTH);
		add(wordPanel, BorderLayout.CENTER);

		addButton.addActionListener(e -> addWord());
		randomButton.addActionListener(e -> showRandomWord());
		saveButton.addActionListener(e -> saveWords());
		roadButton.addActionListener(e -> loadWords());

		setVisible(true);
	}

	private void addWord() {
		String word = wordField.getText();
		String meaning = meaningField.getText();

		if (!word.isEmpty() && !meaning.isEmpty()) {
			addWordToUI(word, meaning);
		} else {
			JOptionPane.showMessageDialog(null, "단어와 뜻을 모두 입력해주세요.", "입력오류: 모두 입력해주세요", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void addWordToUI(String word, String meaning) {
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
	}

	public void showRandomWord() {
		if (wordLabelMap.isEmpty()) {
			JOptionPane.showMessageDialog(null, "단어가 입력 안됨", "먼저, 단어를 추가해주세요", JOptionPane.ERROR_MESSAGE);
			return;
		}

		for (JLabel jLabel : wordLabelMap.values()) {
			jLabel.setVisible(false);
		}

		List<String> words = new ArrayList<>(wordLabelMap.keySet());
		String randomWord = words.get(random.nextInt(words.size()));
		wordLabelMap.get(randomWord).setVisible(true);
	}

	private void saveWords() {
		try (FileWriter fileWriter = new FileWriter("C:\\Temp\\wordTesword.txt")) {
			for (String word : wordLabelMap.keySet()) {
				String meaning = wordMeaningLabelMap.get(word);
				fileWriter.write(word + " = " + meaning+"\n");
			}
			JOptionPane.showMessageDialog(null, "단어가 저장되었습니다.", "저장 성공", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "저장 중 오류가 발생했습니다.", "저장 오류", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void loadWords() {
		try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Temp\\wordTestCsv.csv"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(","); // CSV 형식에서 '='를 기준으로 분리
				if (parts.length == 2) {
					String word = parts[0].trim();
					String meaning = parts[1].trim();
					addWordToUI(word, meaning); // UI에 추가하는 메서드 호출
				}
			}
			JOptionPane.showMessageDialog(null, "단어가 불러와졌습니다.", "불러오기 성공", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "불러오는 중 오류가 발생했습니다.", "불러오기 오류", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		new WordTestPkh0827Test();
	}
}
