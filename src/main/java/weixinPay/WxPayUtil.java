package weixinPay;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;

public class WxPayUtil {
	private static WxPayService wxPayService = null;
	
	public static WxPayService getWxPayService(){
		if(wxPayService == null){
			wxPayService = new WxPayServiceImpl();
			WxPayConfig pc = new WxPayConfig();
			pc.setAppId("appid");
			pc.setMchId("mchid");//商户号
			pc.setMchKey("mchkey");
			wxPayService.setConfig(pc);
		}
		return wxPayService;
	}
	

}
