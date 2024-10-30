package ex_241023_ch9.homework;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Csb806Voca extends JFrame {
    private JLabel[] labels = new JLabel[4];
    private String[] englishWords = {"apple", "banana", "house", "car"};
    private String[] koreanWords = {"사과", "바나나", "집", "차"};

    public Csb806Voca() {
        setTitle("Subin Voca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = getContentPane();
        container.setLayout(null);

        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel(englishWords[i]);
            labels[i].setSize(100, 20);
            labels[i].setLocation(200, 60 + i * 20);
            labels[i].addMouseListener(new MouseEventHandler(i));
            container.add(labels[i]);
        }

        setSize(500, 300);
        setVisible(true);
    }

    private class MouseEventHandler extends MouseAdapter {
        private final int index;

        public MouseEventHandler(int index) {
            this.index = index;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            labels[index].setText(koreanWords[index]);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            labels[index].setText(englishWords[index]);
        }
    }

    public static void main(String[] args) {
        new Csb806Voca();
    }
}
