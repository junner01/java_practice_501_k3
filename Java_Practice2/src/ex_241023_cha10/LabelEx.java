package ex_241023_cha10;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LabelEx extends JFrame {
	public LabelEx() {
		setTitle("레이블 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		//문자열 라벨. 
		JLabel textLabel = new JLabel("제임스 고슬링 입니더!");
		c.add(textLabel);
		
		// ImageIcon 타입으로 이미지 폴더 경로에 있는 이미지를 선택.
		ImageIcon img = new ImageIcon("images/test11.png");
		
		// 주의사항, 이미지 폴더 경로는, 자바프로젝트 바로 밑에 만들기. 
		// 이미지 라벨.
		JLabel imageLabel = new JLabel(img);
		c.add(imageLabel);
		
		//3. 문자열 + 이미지 , 라벨
		ImageIcon icon = new ImageIcon("images/test22.png");
		JLabel label = new JLabel("커피한잔 하실래예, 전화주이소", icon, SwingConstants.CENTER);
		c.add(label);
		
		
		setSize(300, 500);
		setVisible(true);
		
	}

	public static void main(String[] args) {
		new LabelEx();
	}
}
