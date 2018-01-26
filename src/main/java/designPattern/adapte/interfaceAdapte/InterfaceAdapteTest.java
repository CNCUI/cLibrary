package designPattern.adapte.interfaceAdapte;

public class InterfaceAdapteTest {

	public static void main(String[] args) {
		InterfaceAdapte obj = new InterfaceAdapte();
		//调用具体类的适配方法
		obj.androidRun();
		obj.iosRun();
		obj.symbianRun();
	}

}
