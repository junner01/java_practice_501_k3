package ex_241023_cha11.homework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Lsh1208_241028homework_Draw extends JFrame {

	private String selectedShape = "line"; // 기본 도형은 선
	private Color selectedColor = Color.BLACK; // 기본 색상은 검정색
	private DrawPanel drawingPanel; // 그리기 패널

	public Lsh1208_241028homework_Draw() {
		setTitle("Custom Radio Button Example");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 1000);
		setLayout(new BorderLayout());

		// 라디오 버튼을 담는 상단 패널
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS)); // 상하 배치를 위해 BoxLayout 사용

		// 도형 선택 라디오 버튼 패널
		JPanel shapePanel = new JPanel(new FlowLayout());
		JRadioButton lineButton = createShapeRadioButton("선", "line");
		JRadioButton circleButton = createShapeRadioButton("원형", "circle");
		JRadioButton squareButton = createShapeRadioButton("사각형", "square");

		ButtonGroup shapeGroup = new ButtonGroup();
		shapeGroup.add(lineButton);
		shapeGroup.add(circleButton);
		shapeGroup.add(squareButton);

		shapePanel.add(lineButton);
		shapePanel.add(circleButton);
		shapePanel.add(squareButton);

		// 색상 선택 라디오 버튼 패널
		JPanel colorPanel = new JPanel(new FlowLayout());
		JRadioButton blackButton = createColorRadioButton("검정", Color.BLACK);
		JRadioButton redButton = createColorRadioButton("빨강", Color.RED);
		JRadioButton blueButton = createColorRadioButton("파랑", Color.BLUE);

		ButtonGroup colorGroup = new ButtonGroup();
		colorGroup.add(blackButton);
		colorGroup.add(redButton);
		colorGroup.add(blueButton);

		colorPanel.add(blackButton);
		colorPanel.add(redButton);
		colorPanel.add(blueButton);

		// 상단 패널에 도형과 색상 선택 패널 추가
		topPanel.add(shapePanel);
		topPanel.add(colorPanel);

		// 그리기 패널 초기 설정
		drawingPanel = new DrawPanel();
		add(drawingPanel, BorderLayout.CENTER);

		add(topPanel, BorderLayout.NORTH);
		setVisible(true);
	}

	// 도형 라디오 버튼 생성 메서드
	private JRadioButton createShapeRadioButton(String text, String shape) {
		JRadioButton radioButton = new JRadioButton(text);
		radioButton.setBorderPainted(true);
		radioButton.setFocusPainted(false);
		radioButton.setContentAreaFilled(true);
		radioButton.setBackground(Color.LIGHT_GRAY);

		radioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedShape = shape;
				drawingPanel.setDrawingMode(selectedShape);
			}
		});

		return radioButton;
	}

	// 색상 라디오 버튼 생성 메서드
	private JRadioButton createColorRadioButton(String text, Color color) {
		JRadioButton radioButton = new JRadioButton(text);
		radioButton.setBorderPainted(true);
		radioButton.setFocusPainted(false);
		radioButton.setContentAreaFilled(true);
		radioButton.setBackground(Color.LIGHT_GRAY);

		radioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedColor = color;
				drawingPanel.setDrawingColor(selectedColor);
			}
		});

		return radioButton;
	}

	// 그리기 패널 클래스
	class DrawPanel extends JPanel {
		private ArrayList<Shape> shapes = new ArrayList<>(); // 그린 도형 데이터 저장
		private Point start = null, end = null;
		private String drawingMode = "line"; // 기본 모드는 선
		private Color drawingColor = Color.BLACK; // 기본 색상은 검정색

		public DrawPanel() {
			MyMouseListener listener = new MyMouseListener();
			addMouseListener(listener);
			addMouseMotionListener(listener);
		}

		public void setDrawingMode(String mode) {
			drawingMode = mode;
		}

		public void setDrawingColor(Color color) {
			drawingColor = color;
		}

		class MyMouseListener extends MouseAdapter {
			public void mousePressed(MouseEvent e) {
				start = e.getPoint();
			}

			public void mouseReleased(MouseEvent e) {
				end = e.getPoint();
				if (start != null && end != null) {
					shapes.add(new Shape(drawingMode, drawingColor, start, end));
				}
				start = null;
				end = null;
				repaint();
			}

			public void mouseDragged(MouseEvent e) {
				end = e.getPoint();
				repaint();
			}
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for (Shape shape : shapes) {
				shape.draw(g);
			}

			if (start != null && end != null) {
				new Shape(drawingMode, drawingColor, start, end).draw(g);
			}
		}
	}

	class Shape {
		String type;
		Color color;
		Point start, end;

		public Shape(String type, Color color, Point start, Point end) {
			this.type = type;
			this.color = color;
			this.start = start;
			this.end = end;
		}

		public void draw(Graphics g) {
			g.setColor(color);
			int x = Math.min(start.x, end.x);
			int y = Math.min(start.y, end.y);
			int width = Math.abs(start.x - end.x);
			int height = Math.abs(start.y - end.y);

			switch (type) {
			case "line":
				g.drawLine(start.x, start.y, end.x, end.y);
				break;
			case "circle":
				g.drawOval(x, y, width, height);
				break;
			case "square":
				g.drawRect(x, y, width, height);
				break;
			}
		}
	}

	public static void main(String[] args) {
		new Lsh1208_241028homework_Draw();
	}
}
