package designPattern.abstractFactory;

public abstract class AbstractFactory {
	abstract Color getColor(String color);
	abstract Size getSize(String size) ;
}
