package ex_241023_cha10;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;


	public class CheckBoxItemEventEx extends JFrame {
		
		// 인스턴스 멤버, 전역으로 사용이 되고 있다. 
		private JCheckBox[] fruits = new JCheckBox[3];
		private String[] names = { "사과", "배", "체리" };
		private JLabel sumLabel;

		// 생성자, 
		public CheckBoxItemEventEx() {
			setTitle("체크박스와 ItemEvent 예제");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Container c = getContentPane();
			c.setLayout(new FlowLayout());
			
			// 패널에 라벨 붙이기 
//			JLabel jLabel = new JLabel("사과 100원, 배 500원, 체리 2000원")
			c.add(new JLabel("사과 100원, 배 500원, 체리 2000원"));
			
			//이벤트 처리기 작업.
			MyItemListener listener = new MyItemListener();
			
			//
			for (int i = 0; i < fruits.length; i++) {
				// fruits[0] 요소로, JCheckBox 인스턴스, 
				// String[] names = { "사과", "배", "체리" };
				// 각 체크박스 구성하기.
				fruits[i] = new JCheckBox(names[i]);
				// 각 체크박스 테두리 설정. 
				fruits[i].setBorderPainted(true);
				// 패널에 체크박스 추가하기. 
				c.add(fruits[i]);
				// 각 체크 박스 마다, 이벤트 처리기 붙이기 작업. 
				// 이벤트 처리기 붙이는 방법1) 독립 클래스, 방법2) 익명클래스. 
				fruits[i].addItemListener(listener);
				//fruits[i].addItemListener(new MyItemListener());
			}
			sumLabel = new JLabel("현재 0 원 입니다.");
			c.add(sumLabel);
			setSize(250, 200);
			setVisible(true);
		}

	// Item 리스너 구현
		class MyItemListener implements ItemListener {
			// 인스턴스 멤버,
			private int sum = 0; // 가격의 합
			
			// 체크박스 -> 구성품 -> 아이템이라고 부름. 각 요소가 아이템입니다. 
			// 각 아이템 요소들이 체크 또는 체크 해제가 될 때마다, 이 메서드가 호출이 됨. 
			public void itemStateChanged(ItemEvent event) {
				// 아이템 요소 중에서, 예를 들어서 사과를 선택 했음. 
				//event.getStateChange() -> 아이템 요소의 체크 여부 확인.
				if (event.getStateChange() == ItemEvent.SELECTED) { // 아이템 요소가 체크 될경우
					// event.getItem() , 어느 아이템을 선택 했나요? 사과 라는 체크박스 타입을 선택
					//  , JCheckBox [] fruits
					if (event.getItem() == fruits[0])
						sum += 100;
					else if (event.getItem() == fruits[1])
						sum += 500;
					else
						sum += 2000;
				} else { // 아이템 요소가 체크 안 될 경우
					if (event.getItem() == fruits[0])
						sum -= 100;
					else if (event.getItem() == fruits[1])
						sum -= 500;
					else
						sum -= 2000;
				}
				sumLabel.setText("현재 " + sum + "원 입니다.");
			}
		}

		public static void main(String[] args) {
			new CheckBoxItemEventEx();
		}
}
