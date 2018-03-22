package thread;

public class TickeThreadDemo extends Thread{
	private ThreadLocal<Integer> local = new ThreadLocal<Integer>();
 	private static int t = 20;
	private static Object obj = new Object();
	@Override
	public void run(){
		for(int i =0;i<10;i++){
			synchronized(obj){
				t--;
				System.out.println(Thread.currentThread().getName()+"--"+t);
				Integer c = local.get();
				if(c == null){
					c = new Integer(0);
					local.set(c);
				}
				c = c+1;
				System.out.println(c);
			}
		}
	}
	public static void main(String[] args) {
		TickeThreadDemo d = new TickeThreadDemo();
		d.setName("Thread");
		d.start();
	}

}
