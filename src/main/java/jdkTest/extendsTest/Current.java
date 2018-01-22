package jdkTest.extendsTest;

/**
 * 测试子类调用父类的父类的方法
 * 类比java集合类，父类重写toString()方法，print子类时打印元素而不是地址
 * @author Cao
 *
 */
public class Current extends Father{
	public static void main(String[] args) {
		Current c = new Current();
		c.grand();
	}
}
