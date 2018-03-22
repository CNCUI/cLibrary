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
		Thread m = Thread.currentThread();
		m.setName("t"+tag.incrementAndGet());
		System.out.println(m.getName());
		URL url;
		try {
			url = new URL("http://localhost/we/login_test.t");
			InputStreamReader isr = new InputStreamReader(url.openStream());
			BufferedReader br = new BufferedReader(isr);
			String str;
			while ((str = br.readLine()) != null) {
				System.out.println(str);
			}
			br.close();
			isr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}

class NamedThreadFactory implements ThreadFactory{
	  
    private static AtomicInteger tag = new AtomicInteger(0);  
    @Override  
    public Thread newThread(Runnable r) {  
        Thread thread = new Thread(r);  
        thread.setName("pool"+ tag.getAndIncrement());  
        return thread;  
    }  
      
} 