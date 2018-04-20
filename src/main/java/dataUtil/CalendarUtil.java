package dataUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {
	public static void main(String[] args) {
		Calendar now = Calendar.getInstance();  
		//年度
		String year = now.get(Calendar.YEAR) + "";
		//月度
		String month = (now.get(Calendar.MONTH) + 1) + "";
		month = month.length() == 1 ? "0"+month : month;
		//日
		String day = now.get(Calendar.DATE) + "";
		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		
        Date date=new Date();  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.DAY_OF_MONTH, -1);  
        date = calendar.getTime();  
        String nowday = sd.format(date);
		
		System.out.println(nowday);
//		System.out.println(year + "-" + month + "-" + day);
	}
}
