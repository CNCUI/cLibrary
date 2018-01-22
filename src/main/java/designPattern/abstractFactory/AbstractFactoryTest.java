package designPattern.abstractFactory;

public class AbstractFactoryTest {
	public static void main(String[] args) {
		AbstractFactory a1 = FactoryProducer.getFactory("COLOR");
		Red red = (Red) a1.getColor("Red");
		Green green = (Green) a1.getColor("Green");
		red.draw();
		green.draw();
		
		AbstractFactory a2 = FactoryProducer.getFactory("FRUITS");
		Apple apple = (Apple) a2.getSize("Apple");
		Pear pear = (Pear) a2.getSize("Pear");
		apple.size();
		pear.size();
	}
}
