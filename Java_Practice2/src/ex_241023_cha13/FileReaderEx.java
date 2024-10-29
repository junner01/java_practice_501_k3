package ex_241023_cha13;

import java.io.FileReader;
import java.io.IOException;

public class FileReaderEx {
	public static void main(String[] args) { 
		  FileReader in = null; 
		  // 파일 입출력시, 반드시 , try 구문으로 감싸지 않으면, 컴파일러 에러가남. 
		  // 그래서, 반드시 try 로 구문 안에서, 입출력하기.
		  
		  // FileReader 이용해서, 파일 읽기 -> FileReader(인스턴스) 담기.
		  // in 에 -> c:\\windows\\system.ini , 경로에서
		  // 읽은 문자들을 메모리상에 임시보관
		  // 입력 대상, V 1) 파일 , 2) 키보드에 입력된 값들
		  // in 에서 하나씩 꺼내서, 출력 대상  : V 1)콘솔 2) 파일 3) 데이터베이스 4) 네트워크 
		  try { 
		   in = new FileReader("c:\\windows\\system.ini");  
		   int c; 
		   // 한 문자 읽어서 -> char 타입으로 변환, 유니코드 내용.
		   // 메모리안에 문자가 있으면, 음수가 아니고 -> 참
		   // 메모리안에 문자가 없으면, 음수가 됨. -> 거짓
		   while ((c = in.read()) != -1) { // 한 문자씩 파일 끝까지 읽는다. 
		    System.out.print((char)c); 
		   } 
		   // 사용후 자원 반납.
		   in.close(); 
		  } 
		  catch (IOException e) { 
		   System.out.println("입출력 오류"); 
		  } 
		 } 
}
