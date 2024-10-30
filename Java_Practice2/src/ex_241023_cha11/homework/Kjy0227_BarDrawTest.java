package ex_241023_cha11.homework;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Kjy0227_BarDrawTest extends JFrame {
	private MyLabel bar = new MyLabel(100);
    private JLabel valueLabel; // 현재 수치

    public Kjy0227_BarDrawTest(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(null);
       
        bar.setBackground(Color.ORANGE);
        bar.setOpaque(true);
        bar.setLocation(20, 50);
        bar.setSize(300, 20);
        c.add(bar);

        valueLabel = new JLabel("현재 수치 : 0");
        valueLabel.setLocation(20, 80);
        valueLabel.setSize(200, 20);
        c.add(valueLabel);
       
        c.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                bar.fill();
                updateValueLabel();
            }
        });

        setSize(350, 200);
        setVisible(true);
        c.setFocusable(true);
        c.requestFocus();

        ConsumerThread th = new ConsumerThread(bar);
        th.start();
    }

    class MyLabel extends JLabel {
        private int barSize = 0;
        private int maxBarSize;

        public MyLabel(int maxBarSize) {
            this.maxBarSize = maxBarSize;
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.MAGENTA);
            int width = (int) (((double) (this.getWidth())) / maxBarSize * barSize);
            if (width == 0) return;
            g.fillRect(0, 0, width, this.getHeight());
        }

        synchronized void fill() {
            if (barSize == maxBarSize) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    return;
                }
            }
            barSize++;
            repaint();
            notify();
        }

        synchronized void consume() {
            if (barSize == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    return;
                }
            }
            barSize--;
            repaint();
            notify();
        }

        public int getBarSize() {
            return barSize;
        }
    }

    class ConsumerThread extends Thread {
        private MyLabel bar;

        public ConsumerThread(MyLabel bar) {
            this.bar = bar;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    sleep(200);
                    bar.consume();
                    updateValueLabel();
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    private void updateValueLabel() {
        valueLabel.setText("현재 수치 : " + bar.getBarSize());
    }

    public static void main(String[] args) {
        new Kjy0227_BarDrawTest("아무 키나 눌러 바 채우기");
    }

}
