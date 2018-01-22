package designPattern.abstractFactory;

public class ColorFactory extends AbstractFactory{

	@Override
	Color getColor(String color) {
		Color c = null;
		if("Green".equals(color)){
			c = new Green();
		}else if("Red".equals(color)){
			c = new Red();
		}
		return c;
	}

	@Override
	Size getSize(String size) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
