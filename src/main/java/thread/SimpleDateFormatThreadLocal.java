package thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatThreadLocal implements Runnable{
	
	private static ThreadLocal<SimpleDateFormat> local = new ThreadLocal<SimpleDateFormat>();
	
	public static SimpleDateFormat getSimpleDateFormat(){
		SimpleDateFormat sd = local.get();
		if(sd == null){
			sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			local.set(sd);
		}
		return sd;
	}
	
	public static Date parse(String str) throws ParseException{
		return getSimpleDateFormat().parse(str);
	}
	
	public static String format(Date date) throws ParseException{
		return getSimpleDateFormat().format(date);
	}
	
	public static void main(String[] args) {
		SimpleDateFormatThreadLocal st = new SimpleDateFormatThreadLocal();
		for(int i=0;i<20;i++){
			Thread t1 = new Thread(st);
			t1.setName("Thread"+i);
			t1.start();
		}
	}

	@Override
	public void run() {
		try {
			Date date = new Date();
			if("Thread8".equals(Thread.currentThread().getName())){
				Thread.sleep(2000);
				date = new Date();
			}
			if("Thread6".equals(Thread.currentThread().getName())){
				Thread.sleep(1000);
				date = new Date();
			}
			String c1 = format(date);
			Date c2 = parse(c1);
			System.out.println(Thread.currentThread().getName()+"_c1__"+c1);
			System.out.println(Thread.currentThread().getName()+"_c2__"+c2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
