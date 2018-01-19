package designPattern.singelTon;

enum EnumSingleton {
	INSTANCE;
	private T t;
	EnumSingleton() {
		t = new T();
	}
	public T getInstance(){
		return t;
	}
}

class T{
	
}

