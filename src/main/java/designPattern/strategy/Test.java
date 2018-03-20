package designPattern.strategy;

public class Test {
	public static void main(String[] args) {
		Context c = new Context(new AddOpetion());
		Context c2 = new Context(new SubOperation());
		System.out.println(c.executeStrategy(1, 2));
		System.out.println(c2.executeStrategy(1, 2));
	}

}
