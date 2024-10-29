package ex_241023_ch9.homework; // 패키지 이름 정의된것 제일 처음 해야하는 기본이지

import javax.swing.*; //스윙 컴포넌트
import java.awt.*; //awt 패키지 (?)
import java.awt.event.*; // 이벤트 처리를 위한 awt(?)패키지 사용

public class Kdh1018_241025 extends JFrame { //extends 자체가 오른쪽에서 왼쪽으로 상속받는거.
	// 즉 왼쪽이 부모클래스, 오른쪽이 자식클래스. 그래서 상속이 a<- b 방향. 
    private JLabel appleLabel;
    private JLabel bananaLabel;
    private JLabel houseLabel;
    private JLabel carLabel;

    public Kdh1018_241025() { //생성자 정의
        setTitle("Vocabulary Flashcard"); //프레임 제목 설정
        setSize(400, 200); // 제목 설정 했으니까 사이즈도 설정 해줘야함.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit_on _close 말 그대로 창 닫는거
        setLayout(new GridLayout(2, 2)); // 이건 잘 모르겠음...... 봐도 이해안감. grid라는데..?css 아잉교..

        
        //이제 생성자 만들어주고 프레임 제목 사이즈 나갈때 어떻게 하는지까지 기본적인 설정 해줬으니
        //출력하고 싶은 단어에 대한 라벨을 생성할 차례
        appleLabel = createLabel("APPLE");
        bananaLabel = createLabel("BANANA");
        houseLabel = createLabel("HOUSE");
        carLabel = createLabel("CAR");

        //라벨 생성 후에 그 라벨을 프레임에 추가하는거임 -> 이유는 잘 모르겠음.
        add(appleLabel);
        add(bananaLabel);
        add(houseLabel);
        add(carLabel);
    }

    //라벨 생성하고 마우스 리스너 추가 함수
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text); //입력된 텍스트로 라벨생성
        label.setFont(new Font("Arial", Font.PLAIN, 24)); //폰트
        label.setOpaque(true);// 배경색 적용을 위해서 불투명하게 설정(그럼 반대면 투명인듯)
        label.setBackground(Color.white); //기본 백그라운드 색 설정
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        
        //마우스 리스너 추가
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) { //마우스 클릭시(pressed) 클릭된 라벨의 텍스트에 따라 뜻
                switch (text) {
                    case "apple":
                        label.setText("apple"); //한글 출력 안돼서 그대로 적어둠
                        break;
                    case "banana":
                        label.setText("banana"); //한글 출력 안돼서 그대로 적어둠
                        break;
                    case "house":
                        label.setText("house"); //한글 출력 안돼서 그대로 적어둠
                        break;
                    case "car":
                        label.setText("car"); //한글 출력 안돼서 그대로 적어둠
                        break;
                }
                label.setBackground(Color.PINK); //이제 클릭했을때 마우스 리스너니까 색을 핑크로 바꿔준다
            }

            @Override
            public void mouseReleased(MouseEvent e) {//마우스를 뗐을 때(released) 
                label.setText(text); //텍스트 복구
                label.setBackground(Color.GREEN); //배경색을 그린으로
            }
        });

        return label; //라벨 돌려주고
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Kdh1018_241025 frame = new Kdh1018_241025();
            frame.setVisible(true); //화면에 프레임표시
        });
    }
}
