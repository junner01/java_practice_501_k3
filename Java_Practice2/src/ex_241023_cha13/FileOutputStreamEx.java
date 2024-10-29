package ex_241023_cha13;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamEx {
	public static void main(String[] args) {
		// 입력 대상 : 바이트 타입의 임시 배열 
		byte b[] = { 7, 51, 3, 4, -1, 24 };
		
		try {
			// 파일 기반 출력 , 
			// 출력 대상 파일
			FileOutputStream fout = 
					new FileOutputStream("c:\\Temp\\test.out");
			// 반복문 : 임시 배열의 내용을 읽어서 -> 출력 대상 파일 쓰기 작업.
			for (int i = 0; i < b.length; i++)
				fout.write(b[i]); // 배열 b의 바이너리를 그대로 기록
			fout.close();
		} catch (IOException e) {
		}
	}
}





