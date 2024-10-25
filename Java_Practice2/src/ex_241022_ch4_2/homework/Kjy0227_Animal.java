package ex_241022_ch4_2.homework;

public class Kjy0227_Animal {
	public String name;
	public String type;
	public String lifespan;
	
	public Kjy0227_Animal(String name, String type, String lifespan) {
		this.name = name;
		this.type = type;
		this.lifespan = lifespan;
	}

	public void sound() {
		System.out.println("...");
	}
	
	public void introduce() {
		System.out.println(name + " : (타입 : " + type + ", 수명 : " + lifespan + ")");
	}
}
