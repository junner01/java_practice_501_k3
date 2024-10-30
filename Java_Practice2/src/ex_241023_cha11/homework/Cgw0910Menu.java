package ex_241023_cha11.homework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;
import javax.swing.*;

public class Cgw0910Menu extends JFrame {
	Cgw0910ToolBar toolBar = new Cgw0910ToolBar();
	Cgw0910Canvas canvas = null;

	public Cgw0910Menu() {
		setTitle("버튼 클릭, 선, 타원, 사각형 그리기 응용 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1440, 800);
		add(toolBar, BorderLayout.NORTH);
		setVisible(true);
	}

	class Cgw0910ToolBar extends JToolBar {
		private HashMap<String, JButton> buttons = new HashMap<>();

		public Cgw0910ToolBar() {
			setBackground(Color.LIGHT_GRAY);
			addButton("drawLine", "선 그리기");
			addButton("drawCircle", "원 그리기");
			addButton("drawSquare", "네모 그리기");
			addButton("clear", "전체 지우기");
			addButton("addCanvas", "캔버스 만들기");
		}

		private void addButton(String key, String label) {
			JButton button = new JButton(label);
			buttons.put(key, button);
			add(button);

			button.addActionListener(e -> handleButtonAction(key));
		}
	}

	private void handleButtonAction(String key) {
		switch (key) {
		case "drawLine":
			if (canvas != null) {
				canvas.drawingMode = 1;
			}
			break;
		case "drawCircle":
			if (canvas != null) {
				canvas.drawingMode = 2;
			}
			break;
		case "drawSquare":
			if (canvas != null) {
				canvas.drawingMode = 3;
			}
			break;
		case "clear":
			if (canvas != null) {
				canvas.clearCanvas();
			}
			break;
		case "addCanvas":
			if (canvas == null) {
				canvas = new Cgw0910Canvas();
				add(canvas, BorderLayout.CENTER);
				revalidate();
				repaint();
			}
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + key);
		}
	}
}