package thread;

/**
 * 有可能输出 	main thread b=1000
 * 			b=1000
 * 
 * 或者		main thread b=2000        16行加断点，条件 !("main".equals(Thread.currentThread().getName()))
 * 			b=1000
 * 	
 * @author Cao
 *
 */
public class TestSync1 implements Runnable{
	   int b = 100;          

	   synchronized void m1() throws InterruptedException {
	       b = 1000;
	       Thread.sleep(500); //6
	       System.out.println("b=" + b);
	   }

	   synchronized void m2() throws InterruptedException {
	       Thread.sleep(250); //5
	       b = 2000;
	   }

	   public static void main(String[] args) throws InterruptedException {
		   TestSync1 tt = new TestSync1();
	       Thread t = new Thread(tt);  //1
	       t.start(); //2

	       tt.m2(); //3
	       System.out.println("main thread b=" + tt.b); //4
	   }

	   @Override
	   public void run() {
	       try {
	           m1();
	       } catch (InterruptedException e) {
	           e.printStackTrace();
	       }
	   }

}
