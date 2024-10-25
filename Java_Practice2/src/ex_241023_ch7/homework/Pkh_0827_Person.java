package ex_241023_ch7.homework;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pkh_0827_Person {

	private String name;
	private String num;
	private String date;

	public Pkh_0827_Person(String name, String num, String date) {
		super();
		this.name = name;
		this.num = num;
		this.date = getCurrentDateTime();
	}

	private String getCurrentDateTime() {
		// TODO Auto-generated method stub
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return LocalDateTime.now().format(formatter);
	}

	public String getName() {
		return name;
	}

	public String getNum() {
		return num;
	}

	public String getDate() {
		return date;
	}
}
