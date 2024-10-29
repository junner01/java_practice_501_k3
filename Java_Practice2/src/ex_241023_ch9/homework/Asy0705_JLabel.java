package ex_241023_ch9.homework;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Asy0705_JLabel extends JFrame {

    public Asy0705_JLabel() {
        setTitle("단어");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        // JLabel 생성
        JLabel label1 = new JLabel("Apple");
        JLabel label2 = new JLabel("Banana");
        JLabel label3 = new JLabel("House");
        JLabel label4 = new JLabel("Car");

        // 마우스 리스너 추가
        label1.addMouseListener(new MyMouseAdapter());
        label2.addMouseListener(new MyMouseAdapter());
        label3.addMouseListener(new MyMouseAdapter());
        label4.addMouseListener(new MyMouseAdapter());

        // 컨테이너에 라벨 추가
        c.add(label1);
        c.add(label2);
        c.add(label3);
        c.add(label4);

        setSize(250, 250);
        setVisible(true);
    }

    // MouseAdapter 클래스를 상속하여 마우스 이벤트 처리
    class MyMouseAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            JLabel label = (JLabel) e.getSource();
            // 클릭 시 뜻을 보여줌
            if (label.getText().equals("Apple"))
                label.setText("사과");
            else if (label.getText().equals("Banana"))
                label.setText("바나나");
            else if (label.getText().equals("House"))
                label.setText("집");
            else if (label.getText().equals("Car"))
                label.setText("자동차");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            JLabel label = (JLabel) e.getSource();
            // 클릭 해제 시 원래 단어로 복구
            if (label.getText().equals("사과"))
                label.setText("Apple");
            else if (label.getText().equals("바나나"))
                label.setText("Banana");
            else if (label.getText().equals("집"))
                label.setText("House");
            else if (label.getText().equals("자동차"))
                label.setText("Car");
        }
    }

    public static void main(String[] args) {
        new Asy0705_JLabel();
    }
}
