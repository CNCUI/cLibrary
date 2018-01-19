package check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验数字
 * @author Cao
 *
 */
public class IsNumeric {
	public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
	}
	
	public static boolean isNumeric2(String str)
	{
		for(int i = 0; i < str.length(); i++){
			if (!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}
	public static boolean isNumeric00(String str)
	{
	  try{
		   Integer.parseInt(str);
		   return true;
	  }catch(NumberFormatException e)
	  {
		 System.out.println("异常：\"" + str + "\"不是数字/整数...");
		 return false;
	  }
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isNumeric2("12356"));
		System.out.println(isNumeric00("123456789"));
	}

}
