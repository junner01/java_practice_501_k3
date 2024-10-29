package ex_241023_ch9.homework;

import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Hcb0402_Main_Refac extends JFrame {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		// 변수선언
		HashMap<String, String> words = new HashMap<String, String>();
		Hcb0402_Main_Refac grid = new Hcb0402_Main_Refac();
		grid.setTitle("GridLayout 예제");
		grid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		grid.setSize(300, 1000);
		Scanner scanner = new Scanner(System.in);

		// hashmap에 값 넣기
		words.put("apple", "사과");
		words.put("banana", "바나나");
		words.put("house", "집");
		words.put("car", "차");
		words.put("etc", "기타");
		while (true) {
			int ctr;
			System.out.println("===============단어장===============");
			System.out.println("단어장 확인하기 = 아무 키,단어 추가하기 = 1");
			try {
				ctr = scanner.nextInt();
				if (ctr != 1)
					break;
			} catch (Exception e) {
				break;
			}
			System.out.println("영단어 입력 & 뜻 입력");
			words.put(scanner.next(), scanner.next());
		}
		// words가 변경 끝났으니 word의존 method사용
		grid.setLayout(new GridLayout(words.size(), 2, 5, 5));
		Set<Map.Entry<String, String>> wordsSet = words.entrySet();
		ArrayList<JButton> jb = new ArrayList<JButton>();
		ArrayList<JLabel> jl = new ArrayList<JLabel>();
		// map의 값을 set으로 바꾼 뒤 iterator을 쓸 수 있으므로 foreach로 변경
		// event의 getsource와 반응해야하는 곳이 다르므로 인덱싱하는 배열은 필요 ->arraylist사용
		for (Map.Entry<String, String> i : wordsSet) {
			jb.add(new JButton(i.getKey()));
			jl.add(new JLabel(i.getValue()));
			// 굳이 변수를 사용하지 않아도 됨
			JButton thisJButton = jb.getLast();
			JLabel thisJLabel = jl.getLast();
			// 버튼을 패널에 추가하면서 그 버튼에 이벤트리스너도 추가
			grid.getContentPane().add(thisJButton).addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if ((JButton) e.getSource() == thisJButton && !thisJLabel.isVisible()) {
						thisJLabel.setVisible(true);
					} else {
						thisJLabel.setVisible(false);
					}
				}
			});
			grid.getContentPane().add(thisJLabel).setVisible(false);
		}
		scanner.close();
		grid.setVisible(true);
	}// main
}
