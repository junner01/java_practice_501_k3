package ex_241023_ch7.homework;
import java.time.LocalDateTime;

public class Cgw0910Person {
	public void setPerson(String name, String phoneNum, LocalDateTime userSaveDate) {
		this.name = name;
		this.phoneNum = phoneNum;
		this.userSaveDate = userSaveDate;
	}
	public void showInfo() {
		System.out.println("유저 이름은 : " + name);
		System.out.println("유저 연락처는 : " + phoneNum);
		System.out.println("유저 등록 날짜는 : " + userSaveDate);
	}
	public String getName() {
		return name;
	}
	private String name;
	private String phoneNum;
	private LocalDateTime userSaveDate;
}

