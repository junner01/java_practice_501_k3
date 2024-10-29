package ex_241023_cha11.upgradeImageBoard;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ch13_TextBoard {
	JPanel memoBoard = new JPanel();
	JTextArea memo = new JTextArea("텍스트입력하세요.",20,30);
	
	ch13_TextBoard() {
		
		memoBoard.setBackground(Color.white);
		memoBoard.add(memo);
		memoBoard.add(new JScrollPane(memo));
		ch13_Buttons.canvas.add("메모장",memoBoard);
	}
	
}