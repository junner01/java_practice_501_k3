package ex_241023_ch7.homework;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Shw1013_Person {
	private String name;
	private String phone;
	private String currentTime;

	public Shw1013_Person(String name, String phone) {
		this.name = name;
		this.phone = phone;
		this.currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getRegistrationDate() {
		return currentTime;
	}

	public void displayInfo() {
		System.out.println("이름: " + name + ", 연락처: " + phone + ", 등록 날짜: " + currentTime);
	}

}
