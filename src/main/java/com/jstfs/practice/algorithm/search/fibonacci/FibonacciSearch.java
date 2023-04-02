package com.jstfs.practice.algorithm.search.fibonacci;

import java.util.Arrays;

import com.jstfs.common.utils.MyRandomUtils;
import com.jstfs.practice.algorithm.sort.quick.QuickSort;

/**
 * 斐波纳挈查找:
 * 		数据必须是有序的
 * 		数据结构是顺序表结构,如果是链表结构的话,不适合随机存取
 * 
 * @createBy	落叶
 * @createTime	2022年12月17日 下午12:17:30
 */
public class FibonacciSearch {
	private static QuickSort qs = new QuickSort();
	private static int size = 20;
	private static int searchValue = 5;
	
	public static void main(String[] args) {
		MyRandomUtils.setSeed(System.currentTimeMillis());
		int[] ary = MyRandomUtils.generateIntAry(size, 1, 4 * size);
		
		qs.sort(ary, 0, ary.length - 1);
		System.out.println("有序数组:\t" + Arrays.toString(ary));
		int index = search(ary, searchValue);
		System.out.println("随机一个等于" + searchValue + "的元素的下标:[" + index + "]");
	}
	
	public static int search(int[] ary, int value) {
		int low = 0;
		int high = ary.length - 1;
		int k = 0;
		int mid = 0;
		int fib[] = getFib(ary.length);
		
		while(high > fib[k] - 1) {
			k++;
		}
		
		int[] temp = Arrays.copyOf(ary, fib[k]);
		
		for(int i = high + 1; i < temp.length; i++) {
			temp[i] = temp[high];
		}
		
		while(low <= high) {
			mid = low + fib[k - 1] - 1;
			if(value < temp[mid]) {
				high = mid - 1;
				k--;
			} else if(value > temp[mid]) {
				low = mid + 1;
				k = k - 2;
			} else {
				if(mid <= high) {
					return mid;
				} else {
					return high;
				}
			}
		}
		
		return -1;
	}
	
	private static int[] getFib(int size) {
		int[] fib = new int[size];
		fib[0] = 1;
		fib[1] = 1;
		for(int i = 2; i < size; i++) {
			fib[i] = fib[i-1] + fib[i-2];
		}
		
		return fib;
	}
}
