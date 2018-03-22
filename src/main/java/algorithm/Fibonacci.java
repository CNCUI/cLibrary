package algorithm;
/**
 * 0 1 1 2 3 5 8 13 21
 * @author Cao
 *
 */
public class Fibonacci {
	public static void main(String[] args) {
		System.out.println(fb(6));
		System.out.println(sum(6));
	}
	/**
	 * 第几个数的大小
	 * @param c
	 * @return
	 */
	public static int fb(int c){
		if(c == 0){
			return 0;
		}
		if(c == 1){
			return 1;
		}
		return fb(c-1)+fb(c-2);
	}
	/**
	 * 求和
	 * @param c
	 * @return
	 */
	public static int sum(int c){
		int ccc = 0;
		for(int i=c;i>0;i--){
			ccc += fb(i);
		}
		return ccc;
	}
}
