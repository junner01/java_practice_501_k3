package ex_241023_cha11.homework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class SoojongGraphicsDraw extends JFrame {
    private String shapeType = "Rect"; // 기본값을 사각형으로 설정

    public SoojongGraphicsDraw() {
        setTitle("박수종");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new MyPanel());
        createMenu();
        setSize(300, 300);
        setVisible(true);
    }

    public void createMenu() {
        JMenuBar mb = new JMenuBar();
        
        JMenu shapeMenu = new JMenu("Shape");
        
        JMenuItem rectItem = new JMenuItem("Rect");
        JMenuItem ovalItem = new JMenuItem("Oval");

        // Rect 메뉴 클릭 시 사각형으로 설정하고 기존 도형들 지우기
        rectItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                shapeType = "Rect";
                MyPanel panel = (MyPanel) getContentPane(); 
                panel.clearDrawings();
            }
        });

        // Oval 메뉴 클릭 시 원형으로 설정하고 기존 도형들 지우기
        ovalItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                shapeType = "Oval";
                MyPanel panel = (MyPanel) getContentPane(); 
                panel.clearDrawings();
            }
        });

        shapeMenu.add(rectItem);
        shapeMenu.add(ovalItem);
        
        mb.add(shapeMenu);
        
        setJMenuBar(mb);
    }

    public static void main(String[] args) {
        new SoojongGraphicsDraw();
    }

    class MyPanel extends JPanel {
        private Vector<Point> vStart = new Vector<Point>();
        private Vector<Point> vEnd = new Vector<Point>();

        public MyPanel() {
            MyMouseListener listener = new MyMouseListener();
            addMouseListener(listener);
            addMouseMotionListener(listener);
        }

        class MyMouseListener extends MouseAdapter {
            public void mousePressed(MouseEvent e) {
                Point startP = e.getPoint();
                vStart.add(startP);
            }

            public void mouseReleased(MouseEvent e) {
                Point endP = e.getPoint();
                vEnd.add(endP);
                repaint();
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLUE);

            for (int i = 0; i < vStart.size(); i++) {
                Point start = vStart.get(i);
                Point end = vEnd.get(i);
                int x = Math.min(start.x, end.x);
                int y = Math.min(start.y, end.y);
                int width = Math.abs(start.x - end.x);
                int height = Math.abs(start.y - end.y);

                if (shapeType.equals("Rect")) {
                    g.drawRect(x, y, width, height); // 사각형 그리기
                } else if (shapeType.equals("Oval")) {
                    g.drawOval(x, y, width, height); // 원형 그리기
                }
            }
        }

        // 도형을 모두 지우는 메서드
        public void clearDrawings() {
            vStart.clear();
            vEnd.clear();
            repaint();
        }
    }
}
