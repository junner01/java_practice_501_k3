package ex_241023_cha10;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ComboActionEx extends JFrame {
	
	
	private String[] fruits = { "바다1", "바다2", "바다3" };
	private ImageIcon[] images = { new ImageIcon("images/test11.png"), new ImageIcon("images/test22.png"),
			new ImageIcon("images/test44.png")};
	// 이미지 라벨, 첫 화면. 
	private JLabel imgLabel = new JLabel(images[0]);

	public ComboActionEx() {
		setTitle("콤보박스 활용 예제");
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		// 컴포넌트(그림요소) 추가 부분.
		JComboBox<String> combo = new JComboBox<String>(fruits);
		c.add(combo);
		c.add(imgLabel);
// 콤보박스에 Action 리스너 등록. 선택된 아이템의 이미지 출력
		combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				int index = cb.getSelectedIndex();
				imgLabel.setIcon(images[index]);
			}
		});
		setSize(300, 250);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ComboActionEx();
	}
}
