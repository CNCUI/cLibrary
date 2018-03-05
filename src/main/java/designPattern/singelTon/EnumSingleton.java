package designPattern.singelTon;
/**
 * 枚举单例模式
 * @author Cao
 *
 */
enum EnumSingleton {
	INSTANCE;
	private T t;
	EnumSingleton() {
		t = new T();//构造方法只会执行一次
	}
	public T getInstance(){
		return t;
	}
}

class T{
	
}

