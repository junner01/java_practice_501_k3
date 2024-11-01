package ex_241023_cha15.homework;

public class Hcb0402_DTO {
	// property
	int id;
	String name;
	String email;
	String password;
	
	// constructor
	public Hcb0402_DTO() {};
	//insert constructor
	public Hcb0402_DTO(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}
	// read constructor
	public Hcb0402_DTO(int id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	// getter setter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
