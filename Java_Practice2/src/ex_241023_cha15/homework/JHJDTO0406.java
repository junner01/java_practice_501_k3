package ex_241023_cha15.homework;

public class JHJDTO0406 {
	// 모델 정의, 데이터를 
	// 비지니스 모델 (실제 디비)-> 프레젠테이션 모델(출력 형식)
	String name;
    String email;
    String password;
	
	public JHJDTO0406() {
	
	}
	 
	public JHJDTO0406(String name, String email, String password) {
		this.name = name;
        this.email = email;
        this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
}
