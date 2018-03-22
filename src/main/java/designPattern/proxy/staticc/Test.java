package designPattern.proxy.staticc;

public class Test {
	public static void main(String[] args) {
		ManW w = new ManW();
		Italk italk = new ProxyMan(w);
		italk.talk("hello");
	}
}
