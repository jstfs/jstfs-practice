package com.jstfs.practice.algorithm.search.binary;

import java.util.Arrays;

import com.jstfs.common.utils.MyRandomUtils;
import com.jstfs.practice.algorithm.sort.quick.QuickSort;

/**
 * 二分查找变体一: 查找第一个等于给定值的元素
 * 
 * @createBy jstfs
 * @createTime 2019-1-19 下午11:51:24
 */
public class BinarySearchForFirst {
	private static QuickSort qs = new QuickSort();
	private static int size = 50;
	private static int searchValue = 1;
	
	public static void main(String[] args) {
		Integer[] ary = MyRandomUtils.generateIntegerAry(size);
		System.out.println(Arrays.toString(ary));
		qs.sort(ary, false);
		System.out.println(Arrays.toString(ary));
		
		System.out.println("第一个等于" + searchValue + "的数的下标:[" + search(ary, 0, ary.length - 1) + "]");
	}
	
	public static int search(Integer[] ary, int min, int max) {
		if(min >= max) {
			return -1;
		}
		
		int middle = min + ((max - min) >> 1);
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
