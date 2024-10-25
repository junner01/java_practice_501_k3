package ex_241023_ch9;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MouseAdapterClass extends JFrame {
	// 재사용하는 라벨 한개. 싱글톤, 인스턴스 하나로
	private JLabel la = new JLabel("Hello"); // "Hello" 레이블

	public MouseAdapterClass() {
		// 프레임 창의 제목
		setTitle("Mouse 이벤트 예제");
		// 창을 닫기 클릭시, 화면도 안보이고, 프로그램 정상 종료
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 프레임에서, 기본 패널을 선택. 
		Container c = getContentPane();
		// 프레임의 기본 패널에 -> 이벤트 리스너를 추가. 
		c.addMouseListener(new MyMouseAdapter());
		// 배치 관리자를 선택 안함. 사용자 직접 라벨의 크기 및 위치를 조정 가능. 
		c.setLayout(null);

		la.setSize(50, 20); // 레이블의 크기 50x20 설정
		la.setLocation(30, 30); // 레이블의 위치 (30,30)으로 설정
		// 패널에 , 라벨을 붙이는 작업. 
		c.add(la);
		// 창의 크기. 
		setSize(200, 200);
		// 창의 보이게 하는 속성.
		setVisible(true);
	}

	class MyMouseAdapter extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			
			int x = e.getX(); // 마우스의 클릭 좌표 x
			int y = e.getY(); // 마우스의 클릭 좌표 y
			la.setLocation(x, y); // (x,y) 위치로 레이블 이동
		}
		
	} // Listener

	public static void main(String[] args) {
		new MouseAdapterClass();
	} // main 
} // 마우스 리스너 클래스. 








