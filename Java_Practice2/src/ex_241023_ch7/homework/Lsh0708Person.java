package ex_241023_ch7.homework;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lsh0708Person {
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dateTime = dateFormat.format(new Date());
	
    private String name;
	private String number;
	private String date=dateTime;
	
	public Lsh0708Person(String name, String number) {
		super();
		this.name = name;
		this.number = number;
		
	}
	public void showInfo() {
		System.out.println("이름 :" + this.name + ", 번호 :" + this.number + ", 저장 일자 :" + this.date);
	}
	
	public String getName() {
		return name;
	}
	
}
