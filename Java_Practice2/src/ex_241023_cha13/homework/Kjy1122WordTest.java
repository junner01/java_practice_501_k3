package ex_241023_cha13.homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;


public class Kjy1122WordTest extends JFrame {
    private JTextField wordField; // 영어 단어 입력 필드
    private JTextField meaningField; // 영어 뜻 입력 필드
    private JTextArea displayArea; // Load한 단어를 표시할 텍스트 영역
    private JPanel wordPanel; // 단어 표시할 패널

    private Map<String, JLabel> wordLabelMap; // 단어와 JLabel 매핑
    private Map<String, String> wordMeaningLabelMap; // 단어와 뜻 매핑
    private Random random; // 랜덤 단어 뽑기

    public Kjy1122WordTest() {
        setTitle("단어 프로그램 V 2.0.1");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        wordLabelMap = new HashMap<>();
        wordMeaningLabelMap = new HashMap<>();
        random = new Random();

        setLayout(new BorderLayout());

        // 상단 입력 패널 설정
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("단어: "));
        wordField = new JTextField(10);
        inputPanel.add(wordField);

        inputPanel.add(new JLabel("뜻: "));
        meaningField = new JTextField(10);
        inputPanel.add(meaningField);

        JButton addButton = new JButton("단어 추가");
        inputPanel.add(addButton);

        JButton randomButton = new JButton("랜덤 단어 뽑기");
        inputPanel.add(randomButton);

        JButton saveButton = new JButton("Save");
        inputPanel.add(saveButton);

        JButton loadButton = new JButton("Load");
        inputPanel.add(loadButton);

        // 단어 출력용 패널 설정
        wordPanel = new JPanel(new FlowLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(wordPanel, BorderLayout.CENTER);

        // 중앙의 텍스트 영역 설정 (파일에서 Load한 단어들 표시)
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.SOUTH);

        // 버튼 이벤트 리스너 추가
        addButton.addActionListener(e -> addWord());
        randomButton.addActionListener(e -> showRandomWord());
        saveButton.addActionListener(e -> saveWordsToFile());
        loadButton.addActionListener(e -> loadWordsFromCsvFile());

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
                        wordLabel.setText("뜻: " + meaning);
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
            JOptionPane.showMessageDialog(null, "단어와 뜻을 모두 입력해주세요.",
                    "입력오류: 모두 입력해주세요", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showRandomWord() {
        if (wordLabelMap.isEmpty()) {
            JOptionPane.showMessageDialog(null, "단어가 입력 안됨",
                    "먼저, 단어를 추가해주세요", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (JLabel jLabel : wordLabelMap.values()) {
            jLabel.setVisible(false);
        }

        List<String> words = new ArrayList<>(wordLabelMap.keySet());
        String randomWord = words.get(random.nextInt(words.size()));
        wordLabelMap.get(randomWord).setVisible(true);
    }

    private void saveWordsToFile() {
        File file = new File("C:\\Temp\\WordTest.txt");
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Map.Entry<String, String> entry : wordMeaningLabelMap.entrySet()) {
                    writer.write(entry.getKey() + "," + entry.getValue());
                    writer.newLine();
                }
                displayArea.append("Saved words to file.\n");
            }
        } catch (IOException ex) {
            displayArea.append("Error saving file.\n");
            ex.printStackTrace();
        }
    }

    private void loadWordsFromCsvFile() {
        File file = new File("C:\\Temp\\wordTestCsv.csv");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                wordPanel.removeAll();
                wordLabelMap.clear();
                wordMeaningLabelMap.clear();
                displayArea.setText("");

                String line;
                StringBuilder loadedWords = new StringBuilder("Loaded words:\n");
                while ((line = reader.readLine()) != null) {
                    String[] words = line.split(",");
                    for (int i = 0; i < words.length; i += 2) {
                        if (i + 1 < words.length) {
                            String word = words[i];
                            String meaning = words[i + 1];
                            wordField.setText(word);
                            meaningField.setText(meaning);
                            addWord();
                            loadedWords.append(word).append(" - ").append(meaning).append("\n");
                        }
                    }
                }
                displayArea.setText(loadedWords.toString());
                wordPanel.revalidate();
                wordPanel.repaint();
            } catch (IOException ex) {
                displayArea.append("Error loading file.\n");
                ex.printStackTrace();
            }
        } else {
            displayArea.append("File not found: wordTestCsv.csv\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Kjy1122WordTest::new);
    }//main
    
}//class
