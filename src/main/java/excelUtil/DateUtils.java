package excelUtil;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public final class DateUtils
{
  public static final String DATEFORMAT = "yyyy-MM-dd";

  public static final String DATETIMEFORMAT = "yyyy-MM-dd HH:mm:ss";

  public static Date getNowDate()
  {
    Calendar c = Calendar.getInstance();
    return c.getTime();
  }

  public static String getDateString(Date date)
  {
    return formatDate(date, DATEFORMAT);
  }

  public static String getDateTimeString(Date date)
  {
    return formatDate(date, DATETIMEFORMAT);
  }

  public static String formatDate(Date date, String format)
  {
    if (date == null) {
      throw new NullPointerException();
    }
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    return sdf.format(date);
  }

  public static String timestampToString(Timestamp timestamp)
  {
    if (timestamp == null) {
      return "";
    }
    return formatDate(timestamp, DATETIMEFORMAT);
  }

  public static Date getDate(long datetime)
  {
    Calendar c = Calendar.getInstance();
    c.setTimeInMillis(datetime);
    return c.getTime();
  }

  public static Date getDate(String dateStr, String format)
    throws ParseException
  {
    SimpleDateFormat parse = new SimpleDateFormat(format);
    return parse.parse(dateStr);
  }

  public static long getDays(String date1, String date2)
    throws ParseException
  {
    if ((date1 == null) || (date1.equals(""))) {
      return 0L;
    }

    if ((date2 == null) || (date2.equals(""))) {
      return 0L;
    }
    Date date = null;
    Date mydate = null;
    date = getDate(date1, DATEFORMAT);
    mydate = getDate(date2, DATEFORMAT);
    return getDays(date, mydate);
  }

  public static long getDays(Date date1, Date date2)
  {
    if (date1.compareTo(date2) >= 0) {
      return (date1.getTime() - date2.getTime()) / 86400000L;
    }
    return (date2.getTime() - date1.getTime()) / 86400000L;
  }

  public static Date getFirstDayOfMonth()
  {
    Calendar lastDate = Calendar.getInstance();

    lastDate.set(5, 1);
    return lastDate.getTime();
  }

  public static Date add(Date date, int days)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(5, days);
    return c.getTime();
  }

  public static Date addMinute(Date date, int minute)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(12, minute);
    return c.getTime();
  }

  public static boolean grEq(Date date1, Date date2)
  {
    int b = date1.compareTo(date2);
    return b >= 0;
  }

  public static boolean gr(Date date1, Date date2)
  {
    int b = date1.compareTo(date2);
    return b > 0;
  }

  public static List<String> getListBetweenDate(String minDate, String maxDate)
    throws ParseException
  {
    Calendar startCalendar = Calendar.getInstance();
    Calendar endCalendar = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Date startDate = df.parse(minDate);
    startCalendar.setTime(startDate);
    Date endDate = df.parse(maxDate);
    endCalendar.setTime(endDate);
    List dateList = new ArrayList();
    dateList.add(df.format(startCalendar.getTime()));
    while (true) {
      startCalendar.add(5, 1);
      if (startCalendar.getTimeInMillis() >= endCalendar.getTimeInMillis()) break;
      String date = df.format(startCalendar.getTime());
      dateList.add(date);
    }

    dateList.add(df.format(endCalendar.getTime()));
    return dateList;
  }

  public static List<String> getMonthDateList(String month)
    throws ParseException
  {
    List dateList = new ArrayList();
    Calendar cal = Calendar.getInstance();
    cal.set(2, Integer.parseInt(month) - 1);
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    int sum = cal.getActualMaximum(5);
    for (int i = 1; i <= sum; i++) {
      cal.set(5, i);
      String date = df.format(cal.getTime());
      dateList.add(date);
    }
    return dateList;
  }
}

