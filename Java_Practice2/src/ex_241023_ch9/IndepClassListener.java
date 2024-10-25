package ex_241023_ch9;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;

public class IndepClassListener extends JFrame {
	public IndepClassListener() {
		setTitle("Action 이벤트 리스너 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 해당 최상위 컨테이너(윈도우 창)에서의 사용할 패널을 가리킴. 
		Container c = getContentPane();
		// flex 와 비슷., 나란히 배치. 
		c.setLayout(new FlowLayout());
		// UI 버튼 생성
		JButton btn = new JButton("Action");
		// 버튼에 이벤트 리스너를 추가하는 부분. 
		btn.addActionListener(new MyActionListener());
		// UI 컨테이너 패널에 버튼 추가. 
		c.add(btn);
		setSize(250, 120);
		setVisible(true);

	}

	public static void main(String[] args) {
		new IndepClassListener();
	}

}

// 독립된 클래스로 이벤트 리스너를 작성한다.
class MyActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// 버튼 클릭(화면에 클릭 대상이 보이지만, 컴퓨터 입장에서는 몰라요. 
		// 무엇을 클릭 하는지 대상체 ?
		JButton b = (JButton) e.getSource();
		// 버튼의 문자열의 내용으로 현재 구분 중. 
		if (b.getText().equals("Action"))
			b.setText("액션");
		else
			b.setText("Action");
	}

}







