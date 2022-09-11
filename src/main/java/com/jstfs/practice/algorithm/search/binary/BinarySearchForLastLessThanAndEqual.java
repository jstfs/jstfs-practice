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
	private static int size = 50;
	private static int searchValue = 5;
	
	public static void main(String[] args) {
		MyRandomUtils.setSeed(System.currentTimeMillis());
		int[] ary = MyRandomUtils.generateIntAry(size, 1, size);
		qs.sort(ary, 0, ary.length - 1);
		System.out.println("有序数组:\t" + Arrays.toString(ary));
		int index = search(ary, 0, ary.length - 1);
		System.out.println("最后一个小于等于" + searchValue + "的元素的下标:[" + index + "]");
	}
	
	public static int search(int[] ary, int min, int max) {
		if(min > max) {
			return -1;
		}
		
		int middle = (max + min) >> 1;
		if(ary[middle] <= searchValue) {
			if(middle == (ary.length - 1) || ary[middle + 1] > searchValue) {
				return middle;
			}
			return search(ary, middle, max);
		} else {
			if(ary[middle - 1] <= searchValue) {
				return middle - 1;
			}
			return search(ary, min, middle - 1);
		}
	}
}
