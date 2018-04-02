package designPattern.facade;

/**
 * 1、为复杂的模块或子系统提供外界访问的模块。 2、子系统相对独立。 3、预防低水平人员带来的风险。
 * @author Cao
 *
 */
public class FacedTest {
	private Circle circle = null;
	private Rectangle rectangle = null;
	private Square square = null;
	public FacedTest(){
		circle = new Circle();
		rectangle = new Rectangle();
		square = new Square();
	}
	public void drawCircle(){
		circle.draw();
	}
	public void drawRectangle(){
		rectangle.draw();
	}
	public void drawSquare(){
		square.draw();
	}
	public static void main(String[] args) {
		FacedTest t = new FacedTest();
		t.drawCircle();
		t.drawRectangle();
		t.drawSquare();
	}

}
