package ex_241023_ch9;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MouseEventAllEx extends JFrame {
	private JLabel la = new JLabel(" Move Me");

	public MouseEventAllEx() {
		setTitle("MouseListener와 MouseMotionListener 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		
		// 해당 프레임의 패널에, 마우스 이벤트 리스너를 설정.
		MyMouseListener listener = new MyMouseListener();
		c.addMouseListener(listener);
		c.addMouseMotionListener(listener);
		// 배치관리자 설정 안함. 사용자가 직접 위치를 지정하기위한 준비.
		c.setLayout(null);

		la.setSize(80, 20);
		la.setLocation(100, 80);
		c.add(la); // 레이블 컴포넌트 삽입

		setSize(300, 200);
		setVisible(true);
	}

	// 마우스 이벤트 리스너 설정. 인터페이스는 다중 구현이 가능. 
	class MyMouseListener implements MouseListener, MouseMotionListener {
		// 마우스 클릭시 동작하는 함수
		public void mousePressed(MouseEvent e) {
			// 라벨 위치 지정. 
			la.setLocation(e.getX(), e.getY());
			// 프레임 창 제목에, 좌표를 표기. 
			setTitle("mousePressed(" + e.getX() + "," + e.getY() + ")");
		}
		// 마우스 클릭 해제시 동작하는 함수
		public void mouseReleased(MouseEvent e) {
			la.setLocation(e.getX(), e.getY());
			setTitle("mouseReleased(" + e.getX() + "," + e.getY() + ")");
		}

		public void mouseClicked(MouseEvent e) {
		}

		// 마우스 컴포넌트 커서가 올라갈 경우(호버시) 동작하는 함수
		public void mouseEntered(MouseEvent e) {
			Component comp = (Component) e.getSource();
			comp.setBackground(Color.CYAN);
		}
		// 마우스 컴포넌트 커서가 나갈 경우 동작하는 함수
		public void mouseExited(MouseEvent e) {
			Component comp = (Component) e.getSource();
			comp.setBackground(Color.YELLOW);
			setTitle("mouseExited(" + e.getX() + "," + e.getY() + ")");
		}
		// 마우스 드래그 할 경우 동작하는 함수
		public void mouseDragged(MouseEvent e) {
			la.setLocation(e.getX(), e.getY());
			setTitle("mouseDragged(" + e.getX() + "," + e.getY() + ")");
		}
		// 마우스 움직일 경우 동작하는 함수
		public void mouseMoved(MouseEvent e) {
			la.setLocation(e.getX(), e.getY());
			setTitle("mouseMoved (" + e.getX() + "," + e.getY() + ")");
		}
	}

	public static void main(String[] args) {
		new MouseEventAllEx();
	}

}
