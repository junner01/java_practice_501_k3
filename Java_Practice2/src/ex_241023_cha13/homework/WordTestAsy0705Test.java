package ex_241023_cha13.homework;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WordTestAsy0705Test extends JFrame {
	// 전역으로 선언만 했음. 생성자에서 초기화 하면, 다른 곳에서 사용이 가능
	private JTextField wordField; // 영어단어 입력 필드
	private JTextField meaningField; // 영어 뜻 입력 필드
	private JPanel wordPanel; // 단어 표시할 필드

	// 단어를 임시로 저장할 Map -> HashMap
	// UI, 화면에 표시할 라벨
	private Map<String, JLabel> wordLabelMap;
	// 영어의 뜻을 저장하는 임시 공간.
	private Map<String, String> wordMeaningLabelMap;
	// 랜덤으로 단어 퀴즈 형식처럼 출력하기 위한 도구,
	private Random random;

	// 생성자 이용해서, UI를 그리고, 임시 메모리 Map 공간에 단어를 입력 받고, 출력하고, 랜덤출력.
	//
	public WordTestAsy0705Test() {
		// 윈도우 창과 -> 최상위 프레임 필요함. 부모 클래스로 부터 상속 받아서,
		// 그리기 도구를 장착.
		// 창 제목
		setTitle("단어 프로그램 V 1.0.0");
		// 창 사이즈. 기본 크기.
		setSize(800, 300);
		// 창의 닫기를 클릭시, 정상 종료.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 임시 저장소 Map, 전역으로 선언만 한 인스턴스를, 생성자에서 초기화 작업을 함.
		// 다른 곳에서 사용 가능.
		wordLabelMap = new HashMap<>();
		wordMeaningLabelMap = new HashMap<String, String>();

		// random 도구 초기화.
		random = new Random();

		// 배치 관리자 이용해서, 입력 공간은 북, 단어 표시 공간, 중간에 표기.
		setLayout(new BorderLayout());

		// UI 생성하기.
		// 상단 패널 - 단어를 입력하는 공간, 뜻을 입력하는 공간.
		// 인스턴스 당 하나의 입력 공간.
		// 최상위 프레임 창위에, 패널을 붙이는 역할 -> 패널 위에 JLabel 붙이 예정.
		JPanel inputPanel = new JPanel();
		// inputPanel -> 배치 관리자, flex , FlowLayout 나란히 배치.
		inputPanel.setLayout(new FlowLayout());

		// 패널에 붙이는 입력 공간
		// 영어 단어를 입력하는 1) 라벨 2) 입력란
		// 1. 라벨 붙이기, 화면, 이벤트 핸들러, 보통 1회용으로 많이 사용해서,
		inputPanel.add(new JLabel("단어: "));
		// 2. 입력 공간 붙이기.
		wordField = new JTextField(10);
		inputPanel.add(wordField);

		// 영어 뜻 입력하는 1) 라벨 2) 입력란
		inputPanel.add(new JLabel("뜻: "));
		meaningField = new JTextField(10);
		inputPanel.add(meaningField);

		// 단어를 추가하는 버튼
		JButton addButton = new JButton("단어추가");
		inputPanel.add(addButton);

		// 랜덤으로 출력해주는 버튼
		JButton randomButton = new JButton("랜덤 단어 뽑기");
		inputPanel.add(randomButton);

		// 단어 Save 버튼
		JButton saveButton = new JButton("Save");
		inputPanel.add(saveButton);

		// 단어 Load 버튼
		JButton loadButton = new JButton("Load");
		inputPanel.add(loadButton);

		// ==================================================

		// 단어 출력 해주는 UI
		wordPanel = new JPanel();
		// wordPanel 에서 배치 관리자, flex 처럼 나란히 배치 FlowLayout 이용.
		wordPanel.setLayout(new FlowLayout());

		// 최상위 프레임 창에
		// 단어를 입력하는 패널을, 북쪽에 붙이기 작업.
		add(inputPanel, BorderLayout.NORTH);
		// 단어를 출력하는 패널을, 가운데 붙이기 작업.
		add(wordPanel, BorderLayout.CENTER);

		// ==================================================
		// 각 버튼에 이벤트 핸들러(리스너) 붙이기 작업.
		// addActionListener , 함수형 인터페이스 : 추상 메서드를 하나만 가짐.
		// 그래서, 람다식(화살표 함수) 표현 가능.
		// 이벤트 파라미터를 받아서, addWord() 메서드 이용해서, 추가하는 로직.
		// 맵에 추가할 예정.
		addButton.addActionListener(e -> addWord());

		// 랜덤으로 보여주는 기능 버튼에 리스너 추가하기.
		randomButton.addActionListener(e -> showRandomWord());

		// 세이브 기능 버튼에 리스너 추가하기.
		saveButton.addActionListener(e -> saveWord());

		// 로드 기능 버튼에 리스너 추가하기.
		loadButton.addActionListener(e -> loadWord());

		setVisible(true);

	}

	// Map 에 단어를 추가하는 로직.
	// 입력란에 입력된 내용을 추가하는 로직.
	private void addWord() {
		// 입력된 단어 가져오기.
		// 전역으로 설정된, 단어 입력란 참조형 변수를 이용해서 가지고 올 예정.
		// 1
		String word = wordField.getText();
		System.out.println("addWord() , word 확인 : " + word);

		// 입력된 뜻 가져오기.
		// 2
		String meaning = meaningField.getText();

		// 기본 유효성 체크, 단어와 뜻에 입력이 된 경우만 로직을 진행.
		// 3
		if (!word.isEmpty() && !meaning.isEmpty()) {
			// 임시로 출력할 JLabel를 만들어서, 여기에 단어를 붙이는 과정.
			// 임시 라벨 생성.
			// 1
			JLabel wordLabel = new JLabel(word);
			// 마우스 커서를 손 모양으로 변경하는 기능. 옵션,
			//
			wordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

			// 이벤트 리스너 추가하기. , 마우스 클릭시 뜻을 보여주는 기능.
			// addMouseListener , 함수형 인터페이스가 아님. 간단히 추상 메서드가 2개이상.
			// 인터페이스 그냥 구현하면, 사용 안하는 추상메서드 모두를 일단 재정의
			// 조금 더 간편히 하기 위해서, 어댑터 클래스 이용하면, 사용할 추상 메서드만,
			// 이용 가능함.
			// 익명 클래스로 구현.
			// new 부모 클래스의(인터페이스, 클래스) {}
			// 3
			wordLabel.addMouseListener(new MouseAdapter() {
				// 상태 변수, 설정, 뜻을 보여주는 상태.
				private boolean showMeaning = false;

				@Override
				public void mouseClicked(MouseEvent e) {
					if (!showMeaning) {
						wordLabel.setText("뜻 : " + meaning);
						showMeaning = true;
					} else {
						wordLabel.setText(word);
						showMeaning = false;
					}
				}
			});
			// Map 메모리에 저장하는 로직.
			// 4
			wordLabelMap.put(word, wordLabel);
			wordMeaningLabelMap.put(word, meaning);

			// 패널에 라벨 추가.
			// wordPanel : 단어를 출력하는 패널 공간, -> JLabel 붙이고 있음.
			wordPanel.add(wordLabel);
			// 레이아웃 업데이트
			wordPanel.revalidate();
			// 패널 그림을 다시 그리기.
			wordPanel.repaint();

		} // if
			// 유효성 통과 안될 때, 경고창 보여주기.
			// 영어 , 단어 뜻을 입력을 안될 경우
			// 4
		else {
			// 자바 버전에서, 간단한 다이얼 로그 창, 경고창 alert 같은 ui 사용
			JOptionPane.showMessageDialog(null, "단어와 뜻을 모두 입력해주세요.", "입력오류: 모두 입력해주세요", JOptionPane.ERROR_MESSAGE);
		}

	} // addWord()

	// 랜덤 기능
	public void showRandomWord() {
		// 맵에, 단어가 추가 안된 상황,
		if (wordLabelMap.isEmpty()) {
			JOptionPane.showMessageDialog(null, "단어가 입력 안됨", "먼저, 단어를 추가해주세요", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// 랜덤하게 하나만 출력, 나머지 단어는 모두 가리기.
		// wordLabelMap, key : String, value: 각 단어가 표시된 라벨
		for (JLabel jLabel : wordLabelMap.values()) {
			jLabel.setVisible(false);
		}

		// 랜덤하게 단어 하나 선택.
		// 맵 -> 리스트 형식으로 변환.
		// wordLabelMap.keySet(), 영어 단어 , car, -> car 를 출력하는 라벨을 생성.
		List<String> words = new ArrayList<String>(wordLabelMap.keySet());
		// words 에는 맵에 등록된 영어 단어의 영어만 전부다 리스트로 변환.
		String randomWord = words.get(random.nextInt(words.size()));

		// 선택된 단어만 보이게 하는 설정.
		wordLabelMap.get(randomWord).setVisible(true);
	}

	public void saveWord() {
		try (FileWriter writer = new FileWriter("C:\\Temp\\wordTest.txt")) {
			for (Map.Entry<String, String> entry : wordMeaningLabelMap.entrySet()) {
				writer.write(entry.getKey() + "," + entry.getValue() + "\n");
			}
			JOptionPane.showMessageDialog(this, "단어가 성공적으로 저장되었습니다.", "저장 완료", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "파일 저장 중 오류가 발생했습니다.", "저장 오류", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void loadWord() {
		try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Temp\\wordTest.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] tokens = line.split(",");
				for (int i = 0; i < tokens.length; i += 2) {
					String word = tokens[i];
					String meaning = tokens[i + 1];
					addWordToUI(word, meaning);
				}
			}
			JOptionPane.showMessageDialog(this, "단어가 성공적으로 로드되었습니다.", "로드 완료", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "파일 로드 중 오류가 발생했습니다.", "로드 오류", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void addWordToUI(String word, String meaning) {
	    JLabel wordLabel = new JLabel(word);
	    wordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

	    wordLabel.addMouseListener(new MouseAdapter() {
	        private boolean showMeaning = false;

	        @Override
	        public void mouseClicked(MouseEvent e) {
	            if (!showMeaning) {
	                wordLabel.setText("뜻 : " + meaning);
	                showMeaning = true;
	            } else {
	                wordLabel.setText(word);
	                showMeaning = false;
	            }
	        }
	    });
	    wordLabelMap.put(word, wordLabel);
	    wordMeaningLabelMap.put(word, meaning);
	    wordPanel.add(wordLabel);
	    wordPanel.revalidate();
	    wordPanel.repaint();
	}

	public static void main(String[] args) {
		new WordTestAsy0705Test();
	} // main

}// class