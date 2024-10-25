package ex_241022_ch4_2.homework;

public class Kjy0227_Bird extends Kjy0227_Animal {

	public Kjy0227_Bird(String name, String lifespan) {
		super(name, "조류", lifespan);
	}

	@Override
	public void sound() {
		System.out.println("찌르르");
	}

}
