package ex_241023_cha12;

public class SynchronizedEx {
	public static void main(String[] args) {
		// 공유자원, 예시,
		SharedPrinter p = new SharedPrinter(); // 공유 데이터 생성
		String[] engText = { "Wise men say, ", "only fools rush in", "But I can't help, ", "falling in love with you",
				"Shall I stay? ", "Would it be a sin?", "If I can't help, ", "falling in love with you" };
		String[] korText = { "동해물과 백두산이 마르고 닳도록, ", "하느님이 보우하사 우리 나라 만세", "무궁화 삼천리 화려강산, ", "대한 사람 대한으로 길이 보전하세",
				"남산 위에 저 소나무, 철갑을 두른 듯", "바람서리 불변함은 우리 기상일세.", "무궁화 삼천리 화려강산, ", "대한 사람 대한으로 길이 보전하세" };
		// A 클래스, B 클래스 -> 주입, 인스턴스 전달.
		// p : SharedPrinter의 인스턴스와 , engText 문자열
		Thread th1 = new WorkerThread(p, engText);// 영문출력스레드
		Thread th2 = new WorkerThread(p, korText);// 국문출력스레드

		// 두 스레드를 실행시킨다.
		th1.start();
		th2.start();
	}
}

// 두 WorkerThread 스레드에 의해 동시 접근되는 공유 프린터
class SharedPrinter {
	// synchronized를 생략하면
	// 한글과 영어가 한 줄에 섞여 출력되는 경우가 발생한다.
	// 해결책
	synchronized void print(String text) {
		// 문제점 제시
//			 void print(String text) {
		// Thread.yield();
		for (int i = 0; i < text.length(); i++)
			System.out.print(text.charAt(i));
		System.out.println();
	}
}

// 스레드 클래스
class WorkerThread extends Thread {

	// B라는 클래스는 , 전역으로 선언해서,
	private SharedPrinter p; // 공유 프린터 주소
	private String[] text;

	// 생성자로 초기화 해주고 있음.
	public WorkerThread(SharedPrinter p, String[] text) {
		this.p = p;
		this.text = text;
	}

	// 스레드는 반복적으로 공유 프린터에 10번 접근 text[] 출력
	@Override
	public void run() {
		for (int i = 0; i < text.length; i++) // 한 줄씩 출력
			p.print(text[i]); // 공유 프린터에 출력
	}
}



