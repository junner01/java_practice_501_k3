package ex_241022_ch4_2.homework;

public class Kjy0227_Cat extends Kjy0227_Animal {

	public Kjy0227_Cat(String name, String lifespan) {
		super(name, "포유류", lifespan);
	}

	@Override
	public void sound() {
		System.out.println("야옹~");
	}
	
}
