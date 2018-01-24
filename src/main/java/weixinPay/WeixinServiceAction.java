package weixinPay;

import java.io.InputStream;
import java.util.Map;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

public class WeixinServiceAction {
	public String sign() throws Exception {
		Map verificMap = getParameter();
		if (verificMap.get("echostr") != null) {
			return renderText(verificMap.get("echostr") != null ? (String) verificMap.get("echostr") : "err:NULL");
		}

		InputStream is = getRequest().getInputStream();
		if (is != null) {
			WxMpXmlMessage xmlMessage = WxMpXmlMessage.fromXml(is);
			WxMpService wxMpService = WxService.getWxService();
			WxMpMessageRouter router = new WxMpMessageRouter(wxMpService);
			router.rule().async(false).msgType("event").event("subscribe").handler(new SubscribeMessageHandler()).end()
					.rule().msgType("text").handler(new KeyWordsMessageHandler()).end();

			WxMpXmlOutMessage reMsg = router.route(xmlMessage);
			if (reMsg != null) {
				return renderXML(reMsg.toXml());
			}

		}

		return null;
	}

	//为了不报错
	private String renderXML(String xml) {return null;}
	private WeixinServiceAction getRequest() {return null;}
	private InputStream getInputStream() {return null;}
	private String renderText(String string) {return null;}
	private Map getParameter() {return null;}
}
class SubscribeMessageHandler implements WxMpMessageHandler{
	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
		return null;
	}
	
}
class KeyWordsMessageHandler implements WxMpMessageHandler{
	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
	    String openId = wxMessage.getFromUser();
	    //给用户发送图文消息
	    SendMessage.sendNewsMessageToOpenid(openId,"url","PicUrl","description","title");
		return null;
	}
	
}