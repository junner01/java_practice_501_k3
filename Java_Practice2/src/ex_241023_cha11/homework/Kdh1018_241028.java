package ex_241023_cha11.homework; // 패키지 이름

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Kdh1018_241028 extends JFrame { // JFrame을 상속받는 클래스
    private enum DrawMode { LINE, RECTANGLE, CIRCLE } // 동그라미 모드를 추가
    
    private DrawMode currentMode = DrawMode.LINE; // 기본 모드는 LINE
    private Point startPoint = null; // 도형의 시작점
    private Point endPoint = null; // 도형의 끝점
    private final DrawingCanvas canvas = new DrawingCanvas(); // 그리기 위한 캔버스

    public Kdh1018_241028() {
        // JFrame 기본 설정
        setTitle("선, 사각형 및 원 그리기"); // 창 제목 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // 툴바 생성
        JToolBar toolbar = new JToolBar();
        JButton lineButton = new JButton("선"); // 선 버튼
        JButton rectangleButton = new JButton("사각형"); // 사각형 버튼
        JButton circleButton = new JButton("원"); // 원 버튼 추가

        // 버튼 클릭 시 현재 모드를 변경하는 액션 리스너
        lineButton.addActionListener(e -> currentMode = DrawMode.LINE);
        rectangleButton.addActionListener(e -> currentMode = DrawMode.RECTANGLE);
        circleButton.addActionListener(e -> currentMode = DrawMode.CIRCLE); // 원 모드 설정

        // 툴바에 버튼 추가
        toolbar.add(lineButton);
        toolbar.add(rectangleButton);
        toolbar.add(circleButton); // 원 버튼 추가
        
        // 프레임에 툴바와 캔버스 추가
        add(toolbar, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        
        setVisible(true); // 프레임을 화면에 보이도록 설정
    }

    // 그리기를 위한 캔버스 클래스
    private class DrawingCanvas extends JPanel {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public DrawingCanvas() {
            setBackground(Color.WHITE); // 배경색을 흰색으로 설정
            // 마우스 클릭 이벤트 리스너 추가
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    startPoint = e.getPoint(); // 시작점 설정
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    endPoint = e.getPoint(); // 끝점 설정
                    repaint(); // 도형을 다시 그리기 위해 repaint 호출
                }
            });
        }

        // 도형을 그리는 메서드
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // 기본 컴포넌트 그리기
            if (startPoint != null && endPoint != null) {
                g.setColor(Color.BLACK); // 도형 색상 설정
                if (currentMode == DrawMode.LINE) {
                    // 현재 모드가 LINE일 때 선 그리기
                    g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                } else if (currentMode == DrawMode.RECTANGLE) {
                    // 현재 모드가 RECTANGLE일 때 사각형 그리기
                    int x = Math.min(startPoint.x, endPoint.x); // x좌표
                    int y = Math.min(startPoint.y, endPoint.y); // y좌표
                    int width = Math.abs(startPoint.x - endPoint.x); // 폭
                    int height = Math.abs(startPoint.y - endPoint.y); // 높이
                    g.drawRect(x, y, width, height); // 사각형 그리기
                } else if (currentMode == DrawMode.CIRCLE) {
                    // 현재 모드가 CIRCLE일 때 원 그리기
                    int diameter = (int) Math.max(Math.abs(startPoint.x - endPoint.x), Math.abs(startPoint.y - endPoint.y)); // 지름
                    int x = (int) (startPoint.x - diameter / 2.0); // 원의 왼쪽 위 x좌표
                    int y = (int) (startPoint.y - diameter / 2.0); // 원의 왼쪽 위 y좌표
                    g.drawOval(x, y, diameter, diameter); // 원 그리기
                }
            }
        }
    }

    // 프로그램의 시작점
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Kdh1018_241028::new); // UI 스레드에서 실행
    }
}
