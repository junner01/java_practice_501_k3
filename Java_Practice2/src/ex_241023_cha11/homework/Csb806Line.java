package ex_241023_cha11.homework;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Csb806Line extends JFrame {
	//직선을 그리는 모드가 기본 설정
	private boolean drawLine = true;
	
	public Csb806Line() {
		setTitle("Subin's DrawBoard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//버튼 만들기
		JPanel buttonPanel = new JPanel();
		JButton lineButton = new JButton("직선그리기");
		JButton rectButton = new JButton("사각형그리기");
		
		//직선버튼실행
		lineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawLine = true;
			}
		});
		//사각형버튼실행
		rectButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				drawLine = false;
			}
		});
		
		buttonPanel.add(lineButton);
		buttonPanel.add(rectButton);
		
		drawBoard drawPanel = new drawBoard();
		setLayout(new BorderLayout());
		add(buttonPanel, BorderLayout.NORTH);
		add(drawPanel, BorderLayout.CENTER);
		
		setSize(500, 500);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Csb806Line();
	}

	class drawBoard extends JPanel {
		private Vector<Point> vStart = new Vector<Point>();
		private Vector<Point> vEnd = new Vector<Point>();

		public drawBoard() {
			addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					Point startP = e.getPoint();
					vStart.add(startP);
				}

				public void mouseReleased(MouseEvent e) {
					Point endP = e.getPoint();
					vEnd.add(endP);
                    // 패널의 다시 그리기를 요청한다.
					repaint(); // 주목
				}
			});
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.RED);
			for (int i = 0; i < vStart.size(); i++) {
				Point s = vStart.elementAt(i);
				Point e = vEnd.elementAt(i);
			
				if(drawLine) {
					//직선 그리기
					g.drawLine(s.x, s.y, e.x, e.y);
				} else {
					//사각형 그리기
					int x = Math.min(s.x,e.x);
					int y = Math.min(s.y,e.y);
					int width = Math.abs(s.x-e.x);
					int height = Math.abs(s.y-e.y);
					g.drawRect(x,y,width,height);
				}
			}
		}
	}
}
