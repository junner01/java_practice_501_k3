package ex_241023_cha13.homework;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

// 구조, 각 라벨 인스턴스를 각각 생성하고, 각 라벨을 패널에 붙이는 작업. 
public class WordTestLsy1205Test extends JFrame {
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
	public WordTestLsy1205Test() {
		// 윈도우 창과 -> 최상위 프레임 필요함. 부모 클래스로 부터 상속 받아서,
		// 그리기 도구를 장착.
		// 창 제목
		setTitle("단어 프로그램 V 1.0.0");
		// 창 사이즈. 기본 크기.
		setSize(1300, 300);
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

		// 파일로 저장하는 버튼
		JButton addFileButton = new JButton("파일로 저장");
		inputPanel.add(addFileButton);

		// 파일로 저장하는 버튼
		JButton addFileButton2 = new JButton("다른 이름으로 저장");
		inputPanel.add(addFileButton2);

		// 파일로 불러오기 버튼
		JButton loadFileButton = new JButton("파일 불러오기");
		inputPanel.add(loadFileButton);

		// 초기화 버튼
		JButton initButton = new JButton("초기화하기");
		inputPanel.add(initButton);

		// 전체 단어 출력 버튼
		JButton showAllButton = new JButton("전체 단어 보기");
		inputPanel.add(showAllButton);

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

		// 파일로 저장하는 이벤트 리스너
		addFileButton.addActionListener(e -> addFile());

		// 파일로 다른이름으로 저장하는 이벤트 리스너
		addFileButton2.addActionListener(e -> saveAsFile());

		// 파일 불러오기 이벤트 리스너
		loadFileButton.addActionListener(e -> selectAndLoadFile());

		// 초기화하는 이벤트 리스너
		initButton.addActionListener(e -> initWord());

		// 모든 단어 보기 이벤트 리스너
		showAllButton.addActionListener(e -> showAllWord());

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
	// 컬렉션, 맵에 담기, 임시 메모리,
	// 맵 랜덤으로 선택이 어려움, -> 순서를 정해야함, -> ArrayList 형식으로 변환해서,
	// 랜덤 클래스 이용.
	public void showRandomWord() {
		// 맵에, 단어가 추가 안된 상황,
		if (wordLabelMap.isEmpty()) {
			JOptionPane.showMessageDialog(null, "단어가 입력 안됨", "먼저, 단어를 추가해주세요", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// 랜덤하게 하나만 출력, 나머지 단어는 모두 가리기.
		// 1
		// wordLabelMap, key : String, value: 각 단어가 표시된 라벨
//		for (JLabel jLabel : wordLabelMap.values()) {
//			jLabel.setVisible(false);
//		}
		// 2
//	    SwingUtilities.invokeLater(() -> {
//	        for (JLabel jLabel : wordLabelMap.values()) {
//	            jLabel.setVisible(false); // 모든 JLabel 숨기기
//	        }
//	    });

		// 3
		// 모든 JLabel을 숨기기
		wordLabelMap.values().forEach(jLabel -> jLabel.setVisible(false));

		// UI 전체 갱신 (모든 JLabel이 숨겨지도록 보장)
		wordLabelMap.values().forEach(jLabel -> {
			jLabel.revalidate();
			jLabel.repaint();
		});

		// 랜덤하게 단어 하나 선택.
		// 맵 -> 리스트 형식으로 변환.
		// wordLabelMap.keySet(), 영어 단어 , car, -> car 를 출력하는 라벨을 생성.
		List<String> words = new ArrayList<String>(wordLabelMap.keySet());
		// words 에는 맵에 등록된 영어 단어의 영어만 전부다 리스트로 변환.
		// words = {"car","house","red"};
		// words.get(0) -> "car"
		// words.get(1) -> "house"
		// words.get(2) -> "red"
		String randomWord = words.get(random.nextInt(words.size()));

		// 선택된 단어만 보이게 하는 설정.
//		wordLabelMap.get(randomWord).setVisible(true);
		// wordLabelMap -> {"car" : JLabel인스턴스}
		// 선택된 단어의 JLabel만 보이도록 설정
		SwingUtilities.invokeLater(() -> {
			wordLabelMap.get(randomWord).setVisible(true);
		});
	}

	// wordMeaningLabelMap을 파일에 저장하는 함수
	public void addFile() {
		Path filePath = Paths.get("C:\\Temp\\wordTest_241030.txt");
		try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
			for (Map.Entry<String, String> entry : wordMeaningLabelMap.entrySet()) {
				writer.write(entry.getKey() + "," + entry.getValue());
				writer.newLine();
			}
			System.out.println("wordMeaningLabelMap이 파일에 저장되었습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 초기화 하기.
	public void initWord() {
		for (JLabel jLabel : wordLabelMap.values()) {
			jLabel.setVisible(false);
		}
		// 레이아웃 업데이트
		wordPanel.revalidate();
		// 패널 그림을 다시 그리기.
		wordPanel.repaint();

		wordLabelMap.clear();
		wordMeaningLabelMap.clear();

		JOptionPane.showMessageDialog(null, "초기화완료!", "초기화작업", JOptionPane.INFORMATION_MESSAGE);

	}

	// CSV 파일을 읽어 wordMeaningLabelMap에 데이터 추가 및 출력하는 함수
	public void loadFile(String csvFilePath) {
		Path filePath = Paths.get(csvFilePath);
		try (BufferedReader reader = Files.newBufferedReader(filePath)) {
			String line;
			while ((line = reader.readLine()) != null) {
				// 1 car,자동차,house,집
				String[] parts = line.split(",");
				// 2 parts = car,자동차,house,집
				for (int i = 0; i < parts.length - 1; i += 2) { // 짝수 인덱스는 단어, 홀수 인덱스는 뜻

					String word = parts[i];
					String meaning = parts[i + 1];
					System.out.println("word: " + word + ", meaning: " + meaning);
					// 각각 인스턴스를 매번 생성함. 메모리 부족 현상의 원인이 됨.
					JLabel wordLabel = new JLabel(word);
					wordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

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

					wordMeaningLabelMap.put(word, meaning);
					wordLabelMap.put(word, wordLabel);
					wordPanel.add(wordLabel);

				}
			} // while
				// 레이아웃 업데이트
			wordPanel.revalidate();
			// 패널 그림을 다시 그리기.
			wordPanel.repaint();
			System.out.println("CSV 파일에서 데이터를 성공적으로 읽어왔습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // loadFile

	// 모든 단어 출력하기.
	public void showAllWord() {
		for (JLabel jLabel : wordLabelMap.values()) {
			jLabel.setVisible(true);
		}
		// 레이아웃 업데이트
		wordPanel.revalidate();
		// 패널 그림을 다시 그리기.
		wordPanel.repaint();

	}

	// 탐색기를 통해 파일을 선택하고, 선택된 파일을 로드하는 메서드
	public void selectAndLoadFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("CSV 파일 선택");

		int result = fileChooser.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			System.out.println("선택된 파일: " + selectedFile.getAbsolutePath());
			loadFile(selectedFile.getAbsolutePath()); // 파일 로드
		} else {
			System.out.println("파일 선택이 취소되었습니다.");
		}
	}

	// JFileChooser를 사용하여 파일을 다른 이름으로 저장하는 메서드
	public void saveAsFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("다른 이름으로 저장");

		int userSelection = fileChooser.showSaveDialog(null);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			System.out.println("저장할 파일: " + fileToSave.getAbsolutePath());
			saveToFile(fileToSave); // 파일에 데이터 저장
		} else {
			System.out.println("파일 저장이 취소되었습니다.");
		}
	}

	// 파일에 wordMeaningLabelMap의 데이터를 저장하는 메서드
	// 다른이름으로 파일 생성해주는 기능
	private void saveToFile(File file) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			for (Map.Entry<String, String> entry : wordMeaningLabelMap.entrySet()) {
				writer.write(entry.getKey() + "," + entry.getValue());
				writer.newLine();
			}
			System.out.println("wordMeaningLabelMap이 파일에 저장되었습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new WordTestLsy1205Test();
	} // main

}// class
