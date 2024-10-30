package ex_241023_cha13.homework;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
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

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class WordTestJListVersionLsy1205Test2 extends JFrame {
	// 전역으로 선언만 했음. 생성자에서 초기화 하면, 다른 곳에서 사용이 가능
	private JTextField wordField; // 영어단어 입력 필드
	private JTextField meaningField; // 영어 뜻 입력 필드

	// 단어를 임시로 저장할 Map -> HashMap
	// UI, 화면에 표시할 라벨
	private Map<String, JLabel> wordLabelMap;
	// 영어의 뜻을 저장하는 임시 공간.
	private Map<String, String> wordMeaningLabelMap;
	// 랜덤으로 단어 퀴즈 형식처럼 출력하기 위한 도구,
	private Random random;

	// JList 버전
	// 변경부분1
	// JList 에 데이터를 연결할 도구
	private DefaultListModel<String> wordListModel = new DefaultListModel<>();
	// JList UI 표시. 
	private final JList<String> wordList = new JList<>(wordListModel);
	private final JLabel meaningLabel = new JLabel("뜻을 보려면 단어를 선택하세요");

	public WordTestJListVersionLsy1205Test2() {

		setTitle("단어 프로그램 V 2.0.0");
		setSize(1100, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		wordLabelMap = new HashMap<>();
		wordMeaningLabelMap = new HashMap<String, String>();

		// random 도구 초기화.
		random = new Random();

		setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new FlowLayout());

		inputPanel.add(new JLabel("단어: "));
		wordField = new JTextField(10);
		inputPanel.add(wordField);

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

		// 단어 삭제하는 버튼
		JButton deleteButton = new JButton("단어삭제");
		inputPanel.add(deleteButton);

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

		// 최상위 프레임 창에
		// 단어를 입력하는 패널을, 북쪽에 붙이기 작업.
		add(inputPanel, BorderLayout.NORTH);

		// 2
		// 변경부분2
		// // 단어 리스트 설정
		// JList 선택 모드 설정
		wordList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// JList 에서 하나 선택시, 이벤트 리스너 설정, 클릭시 실행할 함수를 지정.
		wordList.addListSelectionListener(e -> {
			// JList 에서 선택된 영어 단어,  ex) car, house 각 선택된 단어를 가리킴.
			String selectedWord = wordList.getSelectedValue();
			// 1차 기본 유효성 체크. 
			if (selectedWord != null) {
				// 뜻을 보여주는 JLabel 인스턴스 하나만 사용해서, 메모리 낭비를 줄이기.
				// 앞에서, 매번 각 인스턴스 생성했고, 그래서, 이번에 싱글톤으로 변경. 
				// 하나의 라벨 인스턴스에 문자열만 교체하는 방법으로 개선.
				meaningLabel.setText("뜻: " + wordMeaningLabelMap.get(selectedWord));
			}
		});
		JScrollPane scrollPane = new JScrollPane(wordList);
		add(scrollPane, BorderLayout.WEST);

		// 뜻을 보여줄 JLabel 설정
		// 변경부분3
		meaningLabel.setFont(new Font("돋움", Font.BOLD, 16));
		meaningLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(meaningLabel, BorderLayout.CENTER);

		// ==================================================
		addButton.addActionListener(e -> addWord());

		// 랜덤으로 보여주는 기능 버튼에 리스너 추가하기.
		randomButton.addActionListener(e -> showRandomWord());

		// 파일로 다른이름으로 저장하는 이벤트 리스너
		addFileButton.addActionListener(e -> saveAsFile());

		// 파일 불러오기 이벤트 리스너
		loadFileButton.addActionListener(e -> selectAndLoadFile());

		// 초기화하는 이벤트 리스너
		initButton.addActionListener(e -> initWord());

		// 단어 삭제 이벤트 리스너
		deleteButton.addActionListener(e -> deleteWord());

		// 모든 단어 보기 이벤트 리스너
		showAllButton.addActionListener(e -> showAllWord());

		setVisible(true);

	}

	private void addWord() {
		String word = wordField.getText();
		System.out.println("addWord() , word 확인 : " + word);
		String meaning = meaningField.getText();

		if (!word.isEmpty() && !meaning.isEmpty()) {
			// 변경부분4
			// 	단어 추가시 wordListModel, JList와 연결된 리스트, 여기의 요소가 화면에 나타남. 
			wordMeaningLabelMap.put(word, meaning);
			wordListModel.addElement(word);
			// 입력 필드 초기화
			wordField.setText("");
			meaningField.setText("");
		} else {
			JOptionPane.showMessageDialog(null, "단어와 뜻을 모두 입력해주세요.", "입력오류: 모두 입력해주세요", JOptionPane.ERROR_MESSAGE);
		}

	} // addWord()

	// 랜덤 기능
	public void showRandomWord() {
		if (wordListModel.isEmpty()) {
			JOptionPane.showMessageDialog(null, "단어가 입력 안됨", "먼저, 단어를 추가해주세요", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 변경부분5
		List<String> words = new ArrayList<String>(wordMeaningLabelMap.keySet());
		String randomWord = words.get(random.nextInt(words.size()));
		wordListModel.clear();
		wordListModel.addElement(randomWord);
		meaningLabel.setText("무슨 뜻 일까요?");

	}

	// 초기화 하기.
	public void initWord() {
		// 변경부분6
		wordListModel.clear();
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
				String[] parts = line.split(",");
				for (int i = 0; i < parts.length - 1; i += 2) { // 짝수 인덱스는 단어, 홀수 인덱스는 뜻

					String word = parts[i];
					String meaning = parts[i + 1];
					System.out.println("word: " + word + ", meaning: " + meaning);
					wordMeaningLabelMap.put(word, meaning);
					// 변경부분7
					wordListModel.addElement(word); // 단어 리스트에 추가

				}
			}
			System.out.println("CSV 파일에서 데이터를 성공적으로 읽어왔습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 모든 단어 출력하기.
	public void showAllWord() {
		// 맵에, 단어가 추가 안된 상황,
		if (wordListModel.isEmpty()) {
			JOptionPane.showMessageDialog(null, "단어가 입력 안됨", "먼저, 단어를 추가해주세요", JOptionPane.ERROR_MESSAGE);
			return;
		}
		List<String> words = new ArrayList<String>(wordMeaningLabelMap.keySet());
		for (String string : words) {
			// 변경부분8
			wordListModel.addElement(string);
		}

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

	// 변경부분9
	// JList 에서 선택된 단어 표기
	private void showMeaningForSelectedWord() {
		String selectedWord = wordList.getSelectedValue();
		if (selectedWord != null && wordMeaningLabelMap.containsKey(selectedWord)) {
			meaningLabel.setText("뜻: " + wordMeaningLabelMap.get(selectedWord));
		}
	}

	// 변경부분10
	// JList 에서 선택된 단어 삭제
	private void deleteWord() {
		int selectedIndex = wordList.getSelectedIndex();
		if (selectedIndex != -1) {
			wordListModel.remove(selectedIndex);
		} else {
			JOptionPane.showMessageDialog(null, "삭제할 단어를 선택해주세요.", "삭제 오류", JOptionPane.WARNING_MESSAGE);
		}
	}

	public static void main(String[] args) {
		new WordTestJListVersionLsy1205Test2();
	} // main

}// class
