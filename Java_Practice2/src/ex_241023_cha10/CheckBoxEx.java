package ex_241023_cha10;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class CheckBoxEx extends JFrame {
	public CheckBoxEx() {
		setTitle("체크박스 만들기 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		// 3개의 체크박스를 생성한다.
		JCheckBox apple = new JCheckBox("사과");
		JCheckBox pear = new JCheckBox("배", true);
		JCheckBox cherry = new JCheckBox("체리");
		ImageIcon normalIcon = new ImageIcon("images/test11.png");
		JCheckBox test11 = new JCheckBox("test11",normalIcon,true);
		
		c.add(apple);
		c.add(pear);
		c.add(cherry);
		
		c.add(test11);
		
		setSize(250, 150);
		setVisible(true);
	}

	public static void main(String[] args) {
		new CheckBoxEx();
	}
}
