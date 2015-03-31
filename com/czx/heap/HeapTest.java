package com.czx.heap;

import org.junit.Assert;
import org.junit.Test;

public class HeapTest {
	@Test
	public void test() {
		Integer[] list = {2,3,2,5,6,1,-2,3,14,12};
		Heap.heapSort(list);
		Assert.assertArrayEquals(new Integer[]{-2,1,2,2,3,3,5,6,12,14}, list);
	}
}