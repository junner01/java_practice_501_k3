package ex_241023_cha11.homework;

import javax.swing.*;
import java.awt.*;

public class Hcb0402_bar extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//property
	private int[] barSize; //매개변수를 받아 쓰기에 배열로 바꾸어줌
	private int maxBarSize;
	//property
	
	
	//constructor
	//int를 매개변수로 받아 maxBarSize에 할당하고 인스턴스 생성하던걸 
	//TabandThreadEx에서 배열 포인터를 매개변수로 받아 bar class의 property에 할당
	public Hcb0402_bar(int maxBarSize, int[] barSize) {
		this.maxBarSize = maxBarSize;
		this.barSize = barSize;
		//이 barSize는 TabandThreadEx의 barSize와 같은 메모리를 가르킴
		//아래로 int를 쓰던 barSize는 전부 0번인덱스를 사용하면 된다
	}//constructor

	
	//그리기 구현
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.MAGENTA);
		int width = (int) (((double) (this.getWidth())) / maxBarSize * barSize[0]);
		if (width == 0)
			return;
		g.fillRect(0, 0, width, this.getHeight());
	}//그리기 구현
	
	
	//fill method
	//synchronized로 이 method가 실행되는동안 consume은 대기지만
	//지속해서 notify 하고 있으니 consume이 호출되면 consume이 실행됨
	//가득 차면 자동적으로 wait이 걸림
	synchronized void fill() {
		if (barSize[0] == maxBarSize) {
			try {
				wait();
			} catch (InterruptedException e) {
				return;
			}
		}
		barSize[0]++;
		repaint(); // 바 다시 그리기
		notify();
	}//fill method

	
	//consume method
	//consume과 반대 이 method가 실행되는 동안 fill은 대기
	//그러나 notify가 계속 호출되므로 언제든 fill이 호출된다면 fill이 우선, 이유는 제일 아래
	synchronized void consume() {
		if (barSize[0] == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				return;
			}
		}
		barSize[0]--;
		repaint(); // 바 다시 그리기
		notify();
	}//consume method
}//Hcb0402_bar class


//thread class
class ConsumerThread extends Thread {
	//property
	private Hcb0402_bar bar;
	//property
	
	//constructor
	public ConsumerThread(Hcb0402_bar bar) {
		this.bar = bar;
	}//constructor
	
	//thread 구현 계속해서 bar에 consume을 호출하는 thread
	@Override
	public void run() {
		while (true) {
			try {
				sleep(200);
				bar.consume();
			} catch (InterruptedException e) {
				return;
			}
		}
	}//thread 구현
}
//결과적으로 thread로 0.2초마다 consume이 호출됨
//barSize == 0일땐 호출되어도 wait로 움직이지 않음
//그리고 키를 누르면 fill이 호출됨
//barSize == maxBarSize 면 wait로 더이상 움직이지 않음
//따라서 0.2초보다 빠르게 누른다면 bar의width는 커짐
//synchronized의 의미는 위처럼 barSize가 0과 max일때 더이상 움직이지 않게 하는 의미도 있고
//fill과 consume이 겹쳐서 에러 발생하는것도 막을 수 있음
