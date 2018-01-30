package algorithm;

public class BubbleSort {

	public static void main(String[] args) {
		int[] arr = new int[]{7,1,3,2,5,4,6};
		sort(arr);
		for(int i=0;i<arr.length;i++){
			System.out.println(arr[i]);
		}
	}
	//冒泡排序   n*n
	public static void sort(int arr[]){
		int temp;
		for(int i=0;i<arr.length;i++){
			for(int j=i;j<arr.length;j++){
				if(arr[i] >= arr[j]){
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}

}
