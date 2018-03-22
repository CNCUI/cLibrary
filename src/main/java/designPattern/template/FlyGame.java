package designPattern.template;

public class FlyGame extends Game{

	@Override
	public void init() {
		System.out.println("FlyGame init");
	}

	@Override
	public void start() {
		System.out.println("FlyGame start");
	}

	@Override
	public void end() {
		System.out.println("FlyGame end");
	}
	
}
