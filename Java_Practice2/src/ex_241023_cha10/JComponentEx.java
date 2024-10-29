package ex_241023_cha10;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;

public class JComponentEx extends JFrame {
	public JComponentEx() {
		// 제목
		super("JComponent의 공통 메소드 예제");
		// 프레임에 있는 컨테이너 가져오기
		Container c = getContentPane();
		// 컨테이너 배치관리자, flex
		c.setLayout(new FlowLayout());
		// 스윙 컴포넌트들 중에서 버튼 종류.
		JButton b1 = new JButton("Magenta/Yellow Button");
		JButton b2 = new JButton(" Disabled Button ");
		JButton b3 = new JButton("getX(), getY()");
		// 각버튼의 간단한 속성 변경하는 메서드
		b1.setBackground(Color.YELLOW);
		b1.setForeground(Color.MAGENTA);
		//
		b1.setFont(new Font("Arial", Font.ITALIC, 20));
		b2.setEnabled(false);
		// 버튼의 클릭 리스너 설정해서, 익명 클래스 구성.
//		  b3.addActionListener(new ActionListener() { 
//		   public void actionPerformed(ActionEvent e) { 
//		    JButton b = (JButton)e.getSource(); 
//		    setTitle(b.getX() + "," + b.getY());  
//		   } 
//		  });
		// 람다식으로 변경하는 코드
		b3.addActionListener(e -> {
			JButton b = (JButton) e.getSource();
			setTitle(b.getX() + "," + b.getY());
		});

		c.add(b1);
		c.add(b2);
		c.add(b3);
		setSize(260, 200);
		setVisible(true);
	};

	public static void main(String[] args) {
		new JComponentEx();
	}
}
