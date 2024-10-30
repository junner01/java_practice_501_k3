package ex_241023_cha11.homework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Cgw0910Canvas extends JPanel {
	Point start, end = null;
	public int drawingMode = 0; // 1 = DrawLine, 2 = DrawCircle
	private ArrayList<Cgw0910Shape> shapes = new ArrayList<>();

	public Cgw0910Canvas() {
		setBackground(Color.white);
		Cgw0910MyMouseListener listener = new Cgw0910MyMouseListener();
		addMouseListener(listener);
		addMouseMotionListener(listener);
	}

	class Cgw0910MyMouseListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			start = e.getPoint();
		}

		public void mouseDragged(MouseEvent e) {
			if (drawingMode == 1) {
				end = e.getPoint();
				if (start != null && end != null) {
					shapes.add(new Cgw0910Shape(drawingMode, start.x, start.y, end.x, end.y, Color.RED));
				}
				repaint();
				start = e.getPoint();
			}
		}

		public void mouseReleased(MouseEvent e) {
			if (drawingMode != 1) {
				end = e.getPoint();
				if (start != null && end != null) {
					shapes.add(new Cgw0910Shape(drawingMode, start.x, start.y, end.x, end.y, Color.RED));
				}
				System.out.println(start);
				System.out.println(end);
				repaint();
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Cgw0910Shape shape : shapes) {
			shape.draw(g);
		}
	}

	public void clearCanvas() {
		shapes.clear();
		repaint();
	}
}