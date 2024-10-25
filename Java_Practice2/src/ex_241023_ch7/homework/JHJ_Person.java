package ex_241023_ch7.homework;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JHJ_Person {
    private String name;
    private String number;
    private String addDay;


    public JHJ_Person(String name, String number) {
        this.name = name;
        this.number = number;
        this.addDay = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }



    public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getNumber() {
		return number;
	}



	public void setNumber(String phoneNumber) {
		this.number = phoneNumber;
	}



	public String getAddDay() {
		return addDay;
	}



	public void setAddDay(String registrationDate) {
		this.addDay = registrationDate;
	}



	// 연락처 정보 출력 메서드
    public void displayPersonInfo() {
        System.out.println("이름: " + name);
        System.out.println("연락처: " + number);
        System.out.println("등록 날짜: " + addDay);
        System.out.println("---------------------------");
    }
}