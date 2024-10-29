package ex_241023_cha13;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileWriterEx {
	public static void main(String[] args) { 
		// 문자 기반 ,
		// 키보드 입력 된 내용 -> InputStreamReader 에 담기 
		// -> 현재 파일, 자바 응용프로그램
		  InputStreamReader in = new InputStreamReader(System.in); 
		 
		  // 출력 대상 : 1) 콘솔 , V 2) 파일 
		  FileWriter fout = null; 
		  int c; 
		  try { 
		   fout = new FileWriter("c:\\Temp\\test.txt"); 
		   // c = in.read() -> in 에서 한문자 씩 읽기 기능 
		   while ((c = in.read()) != -1) { 
			   // 출력, 파일에 한문자씩 쓰기 
		    fout.write(c); // 키보드로부터 받은 문자를 파일에 저장 
		   } 
		   in.close(); 
		   fout.close(); 
		  }  
		  catch (IOException e) { 
		   System.out.println("입출력 오류"); 
		  } 
		 } 
}
