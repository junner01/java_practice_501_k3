package ex_241023_cha11.homework;

import java.awt.BorderLayout;
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

public class JHJ_draw extends JFrame {
	private boolean drawRectangle = false; // 현재 그리기 모드 (false: 직선, true: 사각형)

	public JHJ_draw() {
		setTitle("마우스로 선과 사각형 그리기 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);

		MyPanel drawingPanel = new MyPanel();
		setLayout(new BorderLayout());
		add(drawingPanel, BorderLayout.CENTER);

		
		JPanel buttonPanel = new JPanel();
		JButton lineButton = new JButton("직선");
		JButton rectangleButton = new JButton("사각형");

		//버튼모드---------------------------------------------
		lineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawRectangle = false; // 직선 모드로 변경
				repaint();
			}
		});
		rectangleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawRectangle = true; // 사각형 모드로 변경
				repaint();
			}
		});

		//버튼 추가하기---------------------------------------------
		buttonPanel.add(lineButton);
		buttonPanel.add(rectangleButton);
		add(buttonPanel, BorderLayout.SOUTH);

		setVisible(true);
	}

	public static void main(String[] args) {
		new JHJ_draw();
	}

	class MyPanel extends JPanel {
		private Vector<Point> vStart = new Vector<>(); 
		private Vector<Point> vEnd = new Vector<>();
		private Vector<Boolean> isRectangle = new Vector<>();
		private Point start = null, end = null;

		public MyPanel() {
			addMouseListener(new MouseAdapter() {

				public void mousePressed(MouseEvent e) {
					start = e.getPoint();
//                    vStart.add(e.getPoint()); // 시작점 저장
				}

				public void mouseReleased(MouseEvent e) {
					end = e.getPoint();

					vStart.add(start);
					vEnd.add(end); 
					isRectangle.add(drawRectangle);
					start = null;
					end = null;
					repaint(); 
				}
			});
			addMouseMotionListener(new MouseAdapter() {

				public void mouseDragged(MouseEvent e) {
					end = e.getPoint(); 
					repaint(); 
				}
			});

		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.BLUE);

			
			for (int i = 0; i < vStart.size(); i++) {
				Point s = vStart.elementAt(i);
				Point e = vEnd.elementAt(i);
				//사각형그리기---------------------------------------------
				if (isRectangle.get(i)) { 
					int x = Math.min(s.x, e.x);
					int y = Math.min(s.y, e.y);
					int width = Math.abs(s.x - e.x);
					int height = Math.abs(s.y - e.y);
					g.drawRect(x, y, width, height);
				} else { //직선그리---------------------------------------------
					g.drawLine(s.x, s.y, e.x, e.y);
				}
			}
			
			//드래그보이게하기---------------------------------------------
			if (start != null && end != null) {
				if (drawRectangle) {
					int x = Math.min(start.x, end.x);
					int y = Math.min(start.y, end.y);
					int width = Math.abs(start.x - end.x);
					int height = Math.abs(start.y - end.y);
					g.drawRect(x, y, width, height);
				} else {
					g.drawLine(start.x, start.y, end.x, end.y);
				}
			}
		}

	}
}
