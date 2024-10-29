package ex_241023_cha13;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextCopy {
	public static void main(String[] args) {
		// 파일 읽고 -> 출력 대상 : 파일에 쓰기 
		// 원본 : c:\\windows\\system.ini , 파일 읽기 
		File src = new File("c:\\windows\\system.ini"); // 원본 파일 경로명
		File dest = new File("c:\\Temp\\system.txt"); // 복사 파일 경로명
		int c;
		try {
			// 문자열 기반으로 src 대상 파일을 읽어서 FileReader에 담기. 
			FileReader fr = new FileReader(src); // 파일 입력 문자 스트림 생성
			// 문자열 기반으로 dest 대상 파일에 출력 FileWriter에 담기 
			FileWriter fw = new FileWriter(dest); // 파일 출력 문자 스트림 생성
			
			// fr : 임시 메모리 공간에, 대상 파일로부터 읽은 모든 문자열을 가지고 있다. 
			// c = fr.read(), 한 문자씩 읽기 
			while ((c = fr.read()) != -1) { // 문자 하나 읽고
				// 한문자씩 쓰기 작업을 함. 
				fw.write((char) c); // 문자 하나 쓰고
			}
			// 자원 반납
			fr.close();
			fw.close();
			System.out.println(src.getPath() + "를 " + dest.getPath() + "로 복사하였습니다.");
		} catch (IOException e) {
			System.out.println("파일 복사 오류");
		} 
	}
}
