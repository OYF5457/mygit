package 排序;


import org.junit.Assert;
import org.junit.Test;

public class QuickSort {
	
	public static void quickSort(int[] list) {
		quickSort(list, 0, list.length - 1);
	}
	
	public static void quickSort(int[] list, int first, int last) {
		if (first >= last) {
			return;
		}
		int low = first;
		int high = last;
		int key = list[low];
		while(low < high) {
			while(low < high && list[high] >= key) {
				high--;
			}
			list[low] = list[high];
			while(low < high && list[low] <= key) {
				low++;
			}
			list[high] = list[low];
		}
		list[low] = key;
		quickSort(list, first, low - 1);
		quickSort(list, low + 1, last);
	}
	
	@Test
	public void test() {
		int[] list = {2,3,2,5,6,1,-2,3,14,12};
		QuickSort.quickSort(list);
		Assert.assertArrayEquals(new int[]{-2,1,2,2,3,3,5,6,12,14}, list);
	}

}
