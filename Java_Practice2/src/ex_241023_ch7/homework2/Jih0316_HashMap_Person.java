package ex_241023_ch7.homework2;

public class Jih0316_HashMap_Person {
	private String name;
	private String phone;
	private String regTime;

	public Jih0316_HashMap_Person(String name, String phone, String registrationTime) {
		this.name = name;
		this.phone = phone;
		this.regTime = registrationTime;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getRegistrationTime() {
		return regTime;
	}

	@Override
	public String toString() {
		return "이름: " + name + ", 연락처: " + phone + ", 등록 시간: " + regTime;
	}
}