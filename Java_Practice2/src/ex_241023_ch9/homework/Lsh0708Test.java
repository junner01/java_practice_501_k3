package ex_241023_ch9.homework;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Lsh0708Test extends JFrame {
    private JLabel la = new JLabel("apple");
    private JLabel la2 = new JLabel("banana");
    private JLabel la3 = new JLabel("house");
    private JLabel la4 = new JLabel("car");

    public Lsh0708Test() {
        setTitle("단어장 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(null);

        // 레이블 크기 및 위치 설정
        la.setSize(80, 20);
        la.setLocation(100, 20);
        la2.setSize(80, 20);
        la2.setLocation(100, 40);
        la3.setSize(80, 20);
        la3.setLocation(100, 60);
        la4.setSize(80, 20);
        la4.setLocation(100, 80);

        // 각 레이블에 마우스 리스너 추가
        MyMouseListener listener = new MyMouseListener();
        la.addMouseListener(listener);
        la2.addMouseListener(listener);
        la3.addMouseListener(listener);
        la4.addMouseListener(listener);

        c.add(la); 
        c.add(la2); 
        c.add(la3); 
        c.add(la4); 

        setSize(300, 200);
        setVisible(true);
    }

    class MyMouseListener extends MouseAdapter {
        private String originalText;

        public void mousePressed(MouseEvent e) {
            JLabel word = (JLabel) e.getSource();
            originalText = word.getText(); // 원래 텍스트 저장
            switch (originalText) {
                case "apple":
                    word.setText("사과");
                    break;
                case "banana":
                    word.setText("바나나");
                    break;
                case "house":
                    word.setText("집");
                    break;
                case "car":
                    word.setText("차");
                    break;
            }
        }

        public void mouseReleased(MouseEvent e) {
            JLabel word = (JLabel) e.getSource();
            word.setText(originalText); // 원래 텍스트로 되돌리기
        }
    }

    public static void main(String[] args) {
        new Lsh0708Test();
    }
}
