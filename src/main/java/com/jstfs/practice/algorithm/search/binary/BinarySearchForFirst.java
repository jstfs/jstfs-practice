package com.jstfs.practice.algorithm.search.binary;

import java.util.Arrays;

import com.jstfs.common.utils.MyRandomUtils;
import com.jstfs.practice.algorithm.sort.quick.QuickSort;

/**
 * 二分查找变体一: 查找第一个给定的元素
 * 
 * @createBy 	落叶
 * @createTime 	2019-1-19 下午11:51:24
 */
public class BinarySearchForFirst {
	private static QuickSort qs = new QuickSort();
	private static int size = 50;
	private static int searchValue = 3;
	
	public static void main(String[] args) {
		MyRandomUtils.setSeed(System.currentTimeMillis());
		int[] ary = MyRandomUtils.generateIntAry(size, 1, size);
		qs.sort(ary, 0, ary.length - 1);
		System.out.println("有序数组:\t" + Arrays.toString(ary));
		int index = search(ary, 0, ary.length - 1);
		System.out.println("第一个等于" + searchValue + "的元素的下标:[" + index + "]");
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
			if(middle == 0 || ary[middle - 1]  != searchValue) {
				return middle;
			} else {
				return search(ary, min, middle - 1);
			}
		}
	}
}
