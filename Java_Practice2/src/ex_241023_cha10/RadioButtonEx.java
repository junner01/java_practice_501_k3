package ex_241023_cha10;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class RadioButtonEx extends JFrame {
	// 인스턴스 멤버, 전역으로 사용이 되고 있다. 
	private JRadioButton[] fruits = new JRadioButton[3];
	private String[] names = { "사과", "배", "체리" };
	private JLabel selectedFruitLabel;

	public RadioButtonEx() {
		setTitle("라디오버튼 만들기 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		//
		selectedFruitLabel = new JLabel("선택 된 과일은 : ");
		MyItemRadioListener listener = new MyItemRadioListener(fruits, selectedFruitLabel);

		// 라디오 버튼을 그룹에 추가 하고, 패널에 붙이는 작업.
		ButtonGroup g = new ButtonGroup(); // 버튼 그룹 객체 생성
		for (int i = 0; i < fruits.length; i++) {

			fruits[i] = new JRadioButton(names[i]);

			fruits[i].setBorderPainted(true);

			g.add(fruits[i]);
			c.add(fruits[i]);

			fruits[i].addItemListener(listener);

		}

		c.add(selectedFruitLabel);

		setSize(250, 150);
		setVisible(true);
	}

	// Item 리스너 구현
	class MyItemRadioListener implements ItemListener {
		// 인스턴스 멤버,
		private String selectedFruit = ""; // 가격의 합
		// 결과 라벨
		private JLabel selectedFruitLabel;
		private JRadioButton[] fruits;

		public MyItemRadioListener(JRadioButton[] fruits, JLabel selectedFruitLabel) {
			this.fruits = fruits;
			this.selectedFruitLabel = selectedFruitLabel;
		}

		// 체크박스 -> 구성품 -> 아이템이라고 부름. 각 요소가 아이템입니다.
		// 각 아이템 요소들이 체크 또는 체크 해제가 될 때마다, 이 메서드가 호출이 됨.
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) { // 아이템 요소가 체크 될경우
				// event.getItem() , 어느 아이템을 선택 했나요? 사과 라는 체크박스 타입을 선택
				// , JCheckBox [] fruits
				// "돼지국밥 9500원, 회덮밥 10000원, 돌솥비빔밥 7000원, 찜닭 20000원, 햄버거 8000원"));
				if (event.getItem() == fruits[0]) {
					selectedFruit = fruits[0].getText();
					selectedFruitLabel.setText("현재 선택된 과일은 " + selectedFruit + " 입니다.");
				} else if (event.getItem() == fruits[1]) {
					selectedFruit = fruits[1].getText();
					selectedFruitLabel.setText("현재 선택된 과일은 " + selectedFruit + " 입니다.");
				} else if (event.getItem() == fruits[2]) {
					selectedFruit = fruits[2].getText();
					selectedFruitLabel.setText("현재 선택된 과일은 " + selectedFruit + " 입니다.");
				}

			}
		}
	}

	public static void main(String[] args) {
		new RadioButtonEx();
	}
}
