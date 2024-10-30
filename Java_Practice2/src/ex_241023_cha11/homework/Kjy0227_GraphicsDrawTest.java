package ex_241023_cha11.homework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Kjy0227_GraphicsDrawTest extends JFrame {
	String type = "li"; // 그리기 타입
	
	public Kjy0227_GraphicsDrawTest() {
		setTitle("직선 및 사각형 그리기 실습");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new Kjy0227_Panel();
		
		JButton btnLi = new JButton("직선");
		btnLi.addActionListener(e -> {
			type = "li";
		});
		JButton btnRct = new JButton("사각형");
		btnRct.addActionListener(e -> {
			type = "rct";
		});
		
		panel.add(btnLi);
		panel.add(btnRct);
		setContentPane(panel);
		setSize(800, 500);
		setVisible(true);
	}
	
	class Kjy0227_Panel extends JPanel {
		private Vector<Point> vStart = new Vector<Point>();
		private Vector<Point> vEnd = new Vector<Point>();
		private Vector<String> vType = new Vector<String>();
		
		public Kjy0227_Panel() {
			addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					Point startP = e.getPoint();
					vStart.add(startP);
				}

				public void mouseReleased(MouseEvent e) {
					Point endP = e.getPoint();
					vEnd.add(endP);
					vType.add(type);
					
					repaint(); // 전부 다시 그리기
				}
			});
		}
		
		// repaint() 호출 될 때마다 작동
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.GRAY);
			
			for (int i = 0; i < vStart.size(); i++) {
				Point s = vStart.elementAt(i);
				Point e = vEnd.elementAt(i);
				// type이 직선일 때
				if("li".equals(vType.elementAt(i))) {
					g.drawLine((int) s.getX(), (int) s.getY(), (int) e.getX(), (int) e.getY());
				}
				// type이 사각형일 때
				if("rct".equals(vType.elementAt(i))) {
					int sX = Math.min((int)s.getX(), (int)e.getX());
					int eX = Math.max((int)s.getX(), (int)e.getX());
					int sY = Math.min((int)s.getY(), (int)e.getY());
					int eY = Math.max((int)s.getY(), (int)e.getY());
					g.drawRect(sX, sY, (eX - sX), (eY - sY));
				}
			}
		}
	}

	public static void main(String[] args) {
		new Kjy0227_GraphicsDrawTest();
	}

}
