package thread.BlockingQueue;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueTest {

	public static void main(String[] args) throws InterruptedException {
//		add();
		put();
		offer();
	}

	public static void add(){
		LinkedBlockingQueue lk = new LinkedBlockingQueue(2);
		lk.add("1");
		lk.add("2");
		lk.add("3");//队列满了抛出异常
		System.out.println(lk);
	}
	public static void put() throws InterruptedException{
		LinkedBlockingQueue lk = new LinkedBlockingQueue(2);
		lk.put("1");
		lk.put("2");
		lk.put("3");//队列满了阻塞一段时间
		System.out.println(lk);
	}
	public static void offer() throws InterruptedException{
		LinkedBlockingQueue lk = new LinkedBlockingQueue(2);
		lk.offer("1");
		lk.offer("2");
		boolean c = lk.offer("3");//队列满了返回false
		System.out.println(c);
		System.out.println(lk);
	}
}
