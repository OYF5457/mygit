package com.czx.heap;

import java.util.ArrayList;
import java.util.List;


public class Heap<E extends Comparable<E>> {
	private List<E> list = new ArrayList<E>();
	public Heap() {
	}
	
	public Heap(E[] objects) {
		for (int i = 0; i < objects.length; i++) {
			add(objects[i]);
		}
	}

	public void add(E newElement) {
		list.add(newElement);
		int currentIndex = list.size() - 1;
		
		while(currentIndex > 0) {
			int parentIndex = (currentIndex - 1) / 2;
			if(list.get(currentIndex).compareTo(list.get(parentIndex)) > 0) {
				E temp = list.get(currentIndex);
				list.set(currentIndex, list.get(parentIndex));
				list.set(parentIndex, temp);
			} else {
				break;
			}
			currentIndex = parentIndex;
		}
	}
	
	public E remove() {
		if (list.size() < 1) {
			return null;
		}
		E element = list.get(0);
		list.set(0, list.get(list.size() - 1));
		list.remove(list.size() - 1);
		
		int currentIndex = 0;
		while(currentIndex < list.size()) {
			int leftChildIndex = 2 * currentIndex + 1;
			int rightChildIndex = 2 * currentIndex + 2;
			if (leftChildIndex >= list.size()) {
				break;
			}
			int maxChildIndex = leftChildIndex;
			if (rightChildIndex < list.size()) {
				if (list.get(maxChildIndex).compareTo(list.get(rightChildIndex)) < 0) {
					maxChildIndex = rightChildIndex;
				}
			}
			
			if (list.get(currentIndex).compareTo(list.get(maxChildIndex)) < 0) {
				E temp = list.get(maxChildIndex);
				list.set(maxChildIndex, list.get(currentIndex));
				list.set(currentIndex, temp);
				currentIndex = maxChildIndex;
			} else {
				break;
			}
		}
		
		return element;
	}
	
	public int getSize() {
		return list.size();
	}
	
	public static <E extends Comparable<E>>void heapSort(E[] list) {
		Heap<E> heap = new Heap<E>();
		for (int i = 0; i < list.length; i++) {
			heap.add(list[i]);
		}
		
		for (int i = list.length - 1; i >= 0; i--) {
			list[i] = heap.remove();
		}
	}
	
}
