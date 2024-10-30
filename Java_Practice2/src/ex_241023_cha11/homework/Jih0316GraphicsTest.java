package ex_241023_cha11.homework;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Jih0316GraphicsTest extends JFrame { // Jih0316GraphicsTest class 시작
	private boolean drawLine = true;
	private JButton rectButton;
	private JButton lineButton;

	public Jih0316GraphicsTest() { // Jih0316GraphicsTest 생성자 시작
		setTitle("선과 사각형 그리기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);

		jih0316Panel panel = new jih0316Panel();

		// "선" 버튼
		lineButton = new JButton("선");
		lineButton.addActionListener(new ActionListener() { // lineButton ActionListener 시작
			@Override
			public void actionPerformed(ActionEvent e) {
				drawLine = true;
				lineButton.setBackground(Color.LIGHT_GRAY);
				rectButton.setBackground(null);
			} // actionPerformed 종료
		} // ActionListener 종료
		); // addActionListener 종료

		// "사각형" 버튼
		rectButton = new JButton("사각형");
		rectButton.addActionListener(new ActionListener() { // rectButton ActionListener 시작
			@Override
			public void actionPerformed(ActionEvent e) {
				drawLine = false;
				rectButton.setBackground(Color.LIGHT_GRAY);
				lineButton.setBackground(null);
			} // actionPerformed 종료
		} // ActionListener 종료
		); // addActionListener 종료

		// "지우기" 버튼
		JButton clearButton = new JButton("지우기");
		clearButton.addActionListener(new ActionListener() { // clearButton ActionListener 시작
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.clearDrawings();
			} // actionPerformed 종료
		} // ActionListener 종료
		); // addActionListener 종료

		// 크기 설정
		setLayout(null);
		lineButton.setBounds(10, 10, 100, 30);
		rectButton.setBounds(120, 10, 100, 30);
		clearButton.setBounds(230, 10, 100, 30);
		panel.setBounds(0, 50, 500, 450);

		// 버튼 추가, 패널 추가
		add(lineButton);
		add(rectButton);
		add(clearButton);
		add(panel);

		setVisible(true);
	} // Jih0316GraphicsTest 생성자 종료

	public static void main(String[] args) {
		new Jih0316GraphicsTest();
	} // main 종료

	class jih0316Panel extends JPanel { // jih0316Panel 클래스 시작
		private Vector<Point> lineStart = new Vector<Point>();
		private Vector<Point> lineEnd = new Vector<Point>();
		private Vector<Point> rectStart = new Vector<Point>();
		private Vector<Point> rectEnd = new Vector<Point>();
		private Point tempStart = null;
		private Point tempEnd = null;

		public jih0316Panel() { // jih0316Panel 생성자 시작
			addMouseListener(new MouseAdapter() { // MouseListener 시작
				public void mousePressed(MouseEvent e) {
					tempStart = e.getPoint();
				} // mousePressed 종료

				public void mouseReleased(MouseEvent e) {
					if (drawLine) {
						lineStart.add(tempStart);
						lineEnd.add(e.getPoint());
					} // if 종료
					else {
						rectStart.add(tempStart);
						rectEnd.add(e.getPoint());
					} // else 종료
					tempStart = null;
					tempEnd = null;
					repaint();
				} // mouseReleased 종료
			}); // addMouseListener 종료

			addMouseMotionListener(new MouseMotionAdapter() { // MouseMotionListener 시작
				public void mouseDragged(MouseEvent e) {
					tempEnd = e.getPoint();
					repaint();
				} // mouseDragged 종료
			} // MouseMotionAdapter 종료
			); // addMouseMotionListener 종료
		} // jih0316Panel 생성자 종료

		public void clearDrawings() {
			lineStart.clear();
			lineEnd.clear();
			rectStart.clear();
			rectEnd.clear();
			tempStart = null;
			tempEnd = null;
			repaint();
		} // clearDrawings 종료

		@Override
		protected void paintComponent(Graphics g) { // paintComponent 시작
			super.paintComponent(g);
			g.setColor(Color.DARK_GRAY);

			for (int i = 0; i < lineStart.size(); i++) {
				Point s = lineStart.elementAt(i);
				Point e = lineEnd.elementAt(i);
				g.drawLine(s.x, s.y, e.x, e.y);
			} // for 종료

			for (int i = 0; i < rectStart.size(); i++) {
				Point s = rectStart.elementAt(i);
				Point e = rectEnd.elementAt(i);
				int x = Math.min(s.x, e.x);
				int y = Math.min(s.y, e.y);
				int width = Math.abs(s.x - e.x);
				int height = Math.abs(s.y - e.y);
				g.drawRect(x, y, width, height);
			} // for 종료

			if (tempStart != null && tempEnd != null) {
				g.setColor(Color.BLUE);
				if (drawLine) {
					g.drawLine(tempStart.x, tempStart.y, tempEnd.x, tempEnd.y);
				} // if 종료
				else {
					int x = Math.min(tempStart.x, tempEnd.x);
					int y = Math.min(tempStart.y, tempEnd.y);
					int width = Math.abs(tempStart.x - tempEnd.x);
					int height = Math.abs(tempStart.y - tempEnd.y);
					g.drawRect(x, y, width, height);
				} // else 종료
			} // if 종료
		} // paintComponent 종료
	} // jih0316Panel 클래스 종료
} // Jih0316GraphicsTest 클래스 종료
