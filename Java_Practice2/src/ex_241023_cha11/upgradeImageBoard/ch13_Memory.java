package ex_241023_cha11.upgradeImageBoard;

import java.awt.Color;
import java.util.Stack;

public class ch13_Memory {
	static Stack<Object> memory = new Stack<Object>();
	static Stack<Color> colorMemory = new Stack<Color>();
	static Stack<Integer> thicknessMemory = new Stack<Integer>();
	
	static Stack<Object> redoMemory = new Stack<Object>();
	static Stack<Color> redoColorMemory = new Stack<Color>();
	static Stack<Integer> redoThicknessMemory = new Stack<Integer>();
}
