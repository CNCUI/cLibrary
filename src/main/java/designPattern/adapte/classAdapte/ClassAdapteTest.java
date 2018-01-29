package designPattern.adapte.classAdapte;

public class ClassAdapteTest {
	public static void main(String[] args) {
		ClassAdapte obj = new ClassAdapte();
		//调用具体类的适配方法
		obj.androidRun();
		obj.iosRun();
		obj.symbianRun();
	}
}
