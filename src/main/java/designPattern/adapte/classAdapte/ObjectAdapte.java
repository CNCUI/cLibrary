package designPattern.adapte.classAdapte;

import designPattern.adapte.objAdapte.Apple;
import designPattern.adapte.objAdapte.Huawei;
import designPattern.adapte.objAdapte.Symbian;

/**
 * 继承Apple类并扩展接口
 * 类适配器只能对具体的类进行适配，比如androidRun只能适配一种安卓手机
 * @author Cao
 *
 */
public class ObjectAdapte extends Apple implements AndroidInterface,IosInterface,SymbianInterface{

	@Override
	public void androidRun() {
		Huawei hua = new Huawei();
		hua.androidRun();
	}

	@Override
	public void symbianRun() {
		Symbian sy = new Symbian();
		sy.symbianRun();
	}

	@Override
	public void iosRun() {
		Apple ap = new Apple();
		ap.iosRun();
	}
	
}
