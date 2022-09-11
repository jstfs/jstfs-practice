package com.jstfs.practice.algorithm.search.binary;

import java.util.Arrays;

import com.jstfs.common.utils.MyRandomUtils;
import com.jstfs.practice.algorithm.sort.quick.QuickSort;

/**
 * 二分查找:
 * 		数据必须是有序的
 * 		并且数据结构是顺序表结构,简单说就是数组,因为需要根据下标随机访问,如果是链表结构的话,时间复杂度会高很多
 * 
 * 特点:
 *		时间复杂度: O(log₂N)
 * 
 * @createBy 	落叶
 * @createTime 	2019-1-19 上午11:33:26
 */
public class BinarySearch {
	private static QuickSort qs = new QuickSort();
	private static int size = 50;
	private static int searchValue = 30;
	
	public static void main(String[] args) {
		MyRandomUtils.setSeed(System.currentTimeMillis());
		int[] ary = MyRandomUtils.generateIntAry(size, 1, 4 * size);
		qs.sort(ary, 0, ary.length - 1);
		System.out.println("有序数组:\t" + Arrays.toString(ary));
		int index = search(ary, 0, ary.length - 1);
		System.out.println("随机一个等于" + searchValue + "的元素的下标:[" + index + "]");
	}
	
	public static int search(int[] ary, int min, int max) {
		if(min > max) {
			return -1;
		}
		
		int middle = (max + min) >> 1;
		if(ary[middle] > searchValue) {
			return search(ary, min, middle - 1);
		} else if(ary[middle] < searchValue) {
			return search(ary, middle + 1, max);
		} else {
			return middle;
		}
	}
}
