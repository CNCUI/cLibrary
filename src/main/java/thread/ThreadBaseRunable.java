package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadBaseRunable implements Runnable{
	private Lock lock  = new ReentrantLock();//手动锁的方式
	volatile int index = 10000;
	public ThreadBaseRunable(){
		System.out.println("ThreadBaseRunable run");
	}
	@Override
	public void run() {
		try {
//			lock.lock();
			print(index);
		} catch (Exception e){
			e.printStackTrace();
		}finally{
//			lock.unlock();  //在finally释放锁，避免发生异常出现死锁
		}
	}
	public synchronized void print(int j){
		while(index > 0){
			System.out.println(index);   //syso和index--有线程安全问题,可改写syso(index--),index--是原子性
			index--;
		}
//		for(int i=0;i<j;i++){
//			System.out.println(Thread.currentThread().getName());
//		}
	}
	public static void main(String[] args) {
		ThreadBaseRunable z = new ThreadBaseRunable();
		Thread t1 = new Thread(z);t1.setName("Thread1111111111111111111111");
		Thread t2 = new Thread(z);t2.setName("Thread2");
		Thread t3 = new Thread(z);t2.setName("Thread3333333");
		Thread t4 = new Thread(z);t2.setName("Thread444444");
		Thread t5 = new Thread(z);t2.setName("Thread55555555555");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}
	
}
