package ex_241023_cha14;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientEx {
	public static void main(String[] args) {
		// 상대방으로 부터 전달된 데이터를 읽는 도구
		BufferedReader in = null;
		// 상대방에게 전달할 데이터를 쓰는 도구
		BufferedWriter out = null;
		// 서로 연결하기위한 통신 도구. 
		Socket socket = null;
		Scanner scanner = new Scanner(System.in); // 키보드에서 읽을 scanner 객체 생성
		try {
			// 서버에 연결하기 위한 아이피,
			// 현재 본인이 1인 2역, 
			// 짝지와 같이 할 때, 상다뱅 아이피 입력. "10.100.201.87"
			socket = new Socket("localhost", 9999); // 클라이언트 소켓 생성. 서버에 연결
			// 상대방으로 부터 전달 받은 데이터 읽는 도구
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// 상대방에게 전달할 데이터를 쓰는 도구
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			while (true) {
				
				System.out.print("보내기>>"); // 프롬프트
				// 클 -> 서
				String outputMessage = scanner.nextLine(); // 키보드에서 한 행 읽기
				//outputMessage = bye, 통신 종료 
				if (outputMessage.equalsIgnoreCase("bye")) {
					out.write(outputMessage + "\n"); // "bye" 문자열 전송
					out.flush();
					break; // 사용자가 "bye"를 입력한 경우 서버로 전송 후 실행 종료
				}
				// 클 -> 서 보내는 메세지
				out.write(outputMessage + "\n"); // 키보드에서 읽은 문자열 전송
				out.flush(); // out의 스트림 버퍼에 있는 모든 문자열 전송
				// 클 <- 서 로부터 받는 메세지 
				String inputMessage = in.readLine(); // 서버로부터 한 행 수신
				System.out.println("서버: " + inputMessage);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				scanner.close();
				if (socket != null)
					socket.close(); // 클라이언트 소켓 닫기
			} catch (IOException e) {
				System.out.println("서버와 채팅 중 오류가 발생했습니다.");
			}
		}
	}
}
