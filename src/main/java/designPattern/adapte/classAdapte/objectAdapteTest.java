package designPattern.adapte.classAdapte;

public class objectAdapteTest {
	public static void main(String[] args) {
		ObjectAdapte obj = new ObjectAdapte();
		//调用具体类的适配方法
		obj.androidRun();
		obj.iosRun();
		obj.symbianRun();
	}
}
