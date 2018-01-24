package weixinPay;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpKefuService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage.WxArticle;

public class SendMessage {
	/**
	 * 给用户发送消息
	 * @param openid
	 * @param title
	 * @param description
	 * @param url
	 * @param picurl
	 * @throws WxErrorException
	 */
	public static void sendNewsMessageToOpenid(String openid,String title,String description,String url,String picurl) throws WxErrorException{
		WxMpService wxMpService = WxService.getWxService();
		WxArticle article1 = new WxMpKefuMessage.WxArticle();
		article1.setUrl(url);
		article1.setPicUrl(picurl);
		article1.setDescription(description);
		article1.setTitle(title);
		WxMpKefuMessage text = WxMpKefuMessage.NEWS().toUser(openid).addArticle(article1).build();
		WxMpKefuService s = wxMpService.getKefuService();
		s.sendKefuMessage(text);
		
     }
}
