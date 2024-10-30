package ex_241023_cha11.homework;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Hcb0402_TabandThreadEx extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// int는 일반 변수로 값을 자신이 가지기 때문에 넘겨줘도 서로 다른값을 가진다
	// 따라서 포인터 변수로 바꾸어서 넘겨줄 필요가 있는데 변화해야하는 값의 특성상 Integer은 사용하지 못한다
	// 따라서 가장 간단한 배열로 바꾸어서 넘겨주었다
	private int[] barSize = {0};   
	private Hcb0402_bar bar = new Hcb0402_bar(100, barSize);
	

	
	//constructor
	public Hcb0402_TabandThreadEx(String title) {
		//property
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//property
		

		//container property
		Container c = getContentPane();
		c.setLayout(null);
		//container property
		
		//바의 길이를 표시할 label
		JLabel num = new JLabel();
		//바의 길이를 표시할 label
		
		//bar property
		bar.setBackground(Color.ORANGE);
		bar.setOpaque(true);
		bar.setLocation(20, 50);
		bar.setSize(300, 20);
		//bar property
		
		//container에 bar 추가, container에 리스너 추가, container에 label 추가
		c.add(bar);
		c.add(num);
		c.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				bar.fill();
			}
		});
		//frame property
		setSize(350, 200);
		setVisible(true);
		setLayout(new GridLayout(2, 1, 5, 5));
		c.setFocusable(true);
		c.requestFocus();
		ConsumerThread th = new ConsumerThread(bar);
		th.start(); // 스레드 시작
		
		//barSize를 표시하는 label, barSize[0]는 bar class의 method로 지속적으로 바뀌므로 무한반복문으로 트래킹
		while(true) {
		num.setText("현재 바 : " + barSize[0]);
		}
	}//constructor

	public static void main(String[] args) {
		new Hcb0402_TabandThreadEx("아무키나 빨리 눌러 바 채우기");
	}
}
