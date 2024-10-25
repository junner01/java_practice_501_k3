package ex_241023_ch7.homework;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Kjy0227_Person {
	private String name;
	private String phoneNum;
	private LocalDateTime cdt;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public LocalDateTime getCdt() {
		return cdt;
	}
	public void setCdt(LocalDateTime localDateTime) {
		this.cdt = localDateTime;
	}
	
	@Override
	public String toString() {
		return "이름 = " + name + ", 연락처 = " + phoneNum +
				", 등록날짜 = " + cdt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
	}
	
}
