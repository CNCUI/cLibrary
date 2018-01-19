package basicTypeUtil;

import java.text.NumberFormat;
public class MyNumberFormat {
	
	public static void numberFormat(){
		NumberFormat num = NumberFormat.getPercentInstance(); //返回当前默认语言环境的百分比格式。
		num.setMaximumIntegerDigits(2); //设置数的整数部分所允许的最大位数
		num.setMaximumFractionDigits(2); //设置数的小数部分所允许的最大位数
		double number = 0.1703;
		System.out.println(num.format(number));//17.03%
		//当然NumberFormat 还提供了一些其他的方法,可以控制我们输出的百分比，
		//setMinimumFractionDigits(int newValue)//设置数的小数部分所允许的最小位数
		//setMinimumIntegerDigits(int newValue)//设置数的整数部分所允许的最小位数
	}
	public static void acc0(){
		String s = "111.01100";
		if(s.indexOf(".") > 0){
			//正则表达
			s = s.replaceAll("0+?$", "");//去掉后面无用的零
			s = s.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
	     }
		System.out.println(s);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		numberFormat();
		acc0();
	}

}
