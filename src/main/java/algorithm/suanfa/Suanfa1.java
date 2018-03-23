package algorithm.suanfa;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Suanfa1 {
	/**
	 * 小易准备去魔法王国采购魔法神器,购买魔法神器需要使用魔法币,但是小易现在一枚魔法币都没有,但是小易有两台魔法机器可以通过投入x(x可以为0)个魔法币产生更多的魔法币。
		魔法机器1:如果投入x个魔法币,魔法机器会将其变为2x+1个魔法币
		魔法机器2:如果投入x个魔法币,魔法机器会将其变为2x+2个魔法币
		小易采购魔法神器总共需要n个魔法币,所以小易只能通过两台魔法机器产生恰好n个魔法币,小易需要你帮他设计一个投入方案使他最后恰好拥有n个魔法币。
	 */
	/**
	 * 分析：分别可以产生奇数和偶数
	 */
	public static void t1(){
		Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int coinCount = in.nextInt();
            StringBuilder sb = new StringBuilder();
            while (coinCount > 0) {
                if (coinCount % 2 == 0) {
                    //偶数
                    coinCount = (coinCount - 2) / 2;
                    sb.append("2");
                } else {
                    //奇数
                    coinCount = (coinCount - 1) / 2;
                    sb.append("1");
                }
                System.out.println(coinCount);
            }
            System.out.println(sb.toString());
        }
	}
	/**
	 * 为了得到一个数的"相反数",我们将这个数的数字顺序颠倒,然后再加上原先的数得到"相反数"。例如,为了得到1325的"相反数",首先我们将该数的数字顺序颠倒,我们得到5231,之后再加上原先的数,我们得到5231+1325=6556.如果颠倒之后的数字有前缀零,前缀零将会被忽略。例如n = 100, 颠倒之后是1.
	 */
	public static void t2(){
		Scanner num = new Scanner(System.in);
		int n = num.nextInt(); 
		StringBuffer str = new StringBuffer(String.valueOf(n));
		int n2 = Integer.valueOf(String.valueOf(str.reverse()));//方法1：StringBuffer字符串reverse反转相加
		System.out.println(n+n2);
		
		String str2 = String.valueOf(n);
		char[] ch = str2.toCharArray();
		char[] ch2 = new char[ch.length];//方法1：toCharArray反向复制相加
		for(int i=0;i<ch.length;i++){
			ch2[ch.length-i-1] = ch[i];
		}
		String str3 = new String(ch2);
		int n3 = Integer.valueOf(str3);
		System.out.println(n + n3);
	}
	/**
	 * 一个由小写字母组成的字符串可以看成一些同一字母的最大碎片组成的。例如,"aaabbaaac"是由下面碎片组成的:'aaa','bb','c'。牛牛现在给定一个字符串,请你帮助计算这个字符串的所有碎片的平均长度是多少。
	 * @param args
	 */
	/**
	 * 使用StringBuffer从前往后append,如果后一个字符和前一个字符不相等，则append一个，号，以此分割相同字符子串
	 * 时间复杂度O(n)
	 */
	public static void t3(){
		String str = "aaabbaaac";
		char[] s1 = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<s1.length;i++){
			String t = String.valueOf(s1[i]);
			sb.append(t);
			if(i!=s1.length-1){
				String t2 = String.valueOf(s1[i+1]);
				if(!t.equals(t2)){
					sb.append(",");
				}
			}
		}
		System.out.println("分割碎片："+sb);
		String[] c = sb.toString().split(",");
		Set<String> set = new HashSet<String>();
		for(int i=0;i<c.length;i++){
			set.add(c[i]);
		}
		System.out.println("组成碎片："+set);
		
		int cc = 0;
		for(int i=0;i<c.length;i++){
			cc += c[i].length();
		}
		double db = (double)cc/c.length;
		DecimalFormat df = new DecimalFormat("#.00");
		System.out.println("碎片平均长度："+df.format(db));
	}
	/**
	 * 魔法王国一共有n个城市,编号为0~n-1号,n个城市之间的道路连接起来恰好构成一棵树。
		小易现在在0号城市,每次行动小易会从当前所在的城市走到与其相邻的一个城市,小易最多能行动L次。
		如果小易到达过某个城市就视为小易游历过这个城市了,小易现在要制定好的旅游计划使他能游历最多的城市,请你帮他计算一下他最多能游历过多少个城市(注意0号城市已经游历了,游历过的城市不重复计算)。
	 * @param args
	 */
	
	/**
	 * 小易有一个长度为N的正整数数列A = {A[1], A[2], A[3]..., A[N]}。
		牛博士给小易出了一个难题:
		对数列A进行重新排列,使数列A满足所有的A[i] * A[i + 1](1 ≤ i ≤ N - 1)都是4的倍数。
		小易现在需要判断一个数列是否可以重排之后满足牛博士的要求。
	 * @param args
	 */
	public static void t5(){
		
		
	}
	/**
	 * 链接：https://www.nowcoder.com/questionTerminal/da91be18e18f454583455626114d8707
		编写函数，获取两段字符串的最长公共子串的长度，例如： 
		S1= GCCCTAGCCAGDE 
		S2= GCGCCAGTGDE 
		这两个序列的最长公共子串是GCCAG，也就是说返回值。 
		1）请先描述思路；
		2）编写完整代码实现，编程语言不限。
	 * @param args
	 * 时间复杂度O（n^2）
	 */
	public static void t4(){
		String s1 = "GCCCTAGCCAGDE";
		String s2 = "GCGCCAGTGDE";
		String str = s1.length() > s2.length() ? s2 : s1; //短串
		String str2 = s1.length() > s2.length() ? s1 : s2; //长串
		Set<String> set = new HashSet<>();
		for(int i=0;i<str.length();i++){
			String t = str.substring(i, str.length());//先分割字符串GCGCCAGTGDE，CGCCAGTGDE，GCCAGTGDE。。。
			char[] c = t.toCharArray();
			set.addAll(t4_1(c));//保存每一个分割后的字符串的子集G，GC，GCG。。。
		}
		System.out.println("非重复子串:"+set);
		
		List<String> list = new ArrayList<>();
		for(String s : set){
			if(str2.indexOf(s) != -1){
				list.add(s);
			}
		}
		String str3 = "";
		for(String st : list){
			if(st.length() > str3.length()){
				str3 = st;
			}
		}
		System.out.println("最大子串："+str3);
	}
	//[G,C,G,C,C,A,G,T,G,D,E]
	//字符数组后一个等于前一个加上第i个字符
	public static Set<String> t4_1(char[] c){
		Set<String> set = new HashSet<>();
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<c.length;i++){
			String[] t = String.valueOf(sb).split(","); //2([G])
			if(t.length >= 1){
				sb.append(t[t.length-1]); //2(G,G)
			}
			sb.append(c[i]).append(",");//1(G,) 2(G,GC,)
		}
		System.out.println("子串："+sb);
		String[] t = String.valueOf(sb).split(",");
		for(int i=0;i<t.length;i++){
			set.add(t[i]);
		}
		return set;
	}
	public static void main(String[] args) {
		t1();
//		t2();
//		t3();
//		t4();
    }
}
