package ex_241023_cha15.homework;

public class Kdh1018DTO_241031 {

	private String name;
	private String email;
	private String password;

	public Kdh1018DTO_241031(String name, String email, String password) {
		// 문자열로 name, email, password 만들기
		// this 이용해서 선언
		this.name = name;
		this.email = email;
		this.password = password;
	}

	//get으로 받고 return
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}
