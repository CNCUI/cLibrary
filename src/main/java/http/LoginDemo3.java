package http;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

public class LoginDemo3 {
	private String GET_METHOD = "GET";
	private String POST_METHOD = "POST";
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		//获取cookie
		String url = "http://localhost/login.t";
		Map<String,String> cookiemap = getCookie(url);
		
		//获取codeid
		String getCodeUrl = "http://localhost/verificationCode_random.json";
		String code = getCode(getCodeUrl, cookiemap.get("cookie"));//code
		// System.out.println(code);

		//登录
		String loginUrl = "http://localhost/login_submit.json";
		Map<String,String> param = new HashMap<String,String>();
		param.put("codeid", code);
		param.put("username", "admin");
		param.put("password", "11111");
		String loginSuccess = login(loginUrl,cookiemap.get("cookie"),param);
		
		//访问主页
		String indexUrl = "http://localhost/xuexiao/findXxXmzjps_init.t?_="+(new Date()).getTime();
		String indexSuccess = goIndex(indexUrl,cookiemap.get("cookie"));
	}

	
	private static String goIndex(String indexUrl, String cookie) throws ClientProtocolException, IOException {
		String[] item = cookie.split(";");
		String frcs = "";
		for (String it : item) {
			if (it.startsWith("__frcs")) {
				frcs = it.replace("__frcs=", "");
			}
		}
		
		HttpGet httpGet = initHttpGet(indexUrl);
		httpGet.setHeader("Cookie", "hash=findXxXmzjps;"+cookie);
		httpGet.setHeader("frcs", frcs);
		
		HttpClient httpClient = new DefaultHttpClient();
		
		HttpResponse httpResponse = httpClient.execute(httpGet);
		Header[] header = httpResponse.getAllHeaders();
		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			HttpEntity httpEntity = httpResponse.getEntity();
			String result = EntityUtils.toString(httpEntity);// 取出应答字符串
			// 一般来说都要删除多余的字符
			result.replaceAll("\r", "");// 去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
			System.out.println(result);
		}
		return null;
	}

	private static String login(String loginUrl, String cookie, Map<String, String> param) throws ClientProtocolException, IOException {
		String userInfomation = "";
		String[] item = cookie.split(";");
		String frcs = "";
		for (String it : item) {
			if (it.startsWith("__frcs")) {
				frcs = it.replace("__frcs=", "");
			}
		}
		
		HttpPost httpPost = initHttpPost(loginUrl);
		httpPost.setHeader("Cookie", cookie+"hash=start;");
		httpPost.setHeader("frcs", frcs);
		HttpClient httpClient = new DefaultHttpClient();
		
		String codeid = param.get("codeid");
		String username = param.get("username");
		String password = param.get("password");
		String queryCase = "codeid="+codeid+"&username="+username+"&password="+password;
		StringEntity reqEntity = new StringEntity(queryCase);
		httpPost.setEntity(reqEntity);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		Header[] header = httpResponse.getAllHeaders();

		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			HttpEntity httpEntity = httpResponse.getEntity();
			String result = EntityUtils.toString(httpEntity);// 取出应答字符串
			// 一般来说都要删除多余的字符
			result.replaceAll("\r", "");// 去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
			JSONObject json = JSONObject.fromObject(result);
			System.out.println(json);
			userInfomation = json.getString("data");
//			cookiemap.put("cookie", updateCookie(httpResponse.getAllHeaders()));
		}
		return userInfomation;
	}

	public static String doPost(String url, String cookie) {
		
		return null;
	}

	public static Map<String, String> getCookie(String url) throws ClientProtocolException, IOException {
		Map<String, String> map = new HashMap<>();
		String cookie = "";
		HttpGet httpget = new HttpGet(url);
		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse httpResponse = httpClient.execute(httpget);
		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			HttpEntity httpEntity = httpResponse.getEntity();
			String htmlResult = EntityUtils.toString(httpEntity);// 取出应答字符串
			// 一般来说都要删除多余的字符
			htmlResult.replaceAll("\r", "");

			Header[] header = httpResponse.getAllHeaders();
			for (Header h : header) {
				if ("Set-Cookie".equals(h.getName())) {
					cookie += h.getValue() + ";";
				}
			}
		}
		cookie = cookie.replaceAll(" Path=/;", "").replaceAll(" HttpOnly;", "");
		map.put("cookie", cookie);
		return map;
	}
	public static String updateCookie(Header[] headerr) throws ParseException, IOException{
		String cookie = "";

		Header[] header = headerr;
		for (Header h : header) {
			if ("Set-Cookie".equals(h.getName())) {
				cookie += h.getValue() + ";";
			}
		}
		cookie = cookie.replaceAll(" Path=/;", "").replaceAll(" HttpOnly;", "");
		return cookie;
	}

	public static String getCode(String url, String cookiee) throws ClientProtocolException, IOException {
		String codeid = "";
		String cookie = cookiee;
		String[] item = cookie.split(";");
		String frcs = "";
		for (String it : item) {
			if (it.startsWith("__frcs")) {
				frcs = it.replace("__frcs=", "");
			}
		}
		HttpPost httpPost = initHttpPost(url);
		httpPost.setHeader("Cookie", cookie+"hash=start;");
		httpPost.setHeader("frcs", frcs);
		HttpClient httpClient = new DefaultHttpClient();

		String queryCase = "num=4";
		StringEntity reqEntity = new StringEntity(queryCase);
		httpPost.setEntity(reqEntity);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		Header[] header = httpResponse.getAllHeaders();

		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			HttpEntity httpEntity = httpResponse.getEntity();
			String result = EntityUtils.toString(httpEntity);// 取出应答字符串
			// 一般来说都要删除多余的字符
			result.replaceAll("\r", "");// 去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
			JSONObject json = JSONObject.fromObject(result);
			System.out.println(json);
			codeid = json.getString("codeid");
			
//			cookiemap.put("cookie", updateCookie(httpResponse.getAllHeaders()));
		}
		return codeid;
	}
	
	private static HttpGet initHttpGet(String url){
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept","text/html, */*; q=0.01");
		httpGet.setHeader("Accept-Encoding","gzip, deflate, br");
		httpGet.setHeader("Accept-Language","zh-CN,zh;q=0.9");
		httpGet.setHeader("Connection","keep-alive");
		httpGet.setHeader("Host", "localhost");
		httpGet.setHeader("Origin", "http://localhost");
		httpGet.setHeader("Referer", "http://localhost/index.t");
		httpGet.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.117 Safari/537.36");
		return httpGet;
	}
	private static HttpPost initHttpPost(String url){
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept","*/*");
		httpPost.setHeader("Accept-Encoding","gzip, deflate, br");
		httpPost.setHeader("Accept-Language","zh-CN,zh;q=0.9");
		httpPost.setHeader("Connection","keep-alive");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpPost.setHeader("Host", "localhost");
		httpPost.setHeader("Origin", "http://localhost");
		httpPost.setHeader("Referer", "http://localhost/login.t");
		httpPost.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.117 Safari/537.36");
		return httpPost;
	}
}
