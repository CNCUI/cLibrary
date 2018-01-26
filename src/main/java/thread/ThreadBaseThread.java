package thread;

public class ThreadBaseThread extends Thread{
	@Override
	public void run() {
		print();
	}
	public synchronized void print(){
		for(int i=0;i<1000;i++){
			System.out.println(Thread.currentThread().getName());
		}
	}
	public static void main(String[] args) {
		ThreadBaseRunable z = new ThreadBaseRunable();
		Thread t1 = new Thread(z);t1.setName("Thread1111111111111111111111");
		Thread t2 = new Thread(z);t2.setName("Thread2");
		t1.start();
		t2.start();
	}
}
