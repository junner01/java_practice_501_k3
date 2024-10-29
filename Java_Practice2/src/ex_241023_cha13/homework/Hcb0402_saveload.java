package ex_241023_cha13.homework;

import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

public class Hcb0402_saveload {

	// method에 쓰이는 맵,셋 등 선언
	private static HashMap<String, String> words = new HashMap<String, String>();
	private static Set<Map.Entry<String, String>> wordsSet = words.entrySet();
	private static JTextField wordIn = new JTextField(10);
	private static JTextField meanIn = new JTextField(10);
	private static JPanel gridP = new JPanel();

	// method add
	public static void addWord() {
		if (!wordIn.getText().isEmpty() && !meanIn.getText().isEmpty()) {
			words.put(wordIn.getText(), meanIn.getText());
			outWordpad();
			wordIn.setText("");
			meanIn.setText("");
		}
	}

	// method out
	public static void outWordpad() {
		gridP.removeAll();
		gridP.repaint();
		for (Map.Entry<String, String> i : wordsSet) {
			JButton keyButton = new JButton(i.getKey());
			JLabel valueLabel = new JLabel(i.getValue());
			// 버튼을 패널에 추가하면서 그 버튼에 이벤트리스너도 추가
			gridP.add(keyButton).addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if ((JButton) e.getSource() == keyButton && !valueLabel.isVisible()) {
						valueLabel.setVisible(true);
					} else {
						valueLabel.setVisible(false);
					}
				}
			});
			gridP.add(valueLabel).setVisible(false);
		}
		gridP.revalidate();
	}

	// method save
	public static void saveWord() {
		//저장할 파일의 이름
		File saveFile = new File("c:\\Temp\\Hcb0402_wordTest.txt");
		try {
			//fout 생성
			FileOutputStream fout = new FileOutputStream(saveFile);
			//map의 key와 value를 각각 string배열로 할당
			String[] keyStrings = words.keySet().toArray(new String[0]);
			String[] valueStrings = words.values().toArray(new String[0]);
			//할당된 string 배열을 ,로 붙여 String에 할당 후 그 String을 Byte화 하여 save파일에 저장
			for (int i = 0; i < keyStrings.length; i++) {
				try {
					//마지막에 , 이 들어가면 나중에 불러올 때 마지막에 공백이 생기므로
					if(i+1 == keyStrings.length) {
						String writeString = keyStrings + "," + valueStrings;
						fout.write(writeString.getBytes());
					} else {
					String writeString = keyStrings + "," + valueStrings + ",";
					fout.write(writeString.getBytes());
					}
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "IO오류.", "IO오류: 파일을 쓰는데 오류가 발생했습니다", JOptionPane.ERROR_MESSAGE);
				}
			}
			fout.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "파일을 만들어주세요.", "파일오류: 파일이 없습니다", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "IO오류.", "IO오류: fout을 반납하는데 오류가 발생했습니다.", JOptionPane.ERROR_MESSAGE);
		}

	}

	// mothod load
	public static void loadWord() {
		// 불러올 파일
		File loadFile = new File("c:\\Temp\\Hcb0402_baseWord.txt");
		try {
			//fin 생성
			FileInputStream fin = new FileInputStream(loadFile);
			//읽은 int를 저장할 버퍼
			ByteArrayOutputStream buf = new ByteArrayOutputStream();
			int c;
			//1바이트씩 읽어서 버퍼에 저장, byte[]를 선언해서 몇바이트씩 읽을것인지 정할 수 있음
			while ((c = fin.read()) != -1) {
				buf.write(c);

			}
			//버퍼에 저장된 불러온 파일의 내용을 String으로 변환하고 ,로 구분되어있으니 split으로 ,를 기준으로 나눔
			String loadString = buf.toString();
			String[] loadStrings = loadString.split(",");
			//나눠진 String배열은 key value순서로 되어있음
			for (int i = 0; i < (loadStrings.length / 2); i++) {
				String loadKey = loadStrings[2*i];
				String loadvalString = loadStrings[2*i+1];
				words.put(loadKey, loadvalString);
			}
			fin.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "오류 발생.", "오류: FNF나 IO 오류 발생.", JOptionPane.ERROR_MESSAGE);
		}
		//재출력
		outWordpad();
	}

	// method frame
	private void grid() {

		JFrame grid = new JFrame();

		// frame property
		grid.setTitle("GridLayout 예제");
		grid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		grid.setSize(800, 1000);

		// 추가 panel 생성, 저장된 정보 넣을 panel도 add
		JPanel borderNorth = new JPanel();
		grid.setLayout(new BorderLayout());
		grid.add(borderNorth, BorderLayout.NORTH);
		grid.add(gridP, BorderLayout.CENTER);
		// 출력 패널 레이아웃 세팅
		gridP.setLayout(new GridLayout(words.size(), 2, 5, 5));

		// 추가 panel에 필요한 것들과 이벤트 리스너
		JButton addButton = new JButton("추가하기");
		JButton saveButton = new JButton("save");
		JButton loadButton = new JButton("load");
		addButton.addActionListener(e -> addWord());
		saveButton.addActionListener(e -> saveWord());
		loadButton.addActionListener(e -> loadWord());

		// 추가 panel 에 add
		borderNorth.add(new JLabel("새 영단어: "));
		borderNorth.add(wordIn);
		borderNorth.add(new JLabel("뜻: "));
		borderNorth.add(meanIn);
		borderNorth.add(addButton);
		borderNorth.add(saveButton);
		borderNorth.add(loadButton);

		// 끝
		grid.setVisible(true);
	}

	public static void main(String[] args) {
		Hcb0402_saveload main = new Hcb0402_saveload();
		main.grid();
		outWordpad();
	}// main
}
