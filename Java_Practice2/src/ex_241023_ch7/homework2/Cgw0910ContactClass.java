package ex_241023_ch7.homework2;
import java.time.LocalDateTime;

public class Cgw0910ContactClass {
	public void setPerson(String name, String phoneNum) {
		this.phoneNum = phoneNum;
		this.userSaveDate = LocalDateTime.now();
	}
	public void showInfo() {
		System.out.println("유저 연락처는 : " + phoneNum);
		System.out.println("유저 등록 날짜는 : " + userSaveDate);
	}
	private String phoneNum;
	private LocalDateTime userSaveDate;
}


