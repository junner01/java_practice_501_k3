package ex_241023_cha15.homework.lsy1205Test;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LsyTest1205Splash extends JFrame {

	public LsyTest1205Splash() {
		// 스플래시 화면 프레임 생성
		setUndecorated(true); // 테두리 없는 창
		setSize(1000, 800);
		setLocationRelativeTo(null); // 화면 중앙에 위치
		setLayout(new BorderLayout());

		// 이미지 라벨
		ImageIcon originalIcon = new ImageIcon("images/test1.jpg"); // 이미지 파일 경로
		Image scaledImage = originalIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		 // 이미지 라벨 생성
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
		// 환영 메시지 라벨
		JLabel welcomeLabel = new JLabel("환영합니다!", SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24));
		welcomeLabel.setVerticalAlignment(SwingConstants.CENTER);

		// 스플래시 화면 구성
		add(imageLabel, BorderLayout.CENTER);
		add(welcomeLabel, BorderLayout.SOUTH);

		setVisible(true);

	}

	public void closeSplash() {
		dispose();
	}
}




