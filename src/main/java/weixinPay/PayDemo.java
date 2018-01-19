package weixinPay;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderCloseResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.util.SignUtils;

public class PayDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 返回前台js预支付信息
	 * @param ebean
	 * @return
	 */
	private String getWxJSPayInfo(Map ebean) {
		String jsPayInfo = null;
		/**1.组织支付参数*/
		Map map = new HashMap();
		/**2、生成微信预支付信息*/
		//返回包含预支付id相关的信息
		WxPayUnifiedOrderResult result = prepay(map);
		if("NATIVE".equals(ebean.get("Zffs"))){
			String qrcodeurl = result.getCodeURL();
			return qrcodeurl;
		}
		String prepayId = result.getPrepayId();
		/**3、生成微信JS支付信息*/
		try {
			/**
			 * 获取支付js所需参数
			 * "appId":"wx123413424234",     //公众号名称，由商户传入     
	           "timeStamp":"1395712654",         //时间戳，自1970年以来的秒数     
	           "nonceStr":"e61463f8efa94090b1f366cccfbbb444", //随机串     
	           "package":"prepay_id=u802345jgfjsdfgsdg888",     
	           "signType":"MD5",         //微信签名方式：     
	           "paySign":"70EA570631E4BB79628FBCA90534C63FF7FADD89" //微信签名 
			 */
			String timestamp = String.valueOf(System.currentTimeMillis() / 1000L);
			String nonceStr = String.valueOf(System.currentTimeMillis());
			Map<String, String> payMap = new HashMap<String, String>();  
			payMap.put("appId", "weixin.AppId");  
			payMap.put("timeStamp", timestamp);  
			payMap.put("nonceStr", nonceStr);  
			payMap.put("package", "prepay_id=" + prepayId);
			payMap.put("signType", "MD5");
	        String paySign = SignUtils.createSign(payMap, "MD5","weixin.mchKey",false);
	        
			// 拼接返回json
			StringBuffer para = new StringBuffer("{");
			para.append("\"orderSn\":\"").append("20180115-0001").append("\",");
			para.append("\"orderLc\":\"").append("01").append("\",");
			para.append("\"para\":{");
			para.append("\"appId\":\"").append("weixin.AppId").append("\",");
			para.append("\"timeStamp\":\"").append(timestamp).append("\",");
			para.append("\"nonceStr\":\"").append(nonceStr).append("\",");
			para.append("\"package\":\"").append("prepay_id=").append(prepayId).append("\",");
			para.append("\"signType\":\"").append("MD5").append("\",");
			para.append("\"paySign\":\"").append(paySign).append("\"");
			para.append("}}");
			
			jsPayInfo = para.toString();
			JSONObject json  = JSONObject.fromObject(jsPayInfo);
		}
		catch (Exception ex) {
			System.out.println("生成微信JS支付信息失败-" + ex.getMessage()+ ex);
			return null;
		}
		
		return jsPayInfo;
	}
	/**
	 * 调用下单接口
	 * @param pay
	 * @return
	 */
	public WxPayUnifiedOrderResult prepay(Map pay){
		WxPayUnifiedOrderResult pur = null;
		WxPayService PayService = WxPayUtil.getWxPayService();
	    String nonceStr = String.valueOf(System.currentTimeMillis());
		WxPayUnifiedOrderRequest wuo = new WxPayUnifiedOrderRequest();
		wuo.setNonceStr(nonceStr);
		
//		wuo.setSign(""); //自动验证
		wuo.setBody("商品描述");
		wuo.setOutTradeNo("20180115-0001");
		wuo.setTotalFee(1);
		wuo.setSpbillCreateIp("127.0.0.1");
		wuo.setNotifyURL("http://ccui.nat100.top/we/notify_wxNotify.t");
		wuo.setTradeType("JSAPI");
		wuo.setOpenid("oKSpAuKqJ9__pGfda9O9yS8kKD70");
		wuo.setProductId("20180115-0001");
		//统一下单接口
		try {
			pur = PayService.unifiedOrder(wuo);
		} catch (WxPayException e) {
			pur = new WxPayUnifiedOrderResult();
			pur.setResultCode("FAIL");
			pur.setReturnMsg("支付失败，请重试。");
			e.printStackTrace();
		}
		return pur;
	}
	
	/**
	 * 关闭订单，用于扫码支付，防止重复支付
	 * @param ddbh
	 * @return
	 * @throws WxPayException
	 */
	public WxPayOrderCloseResult closeOrder(String ddbh) throws WxPayException {
		WxPayService payService = WxPayUtil.getWxPayService();
		return payService.closeOrder(ddbh);
	}
}


/*
//微信支付
WEC.onBridgeReady = function(result){
	WeixinJSBridge.invoke('getBrandWCPayRequest', result.para,
		function(res){
			var rediretUrl = "";
			// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
           	if(res.err_msg == "get_brand_wcpay_request:ok") {
           		rediretUrl = "wxjf_paySuccess.t?orderSn="+ result.orderSn;
           	}
           	// 支付过程中用户取消
           	else if(res.err_msg == "get_brand_wcpay_request:cancel"){
           		rediretUrl = "wxjf_payCancel.t";
           	}
         	// 支付失败
           	else if(res.err_msg == "get_brand_wcpay_request:fail"){
           		rediretUrl = "wxjf_payCancel.t";
           	}
           	$(location).attr('href', rediretUrl);
       }
   ); 
};


$.post("<%=path%>/wwx/wxjf_wxjf.json",param,function(data){
			if(data.success){
				if (typeof WeixinJSBridge == "undefined"){
					if( document.addEventListener ){
						document.addEventListener('WeixinJSBridgeReady', WEC.onBridgeReady, false);
					}else if (document.attachEvent){
						document.attachEvent('WeixinJSBridgeReady', WEC.onBridgeReady); 
					   	document.attachEvent('onWeixinJSBridgeReady', WEC.onBridgeReady);
					}
				}else{
					var result = JSON.parse(data.message); 
					WEC.onBridgeReady(result);
				}
			}else{
				alert(data.message);
			}
		},"json");

*/