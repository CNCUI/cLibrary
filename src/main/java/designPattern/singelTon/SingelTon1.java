package designPattern.singelTon;

/**
 * 懒汉式单例模式
 * @author Cao
 *
 */
public class SingelTon1 {
	/* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */
	private static SingelTon1 instance = null;

	/* 私有构造方法，防止被实例化 */
	private SingelTon1() {
	}

	public static SingelTon1 getInstance() {
		if (instance == null) {
			instance = new SingelTon1();
		}
		return instance;
	}

	/* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
	public Object readResolve() {
		return instance;
	}

	/**
	 * 这个类可以满足基本要求，但是，像这样毫无线程安全保护的类，如果我们把它放入多线程的环境下，肯定就会出现问题了，如何解决？我们首先会想到对getInstance方法加synchronized关键字，如下：
	 * 
	 * @return
	 */
	// public static synchronized SingelTon1 getInstance() {
	// if (instance == null) {
	// instance = new SingelTon1();
	// }
	// return instance;
	// }

	/**
	 * 但是，synchronized关键字锁住的是这个对象，这样的用法，在性能上会有所下降，因为每次调用getInstance()，都要对对象上锁，事实上，只有在第一次创建对象的时候需要加锁，之后就不需要了，所以，这个地方需要改进。我们改成下面这个：
	 */
//	public static SingelTon1 getInstance() {
//		if (instance == null) {
//			synchronized (instance) {
//				if (instance == null) {
//					instance = new SingelTon1();
//				}
//			}
//		}
//		return instance;
//	}
}
