package ex_241023_cha15.homework;

import java.util.ArrayList;

public class Csb806DTO {

	
	String name;
	String email;
	String password;

	public Csb806DTO(String name, String email, String password) {
		super();
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

	@Override
	public String toString() {
	    return "Name: " + name + ", Email: " + email + ", Password: " + password;
	}

	public static ArrayList<Csb806DTO> select() {
		// TODO Auto-generated method stub
		return null;
	}


}

