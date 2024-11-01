package ex_241023_cha15.homework;

public class Lsh0708_DTO {
	
	String userId;
	String userPass;
	String userMail;
	
	public Lsh0708_DTO(String userId, String userPass, String userMail) {
		super();
		this.userId = userId;
		this.userPass = userPass;
		this.userMail = userMail;
	}
	public Lsh0708_DTO() {};

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	
}
