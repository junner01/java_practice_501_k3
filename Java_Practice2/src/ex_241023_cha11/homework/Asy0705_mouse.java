package ex_241023_cha11.homework;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Asy0705_mouse extends JFrame {
	public Asy0705_mouse() {
		setTitle("안성엽 마우스 그림 실습");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new MyPanel());
		setSize(300, 300);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Asy0705_mouse();
	}

	class MyPanel extends JPanel {
		private Vector<Point> vStart = new Vector<Point>();
		private Vector<Point> vEnd = new Vector<Point>();

		public MyPanel() {
			addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					Point startP = e.getPoint();
					vStart.add(startP);
				}

				public void mouseReleased(MouseEvent e) {
					Point endP = e.getPoint();
					vEnd.add(endP);
					repaint();
				}
			});

			addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e) {
					Point endP = e.getPoint();
					if (vStart.size() > vEnd.size()) {
						vEnd.set(vEnd.size() - 1, endP);
					} else {
						vEnd.add(endP);
					}
					repaint();
				}
			});
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.GRAY);

			for (int i = 0; i < vStart.size(); i++) {
				Point s = vStart.elementAt(i);
				Point e = vEnd.elementAt(i);

				int x = Math.min(s.x, e.x);
				int y = Math.min(s.y, e.y);
				int width = Math.abs(s.x - e.x);
				int height = Math.abs(s.y - e.y);

				g.drawRect(x, y, width, height);
			}
		}
	}
}
