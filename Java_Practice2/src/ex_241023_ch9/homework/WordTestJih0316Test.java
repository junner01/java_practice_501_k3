package ex_241023_ch9.homework;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class WordTestJih0316Test extends JFrame {

	// GUI 컴포넌트와 데이터를 담을 Map 등을 멤버 변수로 선언
	private JTextField wordField; // 단어 입력 필드
	private JTextField meaningField; // 뜻 입력 필드
	private JPanel wordPanel; // 단어 표시용 패널

	private Map<String, JLabel> wordLabelMap; // 단어별 라벨 Map
	private Map<String, String> wordMeaningLabelMap; // 단어와 뜻을 매핑하는 Map

	private Random random; // 랜덤 단어 출력을 위한 Random 객체

	// 생성자, JFrame의 설정 및 UI 구성
	public WordTestJih0316Test() {
		setTitle("단어 프로그램 v 1.0.0"); // 창 제목 설정
		setSize(600, 300); // 창 크기 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 버튼 설정

		// Map 및 Random 객체 초기화
		wordLabelMap = new HashMap<>();
		wordMeaningLabelMap = new HashMap<String, String>();
		random = new Random();

		// BorderLayout 설정으로 UI 컴포넌트 배치
		setLayout(new BorderLayout());

		// 입력 패널 생성
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new FlowLayout()); // FlowLayout으로 좌에서 우로 배치

		// 단어와 뜻 입력 필드 및 라벨 추가
		inputPanel.add(new JLabel("단어 : "));
		wordField = new JTextField(10); // 단어 입력 필드
		inputPanel.add(wordField);

		inputPanel.add(new JLabel("뜻 : "));
		meaningField = new JTextField(10); // 뜻 입력 필드
		inputPanel.add(meaningField);

		// 단어 추가 버튼 생성 및 추가
		JButton addButton = new JButton("단어추가");
		inputPanel.add(addButton);

		// 랜덤 단어 표시 버튼 생성 및 추가
		JButton randomButton = new JButton("랜덤단어표시");
		inputPanel.add(randomButton);

		// ===================================================
		// 단어 표시용 패널 생성
		wordPanel = new JPanel();
		wordPanel.setLayout(new FlowLayout());

		// 입력 패널을 프레임 상단에 추가
		add(inputPanel, BorderLayout.NORTH);
		// 단어 패널을 프레임 중앙에 추가
		add(wordPanel, BorderLayout.CENTER);

		// ===================================================
		// 이벤트 핸들러 추가
		addButton.addActionListener(e -> addWord()); // "단어 추가" 버튼 이벤트
		randomButton.addActionListener(e -> displayRandomWord()); // "랜덤단어표시" 버튼 이벤트

		setVisible(true); // 창 보이게 설정
	}

	// 단어와 뜻을 입력하여 단어 목록에 추가하는 메서드
	private void addWord() {
		String word = wordField.getText(); // 단어 입력 필드의 값
		System.out.println("addword 확인 :" + word);

		String meaning = meaningField.getText(); // 뜻 입력 필드의 값
		System.out.println("addmeaning 확인 :" + meaning);

		// 단어와 뜻이 입력되었을 때만 로직 실행
		if (!word.isEmpty() && !meaning.isEmpty()) {
			// 단어 라벨 생성 및 설정
			JLabel wordLabel = new JLabel(word);
			wordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 손모양 커서 설정

			// 단어 클릭 시 뜻을 보여주는 이벤트 핸들러 추가
			wordLabel.addMouseListener(new MouseAdapter() {
				private boolean showMeaning = false; // 뜻을 표시할 상태 플래그

				@Override
				public void mouseClicked(MouseEvent e) {
					if (!showMeaning) { // 뜻이 안보이는 경우 뜻을 보여주고
						wordLabel.setText("뜻 : " + meaning);
						showMeaning = true;
					} else { // 뜻이 보이는 경우 다시 단어로 표시
						wordLabel.setText(word);
						showMeaning = false;
					}
				}
			});

			// Map 에 단어 및 뜻 정보 추가
			wordLabelMap.put(word, wordLabel);
			wordMeaningLabelMap.put(word, meaning);

			// 단어 라벨을 패널에 추가하고 새로 고침
			// wordPanel 단어를 출력하는 패널 공간
			wordPanel.add(wordLabel);
			// 레이아웃 업데이트
			wordPanel.revalidate();
			// 패널 그림을 다시 그리기
			wordPanel.repaint();
		} else {
			// 단어와 뜻이 입력되지 않은 경우 경고 메시지 출력, alert 처럼
			JOptionPane.showMessageDialog(null, "단어와 뜻을 입력해주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
		}
	}

	// 추가로 랜덤 단어를 출력할 기능 구현 시 사용할 메서드 (예: 랜덤 단어 표시)
	private void displayRandomWord() {
		if (wordLabelMap.isEmpty()) {
			JOptionPane.showMessageDialog(null, "단어가 입력 안됨.", "입력 오류", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 랜덤하게 하나만 출력, 나머지는 모두 가리기.
		for (JLabel jLabel : wordLabelMap.values()) {
			jLabel.setVisible(false);
		}
		// 랜덤하게 하나 선택.
		// 맵 -> 리스트로 변환
		List<String> words = new ArrayList<String>(wordLabelMap.keySet());

		String randomWords = words.get(random.nextInt(words.size()));

		// 선택된 단어만 보이기
		wordLabelMap.get(randomWords).setVisible(true);

	}

	// 메인 메서드 - 프로그램 실행
	public static void main(String[] args) {
		new WordTestJih0316Test(); // WordTest 인스턴스 생성하여 GUI 실행
	}
}
