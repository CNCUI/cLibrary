package designPattern.adapte.objAdapte;

/**
 * 对象适配器通过接收不同对象来进行适配
 * 可以定义多个类别的适配器进行解耦
 * @author Cao
 *
 */
public class PhoneAdapte{
	private Android android = null;
	public PhoneAdapte(Android an){
		this.android = an;
	}
	
	public void phoneRun(){
		this.android.androidRun();
	}
	
	public void phoneRun(Apple app) {
		app.iosRun();
	}
	public void phoneRun(Symbian sy){
		sy.symbianRun();
	}
}
