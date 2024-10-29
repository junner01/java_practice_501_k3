package ex_241023_ch9.homework;

import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Hcb0402_Main extends JFrame {

	// 클래스의 직렬화와 역직렬화 과정에서 클래스 변경 여부를 확인하는 용도 , 없으면 자동생성하지만 다른 클래스와 연계될때 이 클래스가
	// 변경이되면 uid도 변경되어 버린다
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 정보가 들어갈 해시맵을 하나 선언하고 새 인스턴스 할당
		HashMap<String, String> words = new HashMap<String, String>();
		// jframe 하나 선언하고 새 인스턴스 할당
		Hcb0402_Main grid = new Hcb0402_Main();
		grid.setTitle("GridLayout 예제");
		grid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		grid.setSize(300, 1000);
		Scanner scanner = new Scanner(System.in);

		// 반복문으로 만들어 입력을 받을 수 있음
		words.put("apple", "사과");
		words.put("banana", "바나나");
		words.put("house", "집");
		words.put("car", "차");
		words.put("etc", "기타");
		// 컨트롤러
		int ctr;
		while (true) {
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
		
		// 그리드 방식의 행 갯수를 넣을 단어 갯수에 맞춤
		grid.setLayout(new GridLayout(words.size(), 2, 5, 5));
		// 아래에서 계속 쓸 변수 미리 선언
		Map.Entry<String, String> wordsKey;
		JButton[] wordJbutton = new JButton[words.size()];
		JLabel[] wordJlabel = new JLabel[words.size()];
		for (int i = 0; i < words.size(); i++) {
			// wordsKey의 처음부터 끝까지 하나씩 쓸 수 있음,
			wordsKey = words.entrySet().stream().skip(i).findFirst().orElse(null);
			// 버튼의 배열 전체에 하나씩 key를 넣음
			// 주의 위에서 미리 선언한것은 Jbutton과 Jlabel의 인스턴스가 아니라 인스턴스가 들어갈 수 있는 배열을 선언한 것
			// 따라서 setText로 설정하려하면 인스턴스가 없어서 에러 발생, 새 인스턴스를 만들어서 할당
			wordJbutton[i] = new JButton(wordsKey.getKey());
			// 라벨의 배열 전체에 하나씩 value를 넣음
			wordJlabel[i] = new JLabel(wordsKey.getValue());
			// 위의 버튼,라벨 배열을 jframe에 넣음
			grid.getContentPane().add(wordJbutton[i]);
			grid.getContentPane().add(wordJlabel[i]).setVisible(false);
		} // for1번 버튼,라벨 인덱싱 ->프레임에 추가

		// 이제 버튼에 이벤트 리스터(마우스 리스너) 추가하고 각 라벨의 visible을 토글 가능하게 하기
		for (JButton i : wordJbutton) {
			// 버튼 전체에 리스너 추가
			i.addMouseListener(new MouseAdapter() {
				// 리스너의 내용은 마우스 클릭했을 때
				public void mouseClicked(MouseEvent e) {
					for (int j = 0; j < words.size(); j++) {
						// 이벤트가 일어난 오브젝트를 버튼으로 다운캐스팅 한 것이 (어떤 버튼과 같고 for로 구현) && 그 버튼과 i로 연결된 라벨이
						// visible true가 아니라면
						if (wordJbutton[j] == (JButton) e.getSource() && !wordJlabel[j].isVisible())
							// 그 라벨의 visible을 true로 set
							wordJlabel[j].setVisible(true);
						// 아니면 , 혹은 visible이 이미 true라면 set false
						else
							wordJlabel[j].setVisible(false);
					}
				}

			});
		} // for2번 리스너 추가
		scanner.close();
		grid.setVisible(true);
	}// main
}
