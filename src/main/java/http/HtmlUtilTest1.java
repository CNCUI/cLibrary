package http;

import java.net.URL;
import java.util.Set;

import org.apache.http.cookie.Cookie;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class HtmlUtilTest1 {
	public static String getNextUrl(String key) {
		String page = new String();
		try {
			WebClient webClient = new WebClient();
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setJavaScriptEnabled(false);
			// 去拿网页
//			webClient.addCookie(arg0, arg1, arg2);
//			URL url = new URL("http://localhost/bvcacenter/login.t");
//			url.openConnection();
//			Set<com.gargoylesoftware.htmlunit.util.Cookie> cookie = webClient.getCookies(url);
//			System.out.println(cookie);
			
			HtmlPage htmlPage = webClient.getPage("http://localhost/bvcacenter/login.t");
			// 得到表单
			HtmlForm form = (HtmlForm) htmlPage.getElementById("form1");
			// 得到提交按钮
			HtmlButton button = null;
			DomNodeList<HtmlElement> domElements = form.getElementsByTagName("button");
			for (DomElement temp : domElements) {
				if (temp.getAttribute("class").equals("btn blue pull-right")) {
					button = (HtmlButton) temp;
				}
			}
			// 得到输入框
			HtmlTextInput textField = form.getInputByName("username");
			HtmlPasswordInput textField2 = form.getInputByName("password");
			HtmlTextInput textField3 = form.getInputByName("code");
			// 输入内容
			textField.setValueAttribute("admin");
			textField2.setValueAttribute("11111");
			textField3.setValueAttribute("qthe");
			
			// 点一下按钮
			HtmlPage nextPage = button.click();
			String str = nextPage.toString();
			// page = cutString(str);
			page = str;
			webClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	public static void main(String[] args) {
		String str = getNextUrl("sou");
		System.out.println(str);
	}
}
