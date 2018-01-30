package algorithm;
/**
 * 快速排序由C. A. R. Hoare在1962年提出。它的基本思想是：通过一趟排序将要排序的数据分割成独立的两部分，
 * 其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
 * @author Cao
 *
 */
public class QuickSort {

	public static void main(String[] args) {
		int[] arr = new int[]{4,3,1,7,5,6};
		sort(arr,0,arr.length-1);
		for(int i=0;i<arr.length;i++){
			System.out.println(arr[i]);
		}
	}
	public static void sort(int[] arr,int left,int right){
		if(left < right){
			int i = left;
			int j = right;
			int index = arr[left];//基准，挖坑i
			while(i<j){
				while(i<j && arr[j] >= index){
					j--;
				}
				while(i<j && arr[i] <= index){
					i++;
				}
				if(i<j){
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
			arr[left]=arr[i];   
			arr[i]=index;//填坑i
			
			sort(arr,left,i-1);//继续处理左边的，这里是一个递归的过程   
			sort(arr,i+1,right);
		}
	}
	
}
