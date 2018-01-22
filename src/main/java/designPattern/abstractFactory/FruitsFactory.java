package designPattern.abstractFactory;

public class FruitsFactory extends AbstractFactory{

	@Override
	Color getColor(String color) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Size getSize(String size) {
		Size s = null;
		if("Apple".equals(size)){
			s = new Apple();
		}else if("Pear".equals(size)){
			s = new Pear();
		}
		return s;
	}

}
