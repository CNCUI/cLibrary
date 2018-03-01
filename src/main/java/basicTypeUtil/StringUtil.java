package basicTypeUtil;

import java.util.HashMap;
import java.util.Map;

public class StringUtil {

	public static boolean isBlank(Object obj) {
		if(obj == null){
			return true;
		}
		if(obj instanceof String){
			if("".equals(obj)){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	public static boolean isNotBlank(Object obj){
		return !isBlank(obj);
	}

	
	public static void main(String[] args) {
		String str1 = new String();
		System.out.println(isNotBlank(str1));
		
		String str2 = "test";
		System.out.println(isNotBlank(str2));
		
		Map m = new HashMap();
		m.put("cc", "cc");
		System.out.println(isBlank(m.get("cc")));
	}
	
//	public static void main(String[] args) {
//		String a = "a" + "b";
//		String b = a;
//		String b2 = "a" + new String("b");
//		String c = new String(a);
//		System.out.println(a==c);//false
//		System.out.println(a.equals(c));//true
//		
//		System.out.println(a==b);//true
//		System.out.println(a==b2);//false
//		
//	}
}
