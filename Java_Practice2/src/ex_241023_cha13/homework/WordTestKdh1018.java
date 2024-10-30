package ex_241023_cha13.homework;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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

public class WordTestKdh1018 extends JFrame {
    private JTextField wordField; // 영어단어 입력 필드
    private JTextField meaningField; // 영어 뜻 입력 필드
    private JPanel wordPanel; // 단어 표시할 패널
    private Map<String, JLabel> wordLabelMap; 
    private Map<String, String> wordMeaningLabelMap;
    private Random random;

    public WordTestKdh1018() {
        setTitle("단어 프로그램 V 1.0.0");
        setSize(600, 300);
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

        JButton loadButton = new JButton("불러오기");
        inputPanel.add(loadButton);

        wordPanel = new JPanel();
        wordPanel.setLayout(new FlowLayout());

        add(inputPanel, BorderLayout.NORTH);
        add(wordPanel, BorderLayout.CENTER);

        addButton.addActionListener(e -> addWord());
        randomButton.addActionListener(e -> showRandomWord());
        saveButton.addActionListener(e -> saveWords());
        loadButton.addActionListener(e -> loadWords());

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
            JOptionPane.showMessageDialog(null, "단어와 뜻을 모두 입력해주세요.", "입력오류: 모두 입력해주세요", JOptionPane.ERROR_MESSAGE);
        }
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("c:\\Temp\\wordTest.txt"))) {
            for (String word : wordLabelMap.keySet()) {
                String meaning = wordMeaningLabelMap.get(word);
                writer.write(word + "," + meaning);
                writer.newLine();
            }
            JOptionPane.showMessageDialog(null, "단어가 저장되었습니다.", "저장 성공", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "저장 중 오류 발생.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadWords() {
        try (BufferedReader reader = new BufferedReader(new FileReader("c:\\Temp\\wordTestCsv.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                for (int i = 0; i < parts.length; i += 2) {
                    String word = parts[i];
                    String meaning = parts[i + 1];
                    addWordToPanel(word, meaning);
                }
            }
            JOptionPane.showMessageDialog(null, "단어가 로드되었습니다.", "로드 성공", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "로드 중 오류 발생.", "오류", JOptionPane.ERROR_MESSAGE);
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "잘못된 파일 형식입니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addWordToPanel(String word, String meaning) {
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

    public static void main(String[] args) {
        new WordTestKdh1018();
    }
}
