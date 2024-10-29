package ex_241023_cha14;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerEx {
	public static void main(String[] args) {
		// BufferedReader , 문자 기반의 입력 도구
		BufferedReader in = null;
		// BufferedWriter , 문자 기반의 출력 도구
		BufferedWriter out = null;
		// 통신 하기 위한 도구 
		ServerSocket listener = null;
		// 통신 하기 위한 도구
		Socket socket = null;
		// 키보드로 입력된 바이트 코드 읽어서 -> 사용자가 읽기 편한 형태로 제공하는 기능.
		Scanner scanner = new Scanner(System.in); // 키보드에서 읽을 scanner 객체 생성
		try {
			// 파일 입출력, 네트워크 통신을 할 때, 
			// try 구문으로 감싸야합니다.
			// 서버 입장에서, 포트번호 : 9999 라는 번호
			// 문을 열어 두기.
			listener = new ServerSocket(9999); // 서버 소켓 생성
			System.out.println("연결을 기다리고 있습니다.....");
			 // 클라이언트로부터 연결 요청 대기
			// 상대방으로 부터 소켓 연결이 온다면, 이 메서드가 동작하고, 서로 간에 소켓으로 연결됨.
			// 스트림, 연결관이 생겼다.
			socket = listener.accept(); 
			System.out.println("연결되었습니다.");
			// socket.getInputStream() : 상대방으로 부터 전달 받은 데이터를 읽는 도구-> 메모리 임시 저장 
			// new InputStreamReader -> 바이트로 읽은 코드를 읽어서 문자 기반으로 읽기
			// BufferedReader -> 보조 스트림등을 이용해서, 문자 기반으로 읽기.
			// 상대방으로 부터 전달 받은 데이터를 갖고 있다. -> 메모리 상에 
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// 내가 상대방에게 전달할 데이터를 갖고 있다. -> 메모리 상에
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			while (true) {
				// 한문장씩 읽기, in : 상대방으로부터의 메세지를 읽어 놓은 상태. 
				String inputMessage = in.readLine(); // 클라이언트로부터 한 행 읽기
				// inputMessage = bye  이면, 종료
				if (inputMessage.equalsIgnoreCase("bye")) {
					System.out.println("클라이언트에서 bye로 연결을 종료하였음");
					break; // "bye"를 받으면 연결 종료
				}
				// 상대방으로 부터 받은 메세지를 콘솔에 출력. 
				System.out.println("클라이언트: " + inputMessage);
				System.out.print("보내기>>"); // 프롬프트
				// 서버가, 상대방에게 전달할 메세지 , 콘솔에서 입력된 문장 라인을 읽기
				String outputMessage = scanner.nextLine(); // 키보드에서 한 행 읽기
				// out, 상대방에게 전달할 도구 
				out.write(outputMessage + "\n"); // 키보드에서 읽은 문자열 전송
				// 스트림이라는 관이 , 서버와 클라이언트 연결된 관, 입력된 메세지 모두를 전달하는 기능.
				out.flush(); // out의 스트림 버퍼에 있는 모든 문자열 전송
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				scanner.close(); // scanner 닫기
				socket.close(); // 통신용 소켓 닫기
				listener.close(); // 서버 소켓 닫기
			} catch (IOException e) {
				System.out.println("클라이언트와 채팅 중 오류가 발생했습니다.");
			}
		}
	}
}
