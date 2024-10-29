package ex_241023_ch9.homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Hsw1009JLabelEx extends JFrame {
    private JTextField wordInput;
    private JButton searchButton;
    private JLabel resultLabel;
    private HashMap<String, String> wordDictionary;

    public Hsw1009JLabelEx() {

        wordDictionary = new HashMap<>();
        wordDictionary.put("apple", "사과");
        wordDictionary.put("banana", "바나나");
        wordDictionary.put("house", "집");
        wordDictionary.put("car", "자동차");
        

        setTitle("단어장 프로그램");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        wordInput = new JTextField(15);
        searchButton = new JButton("단어");
        searchButton = new JButton("검색");
        resultLabel = new JLabel("결과가 여기에 표시됩니다.",SwingConstants.CENTER);


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = wordInput.getText();
                String meaning = wordDictionary.get(word.toLowerCase());
                if (meaning != null) {
                    resultLabel.setText("단어: " + word + " - 의미: " + meaning);
                } else {
                    resultLabel.setText("단어를 찾을 수 없습니다.");
                }
            }
        });

        panel.add(new JLabel("단어 입력:"));
        panel.add(wordInput);
        panel.add(searchButton);


        add(panel, BorderLayout.NORTH);
        add(resultLabel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Hsw1009JLabelEx vocabApp = new Hsw1009JLabelEx();
                vocabApp.setVisible(true);
            }
        });
    }
}


