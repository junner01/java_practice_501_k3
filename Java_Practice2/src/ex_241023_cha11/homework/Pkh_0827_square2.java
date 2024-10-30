package ex_241023_cha11.homework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Pkh_0827_square2 extends JFrame {
	private MyPanel myPanel;

	public Pkh_0827_square2() {
		setTitle("사각형 그리기 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myPanel = new MyPanel();
		setContentPane(myPanel);
		setSize(300, 300);
		setLayout(null); // 레이아웃을 null로 설정하여 위치를 수동으로 조정
		setVisible(true);

		// 버튼 추가
		JButton toggleButton = new JButton("사각형 그리기 모드 전환");
		toggleButton.setBounds(50, 10, 200, 30); // 버튼 위치와 크기 설정
		toggleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myPanel.toggleRectangleMode();
			}
		});

		add(toggleButton);
	}

	public static void main(String[] args) {
		new Pkh_0827_square2();
	}

	class MyPanel extends JPanel {
		private Vector<Point> vStart = new Vector<Point>();
		private Vector<Point> vEnd = new Vector<Point>();
		private boolean isDrawingRectangle = false; 
		private Point rectStart; 
		private Point rectEnd; 

		public MyPanel() {
			addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					Point startP = e.getPoint();
					if (!isDrawingRectangle) {
						vStart.add(startP);
					} else {
						rectStart = startP; 
					}
				}

				public void mouseReleased(MouseEvent e) {
					Point endP = e.getPoint();
					if (!isDrawingRectangle) {
						vEnd.add(endP);
					} else {
						rectEnd = endP; 
					}
					repaint();
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

		
			if (isDrawingRectangle && rectStart != null && rectEnd != null) {
				g.setColor(Color.BLUE);
				g.drawRect(Math.min(rectStart.x, rectEnd.x), Math.min(rectStart.y, rectEnd.y),
						Math.abs(rectStart.x - rectEnd.x), Math.abs(rectStart.y - rectEnd.y));
			}
		}

		// 사각형 그리기 모드 전환 메서드
		public void toggleRectangleMode() {
			isDrawingRectangle = !isDrawingRectangle;
		}
	}
}
