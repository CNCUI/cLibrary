package thread.threadLoacalTest;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalTest {

	public static class MyRunnable implements Runnable {
		public MyRunnable() {
			super();
			System.out.println("Construct");
		}
		private ThreadLocal threadLocal = new ThreadLocal();//能分别存储线程的值
		private Map map = new HashMap();
		@Override
		public void run() {
			threadLocal.set((int) (Math.random() * 100D));
			map.put("str", Math.random() * 100D);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {

			}
			System.out.println(threadLocal.get());
			System.out.println(map.get("str"));
		}
	}

	public static void main(String[] args) {
		MyRunnable sharedRunnableInstance = new MyRunnable();//同一个对象
		Thread thread1 = new Thread(sharedRunnableInstance);
		Thread thread2 = new Thread(sharedRunnableInstance);
		thread1.start();
		thread2.start();
	}

}
