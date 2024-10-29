package ex_241023_ch9.homework;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class JHJ_WordCard extends JFrame implements MouseListener {

	JLabel appleLabel = new JLabel("apple");
	JLabel bananaLabel = new JLabel("banana");
	JLabel houseLabel = new JLabel("house");
	JLabel carLabel = new JLabel("car");

	public JHJ_WordCard() {
		setTitle("미니실습");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new GridLayout(2, 2));

		c.add(appleLabel);
		c.add(bananaLabel);
		c.add(houseLabel);
		c.add(carLabel);

		appleLabel.addMouseListener(this);
		bananaLabel.addMouseListener(this);
		houseLabel.addMouseListener(this);
		carLabel.addMouseListener(this);

		setSize(400, 200);
		setVisible(true);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel sourceLabel = (JLabel) e.getSource();
		switch (sourceLabel.getText()) {
		case "apple":
			sourceLabel.setText("사과");
			break;
		case "banana":
			sourceLabel.setText("바나나");
			break;
		case "house":
			sourceLabel.setText("집");
			break;
		case "car":
			sourceLabel.setText("차");
			break;
		case "사과":
			sourceLabel.setText("apple");
			break;
		case "바나나":
			sourceLabel.setText("banana");
			break;
		case "집":
			sourceLabel.setText("house");
			break;
		case "차":
			sourceLabel.setText("car");
			break;

		}

	}
	
	 @Override
	    public void mousePressed(MouseEvent e) {}
	    @Override
	    public void mouseReleased(MouseEvent e) {}
	    @Override
	    public void mouseEntered(MouseEvent e) {}
	    @Override
	    public void mouseExited(MouseEvent e) {}

	    public static void main(String[] args) {
	        new JHJ_WordCard();
	    }


	}
