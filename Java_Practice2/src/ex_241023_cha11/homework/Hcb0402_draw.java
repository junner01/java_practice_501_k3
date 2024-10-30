package ex_241023_cha11.homework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Hcb0402_draw extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 변수 선언
	private static String currentShapeType = "line";
	private ArrayList<Data> shapes = new ArrayList<>();
	private Point startPoint, endPoint;

	// constructor
	public Hcb0402_draw() {
		// eventlistener 추가 adapter 써서 구현
		this.addMouseListener(new MouseAdapter() {
			// 마우스를 누를 때 시작지점 할당
			@Override
			public void mousePressed(MouseEvent e) {
				startPoint = e.getPoint();
			}
			
			// 마우스를 땔 때 마지막 지점을 할당
			@Override
			public void mouseReleased(MouseEvent e) {
				endPoint = e.getPoint();
				shapes.add(new Data(currentShapeType, startPoint, endPoint));
				// 변수 초기화
				startPoint = null;
				endPoint = null;
				repaint(); // 마우스를 땟을 때 마지막으로 그려짐
			}
		});
		this.addMouseMotionListener(new MouseAdapter() {
			// 마우스를 끌고 있을 때 지점을 계속해서 할당
			@Override	//dragged는 motionlistener
			public void mouseDragged(MouseEvent e) {
				endPoint = e.getPoint(); 
				repaint(); // 계속해서 start, end를 있는 도형 그리기
			}
		});
	}//constructor
	
	
	//그리기 구현
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);

		// 저장된 도형들을 모두 그리기 data클래스에 method를 따로 만들었음
		for (Data shape : shapes) {
			shape.draw(g);
		}

		// start와 end에 수치가 있다면 data에 추가하고 그리기
		if (startPoint != null && endPoint != null) {
			new Data(currentShapeType, startPoint, endPoint).draw(g);
		}
	}//그리기 구현
	
	// data 저장 클래스
	private static class Data {
		private String type;
		private Point start, end;

		public Data(String type, Point start, Point end) {
			this.type = type;
			this.start = start;
			this.end = end;
		}
		//그리기 method 정의
		public void draw(Graphics g) {
			// 자주쓰이는 변수 선언
			int x = Math.min(start.x, end.x);
			int y = Math.min(start.y, end.y);
			int width = Math.abs(start.x - end.x);
			int height = Math.abs(start.y - end.y);

			switch (type) {
			case "line":
				g.drawLine(start.x, start.y, end.x, end.y);
				break;
			case "oval":
				g.drawOval(x, y, width, height);
				break;
			case "rect":
				g.drawRect(x, y, width, height);
				break;
			case "roundRect":
				g.drawRoundRect(x, y, width, height, 50, 50);
			}
		}
	}//data 저장 클래스
	
	
	//clear method
	public void shapesClear() {
		shapes.clear();
		repaint();
	}//clear method
	
	
	//프레임 구현
	private void setFrame() {
		//frame property
        JFrame frame = new JFrame("그림판");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        
        //frame에 올라갈 것들 선언
        JPanel buttonPanel = new JPanel();
        JButton lineButton = new JButton("Line");
        JButton ovalButton = new JButton("Oval");
        JButton rectButton = new JButton("rect");
        JButton roundRectButton = new JButton("roundRect");
        JButton deleteButton = new JButton("delete");

        //button에 리스너 추가
        lineButton.addActionListener(e -> currentShapeType = "line");
        ovalButton.addActionListener(e -> currentShapeType = "oval");
        rectButton.addActionListener(e -> currentShapeType = "rect");
        roundRectButton.addActionListener(e -> currentShapeType = "roundRect");
        deleteButton.addActionListener(e -> shapesClear());
        
        //panel에 button 추가
        buttonPanel.add(lineButton);
        buttonPanel.add(ovalButton);
        buttonPanel.add(rectButton);
        buttonPanel.add(roundRectButton);
        buttonPanel.add(deleteButton);

        //frame에 panel 추가
        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(this, BorderLayout.CENTER);
        frame.setVisible(true);	
	}//프레임 구현
	
	
	public static void main(String[] args) {
		Hcb0402_draw draw = new Hcb0402_draw();
		draw.setFrame();
        }
}
