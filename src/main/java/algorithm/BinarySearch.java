package algorithm;

public class BinarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] c = {1,6,8,9,11,44,66,88};
		int cc = fdg(c,8);
		System.out.println(cc);
		
		int[] efcr = efcr(c,33);
		for(int i=0;i<efcr.length;i++){
			System.out.print(efcr[i]+ " ");
		}
		
	}
	
	/**
	 * 二分查找
	 * @param arr
	 * @param x
	 * @return
	 */
	public static int fdg(int[] arr,int x){
		int low = 0;
		int hight = arr.length-1;
		while(low <= hight){
			int mid = (low + hight)/2;	//找中间点
			if(x == arr[mid]){
				return mid;
			}else if(x < arr[mid]){	//比中间的数小，从左到中间点-1
				hight = mid -1;
			}else if(x > arr[mid]){	//比中间数大，查中间点到右
				low = mid +1;
			}
		}
		return -1;
	}

	/**
	 * 二分插入
	 * @param arr
	 * @param x
	 * @return
	 */
	public static int[] efcr(int[] arr,int x){
		int[] co = new int[arr.length+1];	//插入后数组
		int low = 0;
		int hight = arr.length-1;
		int cr = -1;
		while(low <= hight){
			int mid = (low + hight)/2;
			if(x <= arr[mid]){ //小于等于靠右的临界值
				hight = mid -1;
				cr = mid;	//确定插入位置
			}else if(x > arr[mid]){
				low = mid +1;
			}
		}
		for(int i=0;i<arr.length;i++){
			if(i == cr ){
				co[i] = x;
				co[i+1] = arr[i];
			}else if(i > cr && cr != -1){
				co[i+1] = arr[i];
			}else{
				co[i] = arr[i];
			}
			if(i == arr.length -1 && cr == -1){
				co[i+1] = x;
			}
		}
		return co;
	}
}
