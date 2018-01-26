package designPattern.adapte.objAdapte;

public class AdapteTest {
	public static void main(String[] args){
		PhoneAdapte ph = new PhoneAdapte(new Huawei());
		PhoneAdapte ph2 = new PhoneAdapte(new Xiaomi());
		//调用相同名称方法，传递不同对象
		ph.phoneRun();
		ph2.phoneRun();
		
		ph.phoneRun(new Apple());
		ph.phoneRun(new Symbian());
	}
}
