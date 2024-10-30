package ex_241023_cha11.homework;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Lsh0708Test extends JFrame {
	private JRadioButton Jline;
	private JRadioButton JRect;
	private JRadioButton JOval;

	public Lsh0708Test() {
		setTitle("마우스로 여러 개의 선  그리기 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new MyPanel());
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		ButtonGroup g = new ButtonGroup(); // 버튼 그룹 객체 생성
		Jline = new JRadioButton("선", true);
		JRect = new JRadioButton("사각형");
		JOval = new JRadioButton("타원");
		g.add(Jline);
		g.add(JRect);
		g.add(JOval);
		c.add(Jline);
		c.add(JRect);
		c.add(JOval);
		setSize(300, 300);
		setVisible(true);

	}

	class MyPanel extends JPanel {
		private Vector<Point> vStart = new Vector<Point>();
		private Vector<Point> vEnd = new Vector<Point>();
		private Vector<Point> RStart = new Vector<Point>();
		private Vector<Point> REnd = new Vector<Point>();
		private Vector<Point> OStart = new Vector<Point>();
		private Vector<Point> OEnd = new Vector<Point>();

		public MyPanel() {
			addMouseListener(new MouseAdapter() {

				public void mousePressed(MouseEvent e) {
					Point startP = e.getPoint();
					if (Jline.isSelected()) {
						vStart.add(startP);
					} else if (JRect.isSelected()) {
						RStart.add(startP);
					} else if (JOval.isSelected()) {
						OStart.add(startP);
					}
				}

				public void mouseReleased(MouseEvent e) {
					Point endP = e.getPoint();
					if (Jline.isSelected()) {
						vEnd.add(endP);
					} else if (JRect.isSelected()) {
						REnd.add(endP);
					} else if (JOval.isSelected()) {
						OEnd.add(endP);
					}

					// 패널의 다시 그리기를 요청한다.
					repaint(); // 주목
				}
			});
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.BLUE);

			for (int i = 0; i < vStart.size(); i++) {
				Point s = vStart.elementAt(i);
				Point e = vEnd.elementAt(i);

				g.drawLine((int) s.getX(), (int) s.getY(), (int) e.getX(), (int) e.getY());
			}
			for (int j = 0; j < RStart.size(); j++) {
				Point Rs = RStart.elementAt(j);
				Point Re = REnd.elementAt(j);
				int x = Math.min(Rs.x, Re.x);
				int y = Math.min(Rs.y, Re.y);
				int width = Math.abs(Rs.x - Re.x);
				int height = Math.abs(Rs.y - Re.y);
				g.drawRect(x, y, width, height); // 타원 그리기
			}
			for (int k = 0; k < OStart.size(); k++) {
				Point Os = OStart.elementAt(k);
				Point Oe = OEnd.elementAt(k);
				int x = Math.min(Os.x, Oe.x);
				int y = Math.min(Os.y, Oe.y);
				int width = Math.abs(Os.x - Oe.x);
				int height = Math.abs(Os.y - Oe.y);
				g.drawOval(x, y, width, height); // 타원 그리기
			}

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Lsh0708Test();
	}

}
