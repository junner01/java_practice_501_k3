package ex_241023_ch7.homework;

public class Csb806Class {
	private String name;
	private String phoneNumber;
	private String registrationDate;

	public Csb806Class(String name, String phoneNumber, String registrationDate) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.registrationDate = registrationDate;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void printPersonInfo() {
		System.out.println("이름:" + name + " 연락처:" + phoneNumber + " 등록날짜 :" + registrationDate);
	}

}

