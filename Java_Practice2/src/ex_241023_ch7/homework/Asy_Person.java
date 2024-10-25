package ex_241023_ch7.homework;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Asy_Person {
	private String name;
	private String number;
	private String date;

	public Asy_Person(String name, String number) {
		this.name = name;
		this.number = number;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.date = sdf.format(new Date());
	}

	public String Name() {
		return name;
	}

	public String Number() {
		return number;
	}

	public String Date() {
		return date;
	}
	@Override
	public String toString() {
		return "이름 : " + name + ", 연락처 : " + number + ", 날짜 : " + date ;
	}
}

