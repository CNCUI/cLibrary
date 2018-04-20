package basicTypeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 当前日期是星期几
 * @author Cao
 *
 */
public class DateWeekTest {

	public static void main(String[] args) throws ParseException {
		Date date = new Date();
		SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
		System.out.println(dateFm.format(date));
		
		SimpleDateFormat d2 = new SimpleDateFormat("yyyy-MM-dd");
		date = d2.parse("2018-03-07");
		Calendar c = Calendar.getInstance();
		String[] week = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
		c.setTime(date);
		int w = c.get(Calendar.DAY_OF_WEEK)-1;
		System.out.println(week[w]);
	}

}
