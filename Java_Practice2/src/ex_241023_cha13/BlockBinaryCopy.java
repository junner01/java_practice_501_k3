package ex_241023_cha13;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BlockBinaryCopy {
	public static void main(String[] args) {
		// 입력 대상
		File src = new File("c:\\Temp\\test1.jpg");
		// 출력 대상 
		File dest = new File("c:\\Temp\\copyTest1.jpg");
		try {
			// 원본 이미지 읽어서 -> FileInputStream 에 담기
			FileInputStream fi = new FileInputStream(src); // 파일 입력 바이트 스트림 생성
			// 복사본 이미지 파일 읽어서 -> FileOutputStream 에 담기, 출력 대상 이미지 파일. 
			FileOutputStream fo = new FileOutputStream(dest); // 파일 출력 바이트 스트림 생성

			// 임시 배열 만들기, 바이트 타입으로 
			byte[] buf = new byte[1024 * 10]; // 10KB 버퍼
			while (true) {
				// 다른점, 읽을 때, 특정 배열의 크기만큼 씩 읽기
				int n = fi.read(buf); // 버퍼 크기만큼 읽기. n은 실제 읽은 바이트
				// 다른점, 쓰기 작업 할  때, 특정 배열의 크기만큼 씩 쓰기
				// 
				// buf, 임시 저장 공간
				// fi , 읽어서 buf 만큼 씩 읽고, 
				// fo, buf 만큼 씩 쓰기 작업을 진행함. 
				
				fo.write(buf, 0, n); // buf[0]부터 n 바이트 쓰기
				if (n < buf.length)
					break; // 버퍼 크기보다 작게 읽었기 때문에 파일 끝에 도달. 복사 종료
			}
			fi.close();
			fo.close();
			System.out.println(src.getPath() + "를 " + dest.getPath() + "로 복사하였습니다.");
		} catch (IOException e) {
			System.out.println("파일 복사 오류");
		}
	}
}
