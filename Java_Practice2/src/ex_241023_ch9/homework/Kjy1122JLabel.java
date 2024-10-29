package ex_241023_ch9.homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Kjy1122JLabel extends JFrame {
	
	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	        	Kjy1122JLabel wordDictionary = new Kjy1122JLabel();
	            wordDictionary.setVisible(true);
	        });
	    }
	
	
	 private JLabel appleLabel, bananaLabel, houseLabel, carLabel;
	    private String[] words = {"Apple: 사과", "Banana: 바나나", "House: 집", "Car: 차"};
	    private Color[] colors = {Color.RED, Color.YELLOW, Color.GRAY, Color.BLACK};
	    private Color[] originalColors = {null, null, null, null};  // 초기 배경색 저장

	    public Kjy1122JLabel() {
	        setTitle("Word Dictionary");
	        setSize(400, 200);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setLayout(new GridLayout(2, 2));

	        // 라벨 생성
	        appleLabel = createLabel("Apple", 0);
	        bananaLabel = createLabel("Banana", 1);
	        houseLabel = createLabel("House", 2);
	        carLabel = createLabel("Car", 3);

	        // 라벨 추가
	        add(appleLabel);
	        add(bananaLabel);
	        add(houseLabel);
	        add(carLabel);
	    }

	    private JLabel createLabel(String text, int index) {
	        JLabel label = new JLabel(text, SwingConstants.CENTER);
	        label.setOpaque(true); // 배경색을 변경하기 위해 반드시 필요
	        originalColors[index] = label.getBackground(); // 라벨의 원래 배경색 저장
	        label.addMouseListener(new MouseAdapter() {
	            private boolean clicked = false;

	            @Override
	            public void mouseClicked(MouseEvent e) {
	                if (!clicked) {
	                    label.setText(words[index]);  // 뜻을 보여줌
	                    label.setBackground(colors[index]);  // 배경색 변경
	                    label.setForeground(Color.WHITE);  // 텍스트 색상 변경
	                } else {
	                    label.setText(text);  // 원래 단어로 복구
	                    label.setBackground(originalColors[index]);  // 원래 배경색 복구
	                    label.setForeground(Color.BLACK);  // 원래 텍스트 색상 복구
	                }
	                clicked = !clicked;
	            }
	        });
	        return label;
	    }

	   
}
