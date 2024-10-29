package ex_241023_ch9.homework;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Cgw0910MouseEvent extends JFrame {
    private HashMap<String, String> wordsHashMap = new HashMap<>();
    private Container c = getContentPane();
    private JPanel gridPanel;
    private JTextField wordField;
    private JTextField meanField;
    private JButton addButton;

    public Cgw0910MouseEvent() {
        setTitle("단어장 바꾸기 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(1, 5, 10, 10));

        inputPanel.add(new JLabel("단어:"));
        wordField = new JTextField();
        inputPanel.add(wordField);

        inputPanel.add(new JLabel("의미:"));
        meanField = new JTextField();
        inputPanel.add(meanField);

        addButton = new JButton("추가");
        inputPanel.add(addButton);

        inputPanel.add(new JLabel(""));

        add(inputPanel, BorderLayout.NORTH);

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(1, 1, 10, 10));

        add(gridPanel, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = wordField.getText().trim();
                String mean = meanField.getText().trim();

                if (word.isEmpty() || mean.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(null, "단어와 의미를 모두 입력하세요.");
                    return;
                }

                if (wordsHashMap.containsKey(word)) {
                    javax.swing.JOptionPane.showMessageDialog(null, "이미 존재하는 단어입니다.");
                    return;
                }

                addWordsHashMap(word, mean);
                wordField.setText("");
                meanField.setText("");
            }
        });

        setVisible(true);
    }

    public void addWordsHashMap(String word, String mean) {
        wordsHashMap.put(word, mean);

        JLabel label = new JLabel(word, JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(java.awt.Color.LIGHT_GRAY);
        label.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));

        label.addMouseListener(new MyMouseListener(label));

        gridPanel.add(label);

        int size = wordsHashMap.size();
        int gridSize = (int) Math.ceil(Math.sqrt(size));
        gridPanel.setLayout(new GridLayout(gridSize, gridSize, 10, 10));

        gridPanel.revalidate();
        gridPanel.repaint();
    }

    class MyMouseListener extends java.awt.event.MouseAdapter {
        private JLabel label;
        private String word;

        public MyMouseListener(JLabel label) {
            this.label = label;
        }

        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
            word = label.getText();
            String meaning = wordsHashMap.get(word);
            System.out.println("의미: " + meaning);
            label.setText(meaning);
        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {
            label.setText(word);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Cgw0910MouseEvent frame = new Cgw0910MouseEvent();
                frame.addWordsHashMap("apple", "사과");
                frame.addWordsHashMap("delicious", "맛있는");
                frame.addWordsHashMap("banana", "바나나");
                frame.addWordsHashMap("long", "긴");
                frame.addWordsHashMap("train", "기차");
                frame.addWordsHashMap("fast", "빠른");
                frame.addWordsHashMap("airplane", "비행기");
                frame.addWordsHashMap("high", "높은");
            }
        });
    }
}