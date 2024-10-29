package ex_241023_cha10;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class ListEx extends JFrame {
	private String[] fruits = { "apple", "banana", "kiwi", "mango", "pear", "peach", "berry", "strawberry",
			"blackberry" };

	private ImageIcon[] images = { new ImageIcon("images/test11.png"), new ImageIcon("images/test22.png"),
			new ImageIcon("images/test44.png"), new ImageIcon("images/test11.png") };

	public ListEx() {
		setTitle("리스트 만들기 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		// 문자열 배열 추가
		JList<String> strList = new JList<String>(fruits);
		// 패널에 추가.
		c.add(strList);

		// 이미지들을 추가 
		JList<ImageIcon> imageList = new JList<ImageIcon>();
		imageList.setListData(images);
		// 패널에 추가.
		c.add(imageList);

		JList<String> scrollList = new JList<String>(fruits);
		c.add(new JScrollPane(scrollList));
		setSize(300, 300);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ListEx();
	}

}
