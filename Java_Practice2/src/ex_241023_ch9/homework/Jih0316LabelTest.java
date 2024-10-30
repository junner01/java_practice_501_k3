package ex_241023_ch9.homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Jih0316LabelTest {
    private static HashMap<String, String> vocabulary;

    public static void main(String[] args) {
        vocabulary = new HashMap<>();
        vocabulary.put("apple", "사과");
        vocabulary.put("banana", "바나나");
        vocabulary.put("house", "집");
        vocabulary.put("car", "자동차");

        JFrame frame = new JFrame("단어장");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(0, 1));

        for (String word : vocabulary.keySet()) {
            JLabel label = new JLabel(word, SwingConstants.CENTER);
            label.setFont(new Font("Serif", Font.PLAIN, 24));
            label.setOpaque(true);
            label.setBackground(Color.LIGHT_GRAY);
            label.addMouseListener(new MouseAdapter() {
                private boolean showingMeaning = false;

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (showingMeaning) {
                        label.setText(word);  // 원래 단어로 복구
                        label.setBackground(Color.LIGHT_GRAY);
                    } else {
                        label.setText(vocabulary.get(word));  // 뜻으로 변경
                        label.setBackground(Color.YELLOW);
                    }
                    showingMeaning = !showingMeaning;
                }
            });
            frame.add(label);
        }

        frame.setVisible(true);
    }
}
