package ex_241022_ch4_2.homework;

public class Kjy0227_Frog extends Kjy0227_Animal {

	public Kjy0227_Frog(String name, String lifespan) {
		super(name, "양서류", lifespan);
	}

	@Override
	public void sound() {
		System.out.println("개굴개굴");
	}

}
