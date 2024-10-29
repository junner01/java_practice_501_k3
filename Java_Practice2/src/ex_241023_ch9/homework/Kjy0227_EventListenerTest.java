package ex_241023_ch9.homework;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Kjy0227_EventListenerTest extends JFrame {
	private JLabel la1 = new JLabel("Apple");
	private JLabel la2 = new JLabel("Banana");
	private JLabel la3 = new JLabel("House");
	private JLabel la4 = new JLabel("Car");
	
	public Kjy0227_EventListenerTest() {
		setTitle("Kjy0227_EventListener 숙제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		
		la1.addMouseListener(new MyMouseAdapter());
		la2.addMouseListener(new MyMouseAdapter());
		la3.addMouseListener(new MyMouseAdapter());
		la4.addMouseListener(new MyMouseAdapter());
		la1.setSize(50, 30);
		la2.setSize(50, 30);
		la3.setSize(50, 30);
		la4.setSize(50, 30);
		la1.setLocation(50, 30);
		la2.setLocation(150, 30);
		la3.setLocation(250, 30);
		la4.setLocation(340, 30);
		
		c.add(la1);
		c.add(la2);
		c.add(la3);
		c.add(la4);
		
		setSize(500, 300);
		setVisible(true);
	}
	
	class MyMouseAdapter extends MouseAdapter {
		// Press
		public void mousePressed(MouseEvent e) {
			JLabel clickedLabel = (JLabel) e.getSource();
			if("Apple".equals(clickedLabel.getText())) {
				la1.setText("사과");
			} else if("Banana".equals(clickedLabel.getText())) {
				la2.setText("바나나");
			} else if("House".equals(clickedLabel.getText())) {
				la3.setText("집");
			} else {
				la4.setText("자동차");
			}
		}

		// Release
		@Override
		public void mouseReleased(MouseEvent e) {
			JLabel releasedLabel = (JLabel) e.getSource();
			if("사과".equals(releasedLabel.getText())) {
				la1.setText("Apple");
			} else if("바나나".equals(releasedLabel.getText())) {
				la2.setText("Banana");
			} else if("집".equals(releasedLabel.getText())) {
				la3.setText("House");
			} else {
				la4.setText("Car");
			}
		}
	}

	public static void main(String[] args) {
		new Kjy0227_EventListenerTest();
	}

}
