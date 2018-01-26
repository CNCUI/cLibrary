package designPattern.adapte.interfaceAdapte;

import designPattern.adapte.objAdapte.Apple;
import designPattern.adapte.objAdapte.Huawei;
import designPattern.adapte.objAdapte.Symbian;

public class InterfaceAdapte extends AbstractCommon{

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
