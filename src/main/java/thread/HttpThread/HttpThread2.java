package thread.HttpThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class HttpThread2 {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(2, new NamedThreadFactory());
		postUtl postUtl1 = new postUtl();
		postUtl postUtl2 = new postUtl();
		executorService.execute(postUtl1);
		executorService.execute(postUtl2);
		executorService.shutdown();
		
	}
}

class postUtl implements Runnable{
	private static AtomicInteger tag = new AtomicInteger(0);
	@Override
	public void run() {
		URL url;
		try {
			url = new URL("http://localhost/we/ys_test.t");
			InputStreamReader isr = new InputStreamReader(url.openStream());
			BufferedReader br = new BufferedReader(isr);
			String str;
			while ((str = br.readLine()) != null) {
//				System.out.println(str);
			}
			br.close();
			isr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if("pool52".equals(Thread.currentThread().getName())){
			System.out.println("指定线程");
		}
		System.out.println(Thread.currentThread().getName());
	}
	
}

/**
 * 给线程设置名称
 * @author Cao
 */
class NamedThreadFactory implements ThreadFactory{
    private static AtomicInteger tag = new AtomicInteger(52); 
//    private  volatile int aa = 68;
    @Override  
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);  
        thread.setName("pool"+ tag.getAndIncrement());  
//        thread.setName("pool"+ ++aa);  
        return thread;  
    }
    
} 