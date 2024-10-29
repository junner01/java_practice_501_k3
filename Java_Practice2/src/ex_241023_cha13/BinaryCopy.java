package ex_241023_cha13;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BinaryCopy {
	public static void main(String[] args) {
		// 입력 대상
		File src = new File("c:\\Temp\\test1.jpg");
		// 출력 대상 
		File dest = new File("c:\\Temp\\copyTest1.jpg");
		int c;
		try {
			// 입력 대상을 읽어서 -> FileInputStream 에 담기 (메모리) 
			// -> 현재 파일 , 응용프로그램
			FileInputStream fi = new FileInputStream(src);
			// 출력 대상을 읽어서 -> FileOutputStream 에 담기
			FileOutputStream fo = new FileOutputStream(dest);
			// 1바이트 씩 읽어서
			while ((c = fi.read()) != -1) {
				// 1바이트 씩 출력하기.
				fo.write((byte) c);
			}
			// 자원반납
			fi.close();
			fo.close();
			System.out.println(src.getPath() + "를 " + dest.getPath() + "로 복사하였습니다.");
		}

		catch (IOException e) {
			System.out.println("파일 복사 오류");
		}
	}
}
