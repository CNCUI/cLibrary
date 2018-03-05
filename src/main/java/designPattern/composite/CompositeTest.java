package designPattern.composite;

/**
 * 组合模式
 * @author Cao
 *
 */
public class CompositeTest {

	public static void main(String[] args) {
		Component l1 = new Leaf("xiaomi");
		Component l2 = new Leaf("xiaomi2");
		Component l3 = new Leaf("xiaomi3");
		Component c1 = new Composite("dabai");
		c1.add(l2);
		c1.add(l3);
		l1.display();
		c1.display();
	}

}
