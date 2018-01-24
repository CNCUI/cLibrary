package weixinPay;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

public class WxService
{
	private static WxMpInMemoryConfigStorage wxConfigProvider = new WxMpInMemoryConfigStorage();
	private static WxMpService service = new WxMpServiceImpl();
	private WxService(){};
	private static WxMpService buildWxService(String appId,String Secret) {
		wxConfigProvider.setAppId(appId);
		wxConfigProvider.setSecret(Secret);
		service.setWxMpConfigStorage(wxConfigProvider);
		return service;
	}
	public static WxMpService getWxService(){
		if(wxConfigProvider.getAppId()!=null && !"".equals(wxConfigProvider.getAppId())){
			return service;
		}else{
			return WxService.buildWxService("AppId自己填写","Secret自己填写");
		}
	}
	
	public static String getOauth2Url(String url) {
		WxMpService service = getWxService();
		return service.oauth2buildAuthorizationUrl(url,"snsapi_userinfo","STATE");
	}
}