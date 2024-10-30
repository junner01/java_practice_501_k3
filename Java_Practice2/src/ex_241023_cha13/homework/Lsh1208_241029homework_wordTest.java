package ex_241023_cha13.homework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Lsh1208_241029homework_wordTest extends JFrame {

	private JTextField wordField;
	private JTextField wordMeanField;
	private JPanel wordPanel;

	private Map<String, JLabel> wordMap;
	private Map<String, String> wordMeanMap;

	public Lsh1208_241029homework_wordTest() {
		setTitle("단어장");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		wordMap = new HashMap<>();
		wordMeanMap = new HashMap<String, String>();

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new FlowLayout());

		inputPanel.add(new JLabel("단어"));
		wordField = new JTextField(10);
		inputPanel.add(wordField);

		inputPanel.add(new JLabel("뜻"));
		wordMeanField = new JTextField(10);
		inputPanel.add(wordMeanField);

		JButton addbtn = new JButton("추가");
		inputPanel.add(addbtn);

		JButton savebtn = new JButton("저장");
		inputPanel.add(savebtn);

		JButton loadbtn = new JButton("로드");
		inputPanel.add(loadbtn);

		wordPanel = new JPanel();
		wordPanel.setLayout(new FlowLayout());
		wordPanel.setBorder(new LineBorder(Color.black, 1));

		add(inputPanel, BorderLayout.NORTH);
		add(wordPanel, BorderLayout.CENTER);

		addbtn.addActionListener(e -> addword());
		savebtn.addActionListener(e -> saveword());
		loadbtn.addActionListener(e -> loadword());

		setVisible(true);
	}

	public void addword() {
		String word = wordField.getText();
		String meaning = wordMeanField.getText();

		if (!word.isEmpty() && !meaning.isEmpty()) {
			JLabel wordLabel = new JLabel(word);

			wordLabel.addMouseListener(new MouseAdapter() {
				private boolean show = false;

				@Override
				public void mouseClicked(MouseEvent e) {
					if (!show) {
						wordLabel.setText(meaning);
						show = true;
					} else {
						wordLabel.setText(word);
						show = false;
					}
				}
			});

			wordMap.put(word, wordLabel);
			wordMeanMap.put(word, meaning);

			wordPanel.add(wordLabel);
			wordPanel.revalidate();
			wordPanel.repaint();
		} else {
			JOptionPane.showMessageDialog(null, "둘다 입력해주세요.");
		}
	}

	public void saveword() {
		String word = wordField.getText();
		String meaning = wordMeanField.getText();

		String filePath = "C:\\Temp\\test.txt";

		if (!word.isEmpty() && !meaning.isEmpty()) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
				writer.write("단어:" + word + "뜻:" + meaning);
				writer.newLine();
				JOptionPane.showMessageDialog(null, "저장되었습니다.");

				wordField.setText("");
				wordMeanField.setText("");
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "파일 저장에 실패했습니다.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "둘다 입력해주세요.");
		}
	}

	public void loadword() {
		String filePath = "C:\\Temp\\test.txt";
		wordPanel.removeAll();

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;

			while ((line = reader.readLine()) != null) {
				JLabel label = new JLabel(line.trim());

				wordPanel.add(label);
			}

			wordPanel.revalidate();
			wordPanel.repaint();

		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "로드하는 데 실패했습니다.");
		}
	}

	public static void main(String[] args) {
		new Lsh1208_241029homework_wordTest();
	}

}
