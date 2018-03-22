package thread.HttpThread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HttpThread1 {
	
	public static void main(String[] args) {

		ExecutorService executorService = Executors.newFixedThreadPool(30);
		List<Future<String>> resultList = new ArrayList<Future<String>>();  
		
		for (int index = 0; index < 1000; index++) {

			Future<String> future = executorService.submit(new TaskWithResult(index));
            // 将任务执行结果存储到List中  
            resultList.add(future);  
		}
		// 退出线程池
		executorService.shutdown();
		
	}

}
class TaskWithResult implements Callable<String> {  
	public static  int ii = 0;
    private int id;  

    public TaskWithResult(int id) {  
        this.id = id;  
    }  

    /** 
     * 任务的具体过程，一旦任务传给ExecutorService的submit方法，则该方法自动在一个线程上执行。 
     *  
     * @return 
     * @throws Exception 
     */  
    public String call() throws Exception {
        try {
			long time1 = System.currentTimeMillis();
			URL url = new URL("http://localhost/bm/bmBmb_isYsOpen.json?_=1520996464313");
			InputStreamReader isr = new InputStreamReader(url.openStream());
			long time2 = System.currentTimeMillis();
//			System.out.print("Thread " + Thread.currentThread().getName() + " time:" + (time2 - time1) + "ms");
			BufferedReader br = new BufferedReader(isr);
			String str;
			while ((str = br.readLine()) != null) {
//				System.out.println(str);
			}
			System.out.println(ii++);
			br.close();
			isr.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
        return "";  
    }  
}  

class TaskException extends Exception {  
    public TaskException(String message) {  
        super(message);  
    }  
}  