package designPattern.factory;

public class Factory {
	// public Shape getShap(String shap){
	// Shape s = null;
	// if("Circle".equals(shap)){
	// s = new Circle();
	// }else if("Square".equals(shap)){
	// s = new Square();
	// }
	// return s;
	// }

	public static void main(String[] args) {
		// Factory f = new Factory();
		// Shape s1 = f.getShap("Circle");
		// Shape s2 = f.getShap("Square");
		// s1.draw();
		// s2.draw();

		// 拓展
		Circle rect = (Circle) Factory.getShap2(Circle.class);
		rect.draw();
		Square square = (Square) Factory.getShap2(Square.class);
		square.draw();
	}

	/**
	 * 拓展——使用反射机制可以解决每次增加一个产品时，都需要增加一个对象实现工厂的缺点
	 * 
	 * @param clazz
	 * @return
	 */
	public static Object getShap2(Class<? extends Shape> clazz) {
		Object obj = null;

		try {
			obj = Class.forName(clazz.getName()).newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return obj;
	}
}
