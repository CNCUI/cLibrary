package designPattern.abstractFactory;

public class FactoryProducer {
	public static AbstractFactory getFactory(String fac){
		if("COLOR".equals(fac)){
			return new ColorFactory();
		}else if("FRUITS".equals(fac)){
			return new FruitsFactory();
		}
		return null;
	}
}
