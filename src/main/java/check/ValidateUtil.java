package check;

import java.util.regex.Pattern;
/**
 * 手机、邮箱等各种格式校验  工具类
 * 
 */
public class ValidateUtil {
	/**
	 * 校验手机号码
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		if (CommonUtil.isEmpty(mobile)) {
			return false;
		}
		String regEx = "[1]{1}[3,4,5,7,8]{1}[0-9]{9}";
		return Pattern.compile(regEx).matcher(mobile).matches();
	}

	/**
	 * 判断是否为正确的一级单位编码格式
	 * 4位小写字母与数字组合
	 * @param str
	 * @return
	 */
	public static boolean isUnitId(String str) {
		Pattern pattern = Pattern.compile("^[a-z0-9]{4}$");
		return pattern.matcher(str).matches();
	}
	
	/**
	 * 判断是否为6位数字
	 * 判断二级单位编码的时候用
	 * 6位数字
	 * @param str
	 * @return
	 */
	public static boolean isSubUnitId(String str) {
		Pattern pattern = Pattern.compile("^[0-9]{6}$");
		return pattern.matcher(str).matches();
	}
	
	/**
	 * 固定电话  只能包含 '-' 数字
	 * @param str
	 * @return
	 */
	public static boolean isTel(String str) {
		Pattern pattern = Pattern.compile("\\d+(\\-\\d+)?");
		return pattern.matcher(str).matches();
	}
	
	/**
	 * 纯数字
	 * @param str
	 * @return
	 */
	public static boolean isInt(String str) {
		Pattern pattern = Pattern.compile("^\\d+$");
		return pattern.matcher(str).matches();
	}
	
	/**
	 * 邮政编码
	 * @param str
	 * @return
	 */
	public static boolean isPost(String str) {
		Pattern pattern = Pattern.compile("^[1-9]\\d{5}$");
		return pattern.matcher(str).matches();
	}
	
	/**
	 * 邮箱
	 * @param str
	 * @return
	 */
	public static boolean isEmail(String str) {
		Pattern pattern = Pattern.compile("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
		return pattern.matcher(str).matches();
	}
	/**
	 * 用户名
	 * 7到30 字母或者数字
	 * @param str
	 * @return
	 */
	public static boolean isUserName(String str) {
		Pattern pattern = Pattern.compile("^[0-9a-zA-Z]{7,30}$");
		return pattern.matcher(str).matches();
	}
	public static void main(String[] args) {
		System.out.println("sdf".toUpperCase());
	}
	/**
	 * string 长度 校验   
	 * 非空时   使用该校验
	 * @param str
	 * @param i
	 * @return
	 */
	public static boolean strLenLimit(String str, int i){
		if(CommonUtil.isEmpty(str)){
			return false;
		}
		int s = str.length();
		if(s <= i){
			return true;
		}
		return false;
	}
}
