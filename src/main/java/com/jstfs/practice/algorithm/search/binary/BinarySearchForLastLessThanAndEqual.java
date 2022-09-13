package com.jstfs.practice.algorithm.search.binary;

import java.util.Arrays;

import com.jstfs.common.utils.MyRandomUtils;
import com.jstfs.practice.algorithm.sort.quick.QuickSort;

/**
 * 二分查找变体五: 查找最后一个小于等于给定值的元素
 * 
 * @createBy 	落叶
 * @createTime 	2019-1-20 上午11:21:19
 */
public class BinarySearchForLastLessThanAndEqual {
	private static QuickSort qs = new QuickSort();
	private static int size = 20;
	private static int searchValue = 2;
	
	public static void main(String[] args) {
		MyRandomUtils.setSeed(System.currentTimeMillis());
		int[] ary = MyRandomUtils.generateIntAry(size, 1, size);
		qs.sort(ary, 0, ary.length - 1);
		System.out.println("有序数组:\t" + Arrays.toString(ary));
		int index = search(ary, 0, ary.length - 1);
		System.out.println("最后一个小于等于" + searchValue + "的元素的下标:[" + index + "]");
	}
	
	public static int search(int[] ary, int min, int max) {
		if(searchValue < ary[min] ) {
			if(min == 0) {
				return -1;
			}
			return min - 1;
		}
		if(searchValue >= ary[max]) {
			return max;
		}
		
		int middle = (max + min) >> 1;
		if(ary[middle] <= searchValue) {
			return search(ary, middle + 1, max);
		} else {
			return search(ary, min, middle - 1);
		}
	}
}
